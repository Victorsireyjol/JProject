package model;

public class Produit {
    private String nom;
    private double prix;  // Ajout du prix comme attribut
    private int quantiteStock;

    // Constructeur avec le prix
    public Produit(String nom, double prix, int quantiteStock) {
        this.nom = nom;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
    }

    // Getter pour le prix
    public double getPrix() {
        return prix;
    }

    // Setter pour le prix
    public void setPrix(double prix) {
        this.prix = prix;
    }

    // Méthode pour obtenir les détails du produit
    public String getDetails() {
        return "Produit: " + nom + ", Prix: " + String.format("%.2f €", prix) + ", Quantité en stock: " + quantiteStock;
    }

    // Getters et setters pour les autres attributs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }
}
