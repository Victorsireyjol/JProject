package model;

public class Commande {
    private int id;
    private String produit;
    private int quantite;
    private String statut;

    public Commande(int id, String produit, int quantite, String statut) {
        this.id = id;
        this.produit = produit;
        this.quantite = quantite;
        this.statut = statut;
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

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Autres getters et setters
}





