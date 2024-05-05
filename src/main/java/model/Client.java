package model;

public class Client {
    private String nom;

    public Client(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getDetails() {
        return "Client: " + nom ;
    }

}
