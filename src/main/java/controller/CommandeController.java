package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BonCommande;
import model.Client;
import model.Produit;
import model.Commande;
import view.BonCommandeView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandeController {
    private List<Client> clients;
    private List<Produit> produits;
    private List<Commande> commandes;

    private List<BonCommande> bonsDeCommandeValidés;

    private SceneManager sceneManager ;

    public CommandeController(List<Client> clients, List<Produit> produits, List<Commande> commandes) {
        this.clients = clients;
        this.produits = produits;
        this.commandes = commandes;
        this.bonsDeCommandeValidés = new ArrayList<>();
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

    public void verifierEtValiderCommandes(Produit produit) {
        List<Commande> commandesAValider = commandes.stream()
                .filter(c -> c.getProduit().equals(produit) && c.getStatut().equals("En attente de stock"))
                .collect(Collectors.toList());

        for (Commande c : commandesAValider) {
            if (produit.getQuantiteStock() >= c.getQuantite()) {
                c.setStatut("Confirmée");
                produit.setQuantiteStock(produit.getQuantiteStock() - c.getQuantite());

                // Créer un bon de commande
                BonCommande bonDeCommande = creerBonDeCommande(c);

                bonsDeCommandeValidés.add(bonDeCommande);
            }
        }
    }

    public String ajouterCommande(Commande commande) {
        for (Produit produit : produits) {
            if (produit.equals(commande.getProduit()) && produit.getQuantiteStock() >= commande.getQuantite()) {
                produit.setQuantiteStock(produit.getQuantiteStock() - commande.getQuantite());
                commande.setStatut("Confirmée");
                commandes.add(commande);

                // Créer un bon de commande
                BonCommande bonDeCommande = creerBonDeCommande(commande);

                // Afficher le bon de commande
                bonsDeCommandeValidés.add(bonDeCommande);

                return "La commande a été validée.";
            }
        }
        commande.setStatut("En attente de stock");
        commandes.add(commande);
        return "Stock insuffisant. La commande est en attente de réapprovisionnement.";
    }



    private BonCommande creerBonDeCommande(Commande commande) {
        int idCommande = commande.getId();
        String client = commande.getClient().getNom();
        Produit produit = commande.getProduit();
        int quantite = commande.getQuantite();
        LocalDate dateCommande = LocalDate.now();

        return new BonCommande(idCommande, client, produit, quantite, dateCommande);
    }

    public List<BonCommande> getBonsDeCommandeValidés() {
        return bonsDeCommandeValidés;
    }


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
