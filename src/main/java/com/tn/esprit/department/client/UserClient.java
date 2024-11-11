package com.tn.esprit.department.client;

import com.tn.esprit.department.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8057/api/users")
public interface UserClient {
    @GetMapping("/department/{departmentId}")
    List<UserDTO> getUsersByDepartmentId(@PathVariable Long departmentId);
}