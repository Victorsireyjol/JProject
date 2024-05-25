package view;

import controller.CommandeController;
import controller.SceneManager;
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
    private SceneManager controllermanager ;
    private ComboBox<Client> clientComboBox;
    private ComboBox<Produit> produitComboBox;
    private TextField quantiteTextField;
    private Button validerButton;
    private Button voirCommandesButton;
    private Button voirStockButton;
    private Button voirBonsCommandes;

    public CommandesView(CommandeController controller) {
        this.controller = controller;
    }

    public VBox render() {
        VBox layout = new VBox(10);
        HBox buttonsLayout = new HBox(10);

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

        voirBonsCommandes = new Button("Voir les bons de commande");
        voirBonsCommandes.setOnAction(e -> controller.showBonCommandeView());

        layout.getChildren().addAll(
                new Label("Client:"), clientComboBox,
                new Label("Produit:"), produitComboBox,
                new Label("Quantité:"), quantiteTextField,
                validerButton
        );

        buttonsLayout.getChildren().addAll(voirCommandesButton, voirStockButton, voirBonsCommandes);
        layout.getChildren().add(buttonsLayout);

        return layout;
    }

    private void creerCommande() {
        Client selectedClient = clientComboBox.getValue();
        Produit selectedProduit = produitComboBox.getValue();
        try {
            int quantite = Integer.parseInt(quantiteTextField.getText());
            controller.creerCommande(selectedClient, selectedProduit, quantite);
        } catch (NumberFormatException ex) {
            controllermanager.afficherMessage("La quantité doit être un nombre entier positif.", Alert.AlertType.ERROR);
        }
    }


}
