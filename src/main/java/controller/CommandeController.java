package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BonDeLivraison;
import model.Client;
import model.Produit;
import model.Commande;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommandeController {
    private List<Client> clients;
    private List<Produit> produits;
    private List<Commande> commandes;

    private SceneManager sceneManager ;

    public CommandeController(List<Client> clients, List<Produit> produits, List<Commande> commandes) {
        this.clients = clients;
        this.produits = produits;
        this.commandes = commandes;
    }

    public ObservableList<Client> getClients() {
        return FXCollections.observableArrayList(clients);
    }

    public ObservableList<Produit> getProduits() {
        return FXCollections.observableArrayList(produits);
    }

    public void reapprovisionnerProduit(String nomProduit, int quantite) {
        for (Produit produit : produits) {
            if (produit.getNom().equals(nomProduit)) {
                produit.setQuantiteStock(produit.getQuantiteStock() + quantite);
                verifierEtValiderCommandes(produit);
                break;
            }
        }
    }

    private void verifierEtValiderCommandes(Produit produit) {
        commandes.stream()
                .filter(c -> c.getProduit().equals(produit) && c.getStatut().equals("En attente de stock"))
                .forEach(c -> {
                    if (produit.getQuantiteStock() >= c.getQuantite()) {
                        c.setStatut("Confirmée");
                        produit.setQuantiteStock(produit.getQuantiteStock() - c.getQuantite());
                    }
                });
    }

    public String ajouterCommande(Commande commande) {
        for (Produit produit : produits) {
            if (produit.equals(commande.getProduit()) && produit.getQuantiteStock() >= commande.getQuantite()) {
                produit.setQuantiteStock(produit.getQuantiteStock() - commande.getQuantite());
                commande.setStatut("Confirmée");
                commandes.add(commande);
                return "La commande a été validée.";
            }
        }
        commande.setStatut("En attente de stock");
        commandes.add(commande);
        return "Stock insuffisant. La commande est en attente de réapprovisionnement.";
    }


    public String validerCommande(Commande commande) {
        if (commande.getProduit().getQuantiteStock() >= commande.getQuantite()) {
            commande.getProduit().setQuantiteStock(commande.getProduit().getQuantiteStock() - commande.getQuantite());
            commande.setStatut("Confirmée");
            creerBonDeLivraison(commande);
            return "La commande a été validée.";
        }
        return "Stock insuffisant.";
    }

    private void creerBonDeLivraison(Commande commande) {
        BonDeLivraison bon = new BonDeLivraison(
                commande.getId(),
                LocalDate.now(),
                commande.getClient().getDetails(),
                commande.getProduit().getDetails()
        );
        // Logique pour sauvegarder ou afficher le bon de livraison
       // sauvegarderBonDeLivraison(bon);
    }


//    public void afficherBonsDeLivraisonParClient(Client client) {
//        List<BonDeLivraison> bons = serviceDeLivraison.getBonsDeLivraison(client);
//        // Méthode hypothétique pour récupérer les bons de livraison
//        afficherBonsDeLivraison(bons);  // Affiche les bons dans une nouvelle fenêtre ou un dialogue
//    }

    public ObservableList<Commande> getCommandesParClient(Client client) {
        return FXCollections.observableArrayList(
                commandes.stream()
                        .filter(c -> c.getClient().equals(client))
                        .collect(Collectors.toList())
        );
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void showStockView() {
        sceneManager.showStockView();
    }

    public void showInitialView() {
        sceneManager.showInitialView();
    }




}
