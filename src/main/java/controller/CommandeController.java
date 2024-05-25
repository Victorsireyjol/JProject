package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.BonCommande;
import model.Client;
import model.Commande;
import model.Produit;
import view.BonCommandeView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CommandeController {
    private List<Client> clients;
    private List<Produit> produits;
    private List<Commande> commandes;
    private List<BonCommande> bonsDeCommandeValidés;
    private SceneManager sceneManager;
    private BonCommandeView bonCommandeView;
    private AtomicInteger nextCommandeId; // To generate unique IDs

    public CommandeController(List<Client> clients, List<Produit> produits, List<Commande> commandes) {
        this.clients = clients;
        this.produits = produits;
        this.commandes = commandes;
        this.bonsDeCommandeValidés = new ArrayList<>();
        this.nextCommandeId = new AtomicInteger(1); // Initialize with the first ID
    }

    public int getNewCommandeId() {
        return nextCommandeId.getAndIncrement();
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

                BonCommande bonDeCommande = creerBonDeCommande(commande);
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
        Commande commande1 = commande ;

        return new BonCommande(idCommande, client, produit, quantite, dateCommande , commande1);
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



    public void showBonCommandeView() {
        BonCommandeView bonCommandeView = new BonCommandeView(this);
        bonCommandeView.setClients(clients);
        bonCommandeView.setBonsDeCommande(bonsDeCommandeValidés);
        bonCommandeView.display();
    }


    public void showStockView() {
        sceneManager.showStockView();
    }

    public void showInitialView() {
        sceneManager.showInitialView();
    }



    public void mettreAJourStatutCommande(int idCommande, String statut) {
        for (Commande commande : commandes) {
            if (commande.getId() == idCommande) {
                commande.setStatut(statut);
                break;
            }
        }
    }

    public void creerCommande(Client selectedClient, Produit selectedProduit, int quantite) {
        if (selectedProduit != null && selectedClient != null && quantite > 0) {
            int newId = getNewCommandeId();
            int prix = selectedProduit.getPrix() * quantite;
            Commande nouvelleCommande = new Commande(newId, selectedClient, selectedProduit, quantite, "En attente de stock",prix);
            String result = ajouterCommande(nouvelleCommande);
            // Appel à la vue pour afficher le message
            if (sceneManager != null) {
                sceneManager.afficherMessage(result, Alert.AlertType.INFORMATION);
            }
        } else {
            if (sceneManager != null) {
                sceneManager.afficherMessage("Veuillez remplir tous les champs correctement.", Alert.AlertType.ERROR);
            }
        }
    }
}






