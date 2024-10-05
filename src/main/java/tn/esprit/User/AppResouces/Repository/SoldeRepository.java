package tn.esprit.User.AppResouces.Repository;

import tn.esprit.User.AppResouces.Models.Entities.Solde;
import tn.esprit.User.AppResouces.Models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoldeRepository extends JpaRepository<Solde, Long> {
    Optional<Solde> findByUser(User user);
}
