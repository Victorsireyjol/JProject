package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.CommandeController;
import model.Commande;
import model.Stock;

public class MainApp extends Application {

    private CommandeController commandeController;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion de Commande");

        // Création d'une liste de produits (à remplacer par vos propres produits)
        ComboBox<String> produitsComboBox = new ComboBox<>();
        produitsComboBox.getItems().addAll("ProduitA", "ProduitB", "ProduitC");

        // Champ de saisie pour la quantité
        TextField quantiteTextField = new TextField();
        quantiteTextField.setPromptText("Quantité");

        // Bouton de validation
        Button validerButton = new Button("Valider");
        validerButton.setOnAction(e -> validerCommande(produitsComboBox.getValue(), quantiteTextField.getText()));

        // Mise en page de l'interface utilisateur
        VBox root = new VBox();
        root.getChildren().addAll(new Label("Produit:"), produitsComboBox, quantiteTextField, validerButton);

        // Affichage de l'interface utilisateur
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Création d'une instance de Commande
        Commande commande = new Commande(0, "ProduitA", 0, "en attente");

// Création d'une instance de Stock
        Stock stock = new Stock("ProduitA", 5); // Exemple de stock initial pour le produit A

// Initialisation de CommandeController avec les instances de Commande et de Stock
        commandeController = new CommandeController(commande, stock);

    }

    private void validerCommande(String produit, String quantiteStr) {
        try {
            int quantite = Integer.parseInt(quantiteStr);
            if (quantite <= 0) {
                afficherMessageErreur("La quantité doit être un entier positif.");
                return;
            }
            // Création de la commande avec les informations saisies par l'utilisateur
            Commande commande = new Commande(0, produit, quantite, "en attente");

            // Utilisation de la commande et du stock existant pour initialiser CommandeController
            boolean commandeValidee = commandeController.traiterCommande(commande);

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
