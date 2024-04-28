package model;

import java.util.List;

public class Commande {

    private String produit;
    private int quantite;
    private String statut;
    private String client ;
    // Dans la classe Commande ou Client



    public Commande(String produit, int quantite, String statut , String client ) {


        this.produit = produit;
        this.quantite = quantite;
        this.statut = statut;
        this.client = client;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getClient() {
        return client;
    }

    public String getProduit() {
        return produit;
    }
    @Override
    public String toString() {
        return "Commande du " + client + ", status : " + statut + ", produits : " + produit + ", quantité : " + quantite;
    }



}





