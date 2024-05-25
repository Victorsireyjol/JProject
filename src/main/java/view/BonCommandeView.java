package view;

import controller.CommandeController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BonCommande;
import model.Client;

import java.util.List;
import java.util.stream.Collectors;

public class BonCommandeView {
    private CommandeController controller;
    private ComboBox<Client> clientComboBox;
    private ListView<BonCommande> bonCommandeListView;
    private List<Client> clients;
    private List<BonCommande> bonsDeCommande;

    public BonCommandeView(CommandeController controller) {
        this.controller = controller;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setBonsDeCommande(List<BonCommande> bonsDeCommande) {
        this.bonsDeCommande = bonsDeCommande;
    }

    public void display() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);

        clientComboBox = new ComboBox<>();
        clientComboBox.setItems(controller.getClients());
        clientComboBox.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(Client item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null || empty ? "" : item.getNom());
            }
        });

        clientComboBox.setOnAction(event -> afficherBonsDeCommandeParClient());

        bonCommandeListView = new ListView<>();
        bonCommandeListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !bonCommandeListView.getSelectionModel().isEmpty()) {
                BonCommande selectedBon = bonCommandeListView.getSelectionModel().getSelectedItem();
                if (selectedBon != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous facturer ce bon de commande?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == ButtonType.YES) {
                            controller.mettreAJourStatutCommande(selectedBon.getId(), "Facturée");
                        }
                    });
                }
            }
        });

        layout.getChildren().addAll(clientComboBox, bonCommandeListView);
        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Bons de Commande");
        stage.show();
    }

    private void afficherBonsDeCommandeParClient() {
        Client selectedClient = clientComboBox.getValue();
        if (selectedClient != null) {
            List<BonCommande> bons = bonsDeCommande.stream()
                    .filter(bon -> bon.getClient().equals(selectedClient.getNom()))
                    .collect(Collectors.toList());
            bonCommandeListView.setItems(javafx.collections.FXCollections.observableArrayList(bons));
            bonCommandeListView.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(BonCommande bonCommande, boolean empty) {
                    super.updateItem(bonCommande, empty);
                    if (bonCommande == null || empty) {
                        setText(null);
                    } else {
                        setText("Commande ID: " + bonCommande.getId() + ", Produit: " + bonCommande.getProduit().getNom() + ", Quantité: " + bonCommande.getQuantite()  + ", Prix: " + bonCommande.getCommande().StringPrix()  + ", Date: " + bonCommande.getDateCommande() + ", Status: " + bonCommande.getCommande().getStatut());
                    }
                }
            });
        }
    }
}
