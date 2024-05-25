package model;

import java.time.LocalDate;

public class BonCommande {
    private int id;
    private String client;
    private Produit produit;
    private int quantite;
    private LocalDate dateCommande;
    private boolean facture;

    public BonCommande(int id, String client, Produit produit, int quantite, LocalDate dateCommande) {
        this.id = id;
        this.client = client;
        this.produit = produit;
        this.quantite = quantite;
        this.dateCommande = dateCommande;

    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }


    public LocalDate getDateCommande() {
        return dateCommande;
    }


}
