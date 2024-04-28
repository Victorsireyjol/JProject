package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Client;
import model.Commande;
import model.Produit;
import controller.CommandeController;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private CommandeController commandeController;
    private List<Commande>commandes = new ArrayList<Commande>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion de Commande");

        // Création de la liste des produits disponibles
        List<Produit> produitsDisponibles = new ArrayList<>();
        produitsDisponibles.add(new Produit("ProduitA", 10));
        produitsDisponibles.add(new Produit("ProduitB", 5));
        produitsDisponibles.add(new Produit("ProduitC", 2));

        // Création d'une liste déroulante (ComboBox) pour sélectionner le produit
        ComboBox<Produit> produitsComboBox = new ComboBox<>();
        produitsComboBox.setItems(FXCollections.observableArrayList(produitsDisponibles));


        List<Client> clientsDisponibles = new ArrayList<>();
        clientsDisponibles.add(new Client("Client 1"));
        clientsDisponibles.add(new Client("Client 2"));
        clientsDisponibles.add(new Client("Client 3"));

        // Création d'une liste déroulante (ComboBox) pour sélectionner le client
        ComboBox<Client> clientsComboBox = new ComboBox<>();
        clientsComboBox.setItems(FXCollections.observableArrayList(clientsDisponibles));

        // Champ de saisie pour la quantité
        TextField quantiteTextField = new TextField();
        quantiteTextField.setPromptText("Quantité");

        // Bouton de validation
        Button validerButton = new Button("Valider");
        validerButton.setOnAction(e -> {
            Produit produitSelectionne = produitsComboBox.getValue();
            Client clientSelectionne = clientsComboBox.getValue();
            String quantiteStr = quantiteTextField.getText();
            validerCommande(produitSelectionne, quantiteStr ,clientSelectionne);
        });

        Button commandesButton = new Button("Voir les commandes");
        commandesButton.setOnAction(e -> {
            CommandesPage commandesPage = new CommandesPage(clientsDisponibles,commandes);
            Stage commandesStage = new Stage();
            commandesPage.start(commandesStage);
        });


        // Mise en page de l'interface utilisateur
        // Bouton pour accéder à la page des commandes



        VBox root = new VBox();
        root.getChildren().add(commandesButton);
        root.getChildren().addAll(new Label("Clients : "), clientsComboBox);
        root.getChildren().addAll(new Label("Produit:"), produitsComboBox, quantiteTextField, validerButton);

        // Affichage de l'interface utilisateur
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialisation du contrôleur de commande
        commandeController = new CommandeController();
    }

    private void validerCommande(Produit produit, String quantiteStr , Client cLient) {
        try {
            int quantite = Integer.parseInt(quantiteStr);
            if (quantite <= 0) {
                afficherMessageErreur("La quantité doit être un entier positif.");
                return;
            }
            Commande commande = new Commande(produit.getNom(), quantite, "en attente" ,cLient.getNom());
            commandes.add(commande);
            boolean commandeValidee = commandeController.traiterCommande(commande, produit);

            if (commandeValidee) {
                afficherMessageConfirmation("La commande a été acceptée.");
            } else {
                afficherMessageErreur("Stock insuffisant. La commande est en attente de réapprovisionnement.");
            }
        } catch (NumberFormatException e) {
            afficherMessageErreur("La quantité doit être un entier.");
        }
    }

    private void afficherMessageConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
