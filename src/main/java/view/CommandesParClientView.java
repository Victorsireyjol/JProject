package view;

import controller.CommandeController;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Client;
import model.Commande;

public class CommandesParClientView {
    private CommandeController controller;

    public CommandesParClientView(CommandeController controller) {
        this.controller = controller;
    }

    public void display(Client client) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        ListView<Commande> listView = new ListView<>();
        listView.setItems(controller.getCommandesParClient(client));
        listView.setCellFactory(lv -> new ListCell<Commande>() {
            @Override
            protected void updateItem(Commande commande, boolean empty) {
                super.updateItem(commande, empty);
                if (commande == null || empty) {
                    setText(null);
                } else {
                    setText(commande.getProduit().getNom() + " - Prix du produit : "+ commande.getProduit().StringPrix() +  " - Quantité: " + commande.getQuantite() +" - Prix Commande : "+ commande.StringPrix() +" - Statut: " + commande.getStatut());
                }
            }
        });

        layout.getChildren().add(listView);
        Scene scene = new Scene(layout, 400, 400);
        stage.setTitle("Commandes pour " + client.getNom());
        stage.setScene(scene);
        stage.show();
    }
}
