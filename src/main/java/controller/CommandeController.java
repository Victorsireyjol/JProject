package controller;

import model.Commande;
import model.Produit;

public class CommandeController {
    private Commande commande;
    private Produit produit;
    private String nomClient;
    public CommandeController() {

    }

    // Méthode pour traiter la commande
    public boolean traiterCommande(Commande commande, Produit produit) {
        if (commande.getQuantite() > produit.getQuantiteStock()) {
            // Le stock est insuffisant, la commande ne peut pas être traitée
            commande.setStatut("en attente");
            return false;
        } else {
            // Le stock est suffisant, la commande peut être traitée
            commande.setStatut("en cours");

            // mise a jour du Stock du produit
            produit.setQuantiteStock(produit.getQuantiteStock() - commande.getQuantite());
            return true;
        }


    }

}