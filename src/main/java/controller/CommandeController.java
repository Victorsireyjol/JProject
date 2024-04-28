package controller;

import model.Commande;
import model.Stock;

public class CommandeController {
    private Commande commande;
    private Stock stock;

    public CommandeController(Commande commande, Stock stock) {
        this.commande = commande;
        this.stock = stock;
    }

    // Méthode pour traiter la commande
    public boolean traiterCommande(Commande commande) {
        if (stock.estDisponible(commande.getQuantite())) {
            // Le stock est suffisant, la commande peut être traitée
            commande.setStatut("en cours");
            return true;
        } else {
            // Le stock est insuffisant, la commande ne peut pas être traitée
            commande.setStatut("en attente");
            return false;
        }

    }
}