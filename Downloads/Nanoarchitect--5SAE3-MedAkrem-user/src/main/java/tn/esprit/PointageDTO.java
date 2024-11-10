package tn.esprit;



import java.time.LocalDateTime;

public class PointageDTO {
    private Long id;
    private LocalDateTime dateEntree;
    private LocalDateTime dateSortie;
    private Long employeId; // Stocke uniquement l'ID de l'employé pour établir la relation

    // Constructeurs
    public PointageDTO() {}

    public PointageDTO(Long id, LocalDateTime dateEntree, LocalDateTime dateSortie, Long employeId) {
        this.id = id;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.employeId = employeId;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDateTime dateEntree) {
        this.dateEntree = dateEntree;
    }

    public LocalDateTime getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDateTime dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }
}
