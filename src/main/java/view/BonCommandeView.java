package view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.BonCommande;

import java.util.List;

public class BonCommandeView {

    public void afficher(List<BonCommande> bonsDeCommande) {
        Stage stage = new Stage();
        ListView<BonCommande> listView = new ListView<>();
        ObservableList<BonCommande> items = FXCollections.observableArrayList(bonsDeCommande);
        listView.setItems(items);

        Scene scene = new Scene(listView, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Bons de Commande");
        stage.show();
    }
}
