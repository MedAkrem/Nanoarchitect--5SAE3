package tn.esprit.User.AppResouces.Services.interfaces;

import tn.esprit.User.AppResouces.Exceptions.*;
import tn.esprit.User.AppResouces.Models.DTOs.ProfileDto;
import tn.esprit.User.AppResouces.Models.DTOs.UpdatePasswordDto;
import tn.esprit.User.AppResouces.Models.DTOs.UserDto;
import tn.esprit.User.AppResouces.Models.Entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    // Current User features :
    public User getCurrentUser();
    public String updatePassword(UpdatePasswordDto dto) throws PasswordException;
    public ProfileDto updateProfile(ProfileDto dto, MultipartFile file) throws UserException, PermissionException, IOException;
    public List<UserDto> getMyTeam() throws UserException;



    // Admin features :
    public String resetPassword(Long userId) throws UserException, PermissionException;
    public UserDto getUserById(Long userId) throws UserException;

    public UserDto addUser(UserDto user) throws UserException, DepartementException;
    public UserDto updateUser(Long userId, UserDto dto) throws PermissionException, UserException, DepartementException;
    public List<UserDto> getAllUsers() throws UserException;
    public UserDto deleteUser(Long userId) throws UserException, PermissionException, IOException, DemandeException;
}
