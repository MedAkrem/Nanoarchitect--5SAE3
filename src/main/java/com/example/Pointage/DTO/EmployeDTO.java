package com.example.Pointage.DTO;



public class EmployeDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    // Ajouter d'autres champs si nécessaire

    // Constructeur par défaut
    public EmployeDTO() {}

    // Constructeur avec tous les champs
    public EmployeDTO(Long id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
