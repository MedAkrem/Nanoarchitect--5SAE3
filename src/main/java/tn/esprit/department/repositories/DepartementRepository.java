package tn.esprit.department.repositories;

import tn.esprit.department.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
}
