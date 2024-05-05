package view;

import controller.CommandeController;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Produit;

public class StockView {
    private CommandeController controller;
    private Stage stage;

    public StockView(CommandeController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }
    public VBox render() {
        VBox layout = new VBox(10);
        Button backButton = new Button("Retour");
        backButton.setOnAction(e -> controller.showInitialView());  // Ici, adapter selon la méthode appropriée
        layout.getChildren().addAll(backButton);
        for (Produit produit : controller.getProduits()) {
            HBox productLine = new HBox(10);
            Label productName = new Label(produit.getNom() + " - Stock: " + produit.getQuantiteStock());
            TextField quantityField = new TextField();
            quantityField.setPromptText("Quantité à ajouter");
            Button addButton = new Button("Réapprovisionner");
            addButton.setOnAction(e -> {
                try {
                    int quantity = Integer.parseInt(quantityField.getText());
                    controller.reapprovisionnerProduit(produit.getNom(), quantity);
                    productName.setText(produit.getNom() + " - Stock: " + produit.getQuantiteStock());
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Entrez un nombre valide.");
                    alert.showAndWait();
                }
            });

            productLine.getChildren().addAll(productName, quantityField, addButton);
            layout.getChildren().add(productLine);

        }
        return layout;
    }
}
