package com.example.Pointage.Repository;

import com.example.Pointage.Entity.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointageRepository extends JpaRepository<Pointage, Long> {
    List<Pointage> findByEmployeId(Long employeId);
}
