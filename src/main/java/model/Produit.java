package model;

public class Produit {
    private int id;
    private String nom;
    private int quantiteStock;

    private int prix;

    public Produit(int id, String nom, int quantiteStock, int prix) {
        this.id = id;
        this.nom = nom;
        this.quantiteStock = quantiteStock;
        this.prix = prix;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public int getPrix() {
        return prix;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public String StringPrix() {
        String stringprix;
        stringprix = getPrix() + "$";
        return stringprix;
    }
}