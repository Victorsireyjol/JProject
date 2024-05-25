package model;

public class Commande {
    private int id;
    private Client client;
    private Produit produit;
    private int quantite;
    private String statut;

    private int prix ;

    public Commande(int id, Client client, Produit produit, int quantite, String statut , int prix) {
        this.id = id;
        this.client = client;
        this.produit = produit;
        this.quantite = quantite;
        this.statut = statut;
        this.prix = prix;
    }

    // Getters et setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getStatut() {
        return statut;
    }

    public int getPrix() {
        return prix;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String StringPrix() {
String stringprix ;
stringprix =  getPrix() + "$" ;
return stringprix ;

    }

}



