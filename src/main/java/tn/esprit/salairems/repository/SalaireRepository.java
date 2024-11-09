package tn.esprit.salairems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.salairems.entity.Salaire;

public interface SalaireRepository extends JpaRepository<Salaire, Long> {

    Salaire findByEmployeeId(Long employeeId);

    /*
    @Query("SELECT SUM(s.amount) FROM Salaire s")
    Double findTotalSalary();

    @Query("SELECT AVG(s.amount) FROM Salaire s")
    Double findAverageSalary();
    */

}
