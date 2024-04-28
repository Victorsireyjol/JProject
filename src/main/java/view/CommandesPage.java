package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Client;
import model.Commande;
import model.Produit;

import java.util.List;

public class CommandesPage {

    private List<Client> clientsDisponibles;
    private List<Commande> commandesPasser;

    public CommandesPage(List<Client> clientsDisponibles ,List<Commande> commandes) {
        this.clientsDisponibles = clientsDisponibles;
        this.commandesPasser = commandes;
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Liste des commandes par client");

        BorderPane root = new BorderPane();
        HBox topBox = new HBox();
        root.setTop(topBox);

        // Ajouter une ComboBox contenant les noms des clients
        ComboBox<String> clientsComboBox = new ComboBox<>();
        ObservableList<String> nomsClients = FXCollections.observableArrayList();
        for (Client client : clientsDisponibles) {
            nomsClients.add(client.getNom());
        }
        clientsComboBox.setItems(nomsClients);
        topBox.getChildren().add(clientsComboBox);

        Button validerButton = new Button("Valider");
        validerButton.setOnAction(e -> {
            String clientSelectionne = clientsComboBox.getValue();
        });

        // ListView pour afficher les commandes
        ListView<String> commandesListView = new ListView<>();
        ObservableList<String> commandesList = FXCollections.observableArrayList();

        for (Commande commande : commandesPasser) {
            // Ajoutez la représentation textuelle de chaque commande à la liste observable
            commandesList.add(commande.toString()); // Assurez-vous que votre classe Commande a une méthode toString() appropriée
        }
        commandesListView.setItems(commandesList);
        root.setCenter(commandesListView);


        // Afficher la scène
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


}
