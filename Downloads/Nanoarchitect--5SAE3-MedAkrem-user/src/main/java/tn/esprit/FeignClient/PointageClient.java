package tn.esprit.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tn.esprit.DTO.PointageDTO;
import tn.esprit.config.FeignClientConfiguration;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "pointage-service", url = "http://localhost:8081/pointage-service/api/pointage",configuration = FeignClientConfiguration.class)
public interface PointageClient {
    @GetMapping("/employe/{employeId}")
    List<PointageDTO> getPointagesByEmployeId(@PathVariable("employeId") Long employeId);

    @GetMapping("/employe/{employeId}/totalHeures")
    Duration getTotalHeuresTravaillées(
            @PathVariable("employeId") Long employeId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate);
}
