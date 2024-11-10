package com.example.Pointage.Repository;

import com.example.Pointage.Entity.pointage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointageRepository extends JpaRepository<pointage,Long> {
}
