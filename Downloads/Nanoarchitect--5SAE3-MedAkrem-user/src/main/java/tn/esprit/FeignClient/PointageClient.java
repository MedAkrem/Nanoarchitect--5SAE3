package tn.esprit.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.DTO.PointageDTO;

import java.util.List;

@FeignClient(name = "pointage-service")
public interface PointageClient {
    @GetMapping("/employe/{employeId}")
    List<PointageDTO> getPointagesByEmployeId(@PathVariable("employeId") Long employeId);
}
