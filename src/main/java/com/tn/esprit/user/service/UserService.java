package com.tn.esprit.user.service;


import com.tn.esprit.user.entity.User;
import com.tn.esprit.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(userDetails.getEmail());
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        user.setSexe(userDetails.getSexe());
        user.setDepartementId(userDetails.getDepartementId());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersByDepartmentId(Long departmentId) {
        return userRepository.findByDepartementId(departmentId);
    }
}