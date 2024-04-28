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
    public void AfficheCommande() {
        System.out.println("Commande pour le client " + client + ":");
        System.out.println("Produit: " + produit);
        System.out.println("Quantité: " + quantite);
        System.out.println("Statut: " + statut);
    }


}





