package tn.esprit.salairems.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.salairems.dto.EmployeeDto;

@FeignClient(name = "user-service", url = "http://localhost:8057/api/users")
public interface EmployeeClient {

    @GetMapping("/{id}")
    EmployeeDto getUserById(@PathVariable("id") Long id);
}
