package model;

public class Produit {
    private String nom;
    private int quantiteStock;

    public Produit(String nom, int quantiteStock) {
        this.nom = nom;
        this.quantiteStock = quantiteStock;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantiteStock()
    {
        return quantiteStock;
    }

    public void setQuantiteStock(int NouvelleQuantite){

        this.quantiteStock = NouvelleQuantite ;
    }

    @Override
    public String toString() {
        return nom + " Quantité : " + quantiteStock ;
    }
}
