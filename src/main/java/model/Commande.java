package model;

public class Commande {

    private static int nextId = 1;
    private Produit produit;
    private int quantite;
    private String statut;
    private Client client;

    private int id;

    public Commande(Produit produit, int quantite, String statut, Client client) {
        this.id = nextId++;
        this.produit = produit;
        this.quantite = quantite;
        this.statut = statut;
        this.client = client;
    }


    public int getId() {
        return id;
    }

    // Getters pour accéder aux propriétés de la commande
    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getStatut() {
        return statut;
    }

    public Client getClient() {
        return client;
    }

    // Setter pour modifier le statut de la commande
    public void setStatut(String statut) {
        this.statut = statut;
    }
}
