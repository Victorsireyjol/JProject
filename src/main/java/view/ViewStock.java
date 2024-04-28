package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Client;
import model.Commande;
import model.Produit;

import java.util.List;
import java.util.Optional;

public class ViewStock {

    private  List<Produit> produitsenstock ;
    public ViewStock(List<Produit> produitsDisponibles) {

        this.produitsenstock = produitsDisponibles ;
    }

    ListView<String> StockListView = new ListView<>();
    ObservableList<String> StockList = FXCollections.observableArrayList();



    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stock des Produits");


        BorderPane root = new BorderPane();
        HBox topBox = new HBox();
        root.setTop(topBox);

for(Produit produit : produitsenstock) {

    StockList.add(produit.toString());

}

StockListView.setItems(StockList);
root.setCenter(StockListView);

        StockListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double clic pour modifier le stock
                String selectedProductString = StockListView.getSelectionModel().getSelectedItem();
                int selectedIndex = StockListView.getSelectionModel().getSelectedIndex();
                Produit selectedProduct = produitsenstock.get(selectedIndex);

                // Afficher une boîte de dialogue pour entrer la nouvelle quantité
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Modification de stock");
                dialog.setHeaderText("Modifier le stock pour " + selectedProduct.getNom());
                dialog.setContentText("Entrez la nouvelle quantité de stock:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(newStockStr -> {
                    try {
                        int newStock = Integer.parseInt(newStockStr);
                        selectedProduct.setQuantiteStock(newStock);
                        // Mettre à jour la vue
                        StockList.set(selectedIndex, selectedProduct.toString());
                    } catch (NumberFormatException e) {
                        // Gérer l'exception si le nombre n'est pas valide
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Entrée non valide");
                        alert.setContentText("Veuillez entrer un nombre valide.");
                        alert.showAndWait();
                    }
                });
            }
        });
        // Afficher la scène
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
