package view;

import controller.CommandeController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Client;
import model.Produit;
import model.Commande;

public class CommandesView {
    private CommandeController controller;
    private ComboBox<Client> clientComboBox;
    private ComboBox<Produit> produitComboBox;
    private TextField quantiteTextField;
    private Button validerButton;
    private Button voirCommandesButton;
    private Button voirStockButton;

    public CommandesView(CommandeController controller) {
        this.controller = controller;
    }

    public VBox render() {
        VBox layout = new VBox(10);
        HBox buttonsLayout = new HBox(10);  // Layout pour les boutons en bas



        clientComboBox = new ComboBox<>(controller.getClients());
        clientComboBox.setCellFactory(cb -> new ListCell<Client>() {
            @Override
            protected void updateItem(Client item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null || empty ? "" : item.getNom());
            }
        });

        produitComboBox = new ComboBox<>(controller.getProduits());
        produitComboBox.setCellFactory(cb -> new ListCell<Produit>() {
            @Override
            protected void updateItem(Produit item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null || empty ? "" : item.getNom() + " (Stock: " + item.getQuantiteStock() + ")");
            }
        });

        quantiteTextField = new TextField();
        quantiteTextField.setPromptText("Quantité");

        validerButton = new Button("Valider");
        validerButton.setOnAction(e -> creerCommande());

        voirCommandesButton = new Button("Voir les commandes du Client");
        voirCommandesButton.setOnAction(e -> {
            Client selectedClient = clientComboBox.getValue();
            if (selectedClient != null) {
                CommandesParClientView commandesView = new CommandesParClientView(controller);
                commandesView.display(selectedClient);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez sélectionner un client.");
                alert.showAndWait();
            }
        });
        voirStockButton = new Button("Voir le stock");
        voirStockButton.setOnAction(e -> controller.showStockView());


        // Ajout de tous les composants au layout principal
        layout.getChildren().addAll(
                new Label("Client:"), clientComboBox,
                new Label("Produit:"), produitComboBox,
                new Label("Quantité:"), quantiteTextField,
                validerButton
        );

        // Ajouter les boutons au layout des boutons et ce layout au layout principal
        buttonsLayout.getChildren().add(voirCommandesButton);
        buttonsLayout.getChildren().add(voirStockButton);
        layout.getChildren().add(buttonsLayout);  // Ajouter le layout des boutons tout en bas

        return layout;
    }

    private void creerCommande() {
        Client selectedClient = clientComboBox.getValue();
        Produit selectedProduit = produitComboBox.getValue();
        try {
            int quantite = Integer.parseInt(quantiteTextField.getText());
            if (selectedProduit != null && selectedClient != null && quantite > 0) {
                Commande nouvelleCommande = new Commande(selectedProduit, quantite, "En attente", selectedClient);
                String result = controller.ajouterCommande(nouvelleCommande);
                afficherMessage(result, Alert.AlertType.INFORMATION);
            } else {
                afficherMessage("Veuillez remplir tous les champs correctement.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException ex) {
            afficherMessage("La quantité doit être un nombre entier positif.", Alert.AlertType.ERROR);
        }
    }

    private void afficherMessage(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Notification de Commande");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
