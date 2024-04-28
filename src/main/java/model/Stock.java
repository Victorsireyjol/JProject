package model;

public class Stock {
    private String produit;
    private int quantiteDisponible;

    public Stock(String produit, int quantiteDisponible) {
        this.produit = produit;
        this.quantiteDisponible = quantiteDisponible;
    }

    // Méthode pour vérifier la disponibilité du produit dans le stock
    public boolean estDisponible(int quantiteDemandee) {
        if(quantiteDemandee <= quantiteDisponible) {
return true ;
        } else {
            return false ;

        }


    }
}