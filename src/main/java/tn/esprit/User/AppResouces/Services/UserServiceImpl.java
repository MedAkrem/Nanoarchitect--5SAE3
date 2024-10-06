package tn.esprit.User.AppResouces.Services;

import tn.esprit.User.AppResouces.Exceptions.*;
import tn.esprit.User.AppResouces.Models.DTOs.DepartmentDto;
import tn.esprit.User.AppResouces.Models.DTOs.ProfileDto;
import tn.esprit.User.AppResouces.Models.DTOs.UpdatePasswordDto;
import tn.esprit.User.AppResouces.Models.DTOs.UserDto;
import tn.esprit.User.AppResouces.Models.ENUMs.Role;
import tn.esprit.User.AppResouces.Models.Entities.*;
import tn.esprit.User.AppResouces.Repository.*;
import tn.esprit.User.AppResouces.Services.interfaces.UserService;


import tn.esprit.User.AppUtils.FileUpload.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentClient departmentClient;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder pwd;
    /*
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private TokenRepository tokenRepository;
    */
    @Autowired
    private SoldeServiceImpl soldeService;


    // Current User features :
    @Override
    public User getCurrentUser() {

        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if(o.equals("anonymousUser")) throw new RuntimeException("Please login first...");

        UserDetails userDetails = (UserDetails)o;


        String username = userDetails.getUsername();


        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user does not exist"));

    }

    @Override
    public String updatePassword(UpdatePasswordDto dto) throws PasswordException {
        User currentUser = getCurrentUser();

        if(!dto.getNewPassword().equals(dto.getConfirmPassword()))
            throw new PasswordException("Password confirmation does not match");

        if(!pwd.matches(dto.getCurrentPassword(), currentUser.getPassword()))
            throw new PasswordException("Current Password is incorrect");

        if(pwd.matches(dto.getNewPassword(), currentUser.getPassword()))
            throw new PasswordException("New password needs to be different");

        currentUser.setPassword(pwd.encode(dto.getNewPassword()));
        userRepository.save(currentUser);

        return "Password has been changed";
    }

    public ProfileDto updateProfile(ProfileDto dto, MultipartFile file) throws UserException, PermissionException, IOException {
        User currentUser = getCurrentUser();
        User targetUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserException("User does not exist with this id..."));
        if(currentUser.getCin() != targetUser.getCin()) throw new PermissionException("You don't have permission");

        if(!Objects.equals(targetUser.getTelephone(), dto.getTelephone())){
            if(userRepository.existsByTelephone(dto.getTelephone())) throw new UserException("PhoneNumber already exists");
            targetUser.setTelephone(dto.getTelephone());
        }
        if(Objects.nonNull(file)){
            if(Objects.isNull(targetUser.getImgPath())){
                String imgPath = FileUploadUtil.saveFile(file);
                targetUser.setImgPath(imgPath);
            }else {
                FileUploadUtil.deleteFile(targetUser.getImgPath());
                String imgPath = FileUploadUtil.saveFile(file);
                targetUser.setImgPath(imgPath);
            }

        }
        if(targetUser.getNomComplet() != dto.getNomComplet()){ targetUser.setNomComplet(dto.getNomComplet());}
        User user = userRepository.save(targetUser);
        ProfileDto profileDto = modelMapper.map(user, ProfileDto.class);
        return profileDto;

    }

    @Override
    public List<UserDto> getMyTeam() throws UserException {
        User currentUser = getCurrentUser();
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserException("No Users Found");
        } else {
            List<UserDto> dtoList = new ArrayList<>();

            for (User user : users) {
                // Check if the user's department ID matches the current user's department ID
                if (Objects.equals(currentUser.getDepartementId(), user.getDepartementId())) {
                    UserDto userDto = modelMapper.map(user, UserDto.class);

                    // Fetch department details using the DepartmentClient Feign client
                    if (user.getDepartementId() != null) {
                        DepartmentDto departmentDto = departmentClient.getDepartmentById(user.getDepartementId());
                        userDto.setDepartementId(departmentDto.getId());
                        userDto.setDepartementName(departmentDto.getName());
                    }

                    dtoList.add(userDto);
                }
            }

            return dtoList;
        }
    }



    // Admin features :
    @Override
    public String resetPassword(Long userId) throws UserException, PermissionException {
        User currentUser = getCurrentUser();
        Role role = currentUser.getRole();
        User targetUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User does not exist"));
        Role targetRole = targetUser.getRole();
        if(role != Role.DG && role != Role.RRH){
            throw new PermissionException("You don't have permission");
        }else{
            targetUser.setPassword(pwd.encode(targetUser.getCin()+"$mart"));
            userRepository.save(targetUser);
            return "Password has been reset successfully";
        }
    }

    @Override
    public UserDto getUserById(Long userId) throws UserException {
        // Retrieve the user entity by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User does not exist with this id..."));

        // Map the User entity to UserDto
        UserDto userDto = modelMapper.map(user, UserDto.class);

        // Use Feign client to get department details if the department ID is present
        if (user.getDepartementId() != null) {
            DepartmentDto departmentDto = departmentClient.getDepartmentById(user.getDepartementId());
            userDto.setDepartementId(departmentDto.getId());
            userDto.setDepartementName(departmentDto.getName());
        }

        return userDto;
    }


    @Override
    public UserDto addUser(UserDto user) throws UserException, DepartementException {
        // Use Feign client to verify if the department exists
        DepartmentDto department = departmentClient.getDepartmentById(user.getDepartementId());
        if (department == null) {
            throw new DepartementException("No department found with this Id");
        }

        // Check for existing CIN and telephone in the User repository
        if (userRepository.existsByCin(user.getCin())) {
            throw new UserException("CIN already exists");
        } else if (userRepository.existsByTelephone(user.getTelephone())) {
            throw new UserException("Telephone already exists");
        } else {
            // Map UserDto data to User entity and set department ID
            User newUser = new User();
            newUser.setCin(user.getCin());
            newUser.setUsername(user.getCin());
            newUser.setPassword(pwd.encode(user.getCin() + "$mart"));
            newUser.setNomComplet(user.getNomComplet());
            newUser.setQualification(user.getQualification());
            newUser.setTelephone(user.getTelephone());
            newUser.setDob(user.getDob());
            newUser.setDoj(user.getDoj());
            newUser.setSexe(user.getSexe());
            newUser.setRole(Role.USER);
            newUser.setEnabled(Boolean.TRUE);
            newUser.setIndependant(Boolean.FALSE);
            newUser.setDateCreation(LocalDateTime.now());

            // Set the department ID using the ID from the DepartmentDto fetched via Feign client
            newUser.setDepartementId(department.getId());

            // Save the new user in the repository
            User userObj = userRepository.save(newUser);

            // Add initial solde for the new user
            soldeService.addSolde(userObj.getId());

            return modelMapper.map(userObj, UserDto.class);
        }
    }


    @Override
    public UserDto updateUser(Long userId, UserDto dto) throws PermissionException, UserException, DepartementException {
        User currentUser = getCurrentUser();
        Role role = currentUser.getRole();
        User targetUser = userRepository.findById(userId).orElseThrow(() -> new UserException("User does not exist"));
        Role targetRole = targetUser.getRole();

        // Check for permissions
        if (((role != Role.DG) && (role != Role.RRH)) || (role == Role.RRH && targetRole == Role.DG)) {
            throw new PermissionException("You don't have permission");
        }

        // Update CIN if needed
        if (dto.getCin() != null && !dto.getCin().equals(targetUser.getCin())) {
            if (userRepository.existsByCin(dto.getCin())) {
                throw new UserException("CIN already exists");
            }
            targetUser.setCin(dto.getCin());
            targetUser.setUsername(dto.getCin());
        }

        // Update department if a new department ID is provided
        if (dto.getDepartementId() != null && !dto.getDepartementId().equals(targetUser.getDepartementId())) {
            // Verify the department using the DepartmentClient
            DepartmentDto department = departmentClient.getDepartmentById(dto.getDepartementId());
            if (department == null) {
                throw new DepartementException("Department does not exist");
            }

            // Additional role check to ensure department change rules
            if (targetUser.getRole() == Role.DG || targetUser.getRole() == Role.RRH || targetUser.getRole() == Role.CD) {
                throw new UserException("You must change Department Chef First");
            }

            // Update department ID in the user
            targetUser.setDepartementId(department.getId());
        }

        // Update other fields if they differ
        if (dto.getNomComplet() != null && !dto.getNomComplet().equals(targetUser.getNomComplet())) {
            targetUser.setNomComplet(dto.getNomComplet());
        }
        if (dto.getQualification() != null && !dto.getQualification().equals(targetUser.getQualification())) {
            targetUser.setQualification(dto.getQualification());
        }
        if (dto.getTelephone() != null && !dto.getTelephone().equals(targetUser.getTelephone())) {
            if (userRepository.existsByTelephone(dto.getTelephone())) {
                throw new UserException("Telephone already exists");
            }
            targetUser.setTelephone(dto.getTelephone());
        }
        if (dto.getDob() != null && !dto.getDob().equals(targetUser.getDob())) {
            targetUser.setDob(dto.getDob());
        }
        if (dto.getDoj() != null && !dto.getDoj().equals(targetUser.getDoj())) {
            targetUser.setDoj(dto.getDoj());
        }
        if (dto.getSexe() != null && !dto.getSexe().equals(targetUser.getSexe())) {
            targetUser.setSexe(dto.getSexe());
        }
        if (dto.isIndependant() != targetUser.isIndependant()) {
            targetUser.setIndependant(dto.isIndependant());
        }
        if (dto.isEnabled() != targetUser.isEnabled()) {
            if ((targetUser.getRole() == Role.DG || targetUser.getRole() == Role.RRH) && !dto.isEnabled()) {
                throw new UserException("This User can't be disabled!");
            }
            targetUser.setEnabled(dto.isEnabled());
        }

        // Save the updated user
        User updatedUser = userRepository.save(targetUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() throws UserException {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserException("No Users Found");
        } else {
            List<UserDto> dtoList = new ArrayList<>();

            for (User user : users) {
                UserDto userDto = modelMapper.map(user, UserDto.class);

                // Fetch department details using Feign client if department ID is not null
                if (user.getDepartementId() != null) {
                    DepartmentDto departmentDto = departmentClient.getDepartmentById(user.getDepartementId());
                    userDto.setDepartementId(departmentDto.getId());
                    userDto.setDepartementName(departmentDto.getName());
                }

                dtoList.add(userDto);
            }

            return dtoList;
        }
    }



    @Override
    public UserDto deleteUser(Long userId) throws UserException, PermissionException, IOException, DemandeException {
        User currentUser = getCurrentUser();
        Role role = currentUser.getRole();
        User targetUser = userRepository.findById(userId).orElseThrow(() -> new UserException("User does not exist"));
        Role targetRole = targetUser.getRole();

        // Check if the user's role allows deletion
        if ((targetRole == Role.DG) || (targetRole == Role.RRH)) {
            throw new PermissionException("This User can't be Deleted");
        } else {
            // If the target user is a department head, remove them from that position
            if (targetRole == Role.CD && targetUser.getDepartementId() != null) {
                DepartmentDto department = departmentClient.getDepartmentById(targetUser.getDepartementId());
                if (department == null) {
                    throw new UserException("Department does not exist");
                }
                // Update the department to remove the current user as the chef
                department.setChefId(null);
                departmentClient.updateDepartment(department.getId(), department);  // Assuming `updateDepartment` is an endpoint in `DepartmentClient`
            }

            // Delete user's image file if exists
            if (Objects.nonNull(targetUser.getImgPath())) {
                FileUploadUtil.deleteFile(targetUser.getImgPath());
            }


            /*
            // Remove all related notifications
            List<Notification> notificationsWithUserInViewPar = notificationRepository.findByVueParContaining(targetUser);
            for (Notification notification : notificationsWithUserInViewPar) {
                notification.getVuePar().remove(targetUser);
                notificationRepository.save(notification);
            }



            List<Notification> notificationsWithUserInDestination = notificationRepository.findByDestinationContaining(targetUser);
            for (Notification notification : notificationsWithUserInDestination) {
                notification.getDestination().remove(targetUser);
                if (notification.getDestination().size() > 0) {
                    notificationRepository.save(notification);
                } else {
                    notificationRepository.delete(notification);
                }
            }

            List<Notification> notificationsWithUserAsOrigine = notificationRepository.findByOrigine(targetUser);
            for (Notification notification : notificationsWithUserAsOrigine) {
                notificationRepository.delete(notification);
            }

             */

            /*

            // Remove all associated tokens
            List<Token> tokens = tokenRepository.findAllTheTokensByUser(targetUser.getId());
            for (Token token : tokens) {
                tokenRepository.delete(token);
            }

            // Remove all associated requests (demandes) and attachments
            List<Demande> dems = demandeRepository.findAllDemandesByUser(targetUser.getId());
            for (Demande dem : dems) {
                if (Objects.nonNull(dem.getAttachments())) {
                    for (Attachment x : dem.getAttachments()) {
                        FileUploadUtil.deleteFile(x.getPath());
                        attachmentRepository.delete(x);
                    }
                }
                demandeRepository.delete(dem);
            }
            */

            // Delete user's solde
            soldeService.deleteSolde(targetUser.getId());

            // Finally, delete the user
            userRepository.delete(targetUser);
        }

        // Map deleted user data to UserDto and fetch department details
        UserDto userDto = modelMapper.map(targetUser, UserDto.class);

        if (targetUser.getDepartementId() != null) {
            DepartmentDto department = departmentClient.getDepartmentById(targetUser.getDepartementId());
            userDto.setDepartementId(department.getId());
            userDto.setDepartementName(department.getName());
        }

        return userDto;
    }



}
