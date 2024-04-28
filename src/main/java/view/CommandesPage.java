package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Client;
import model.Commande;

import java.util.List;

public class CommandesPage {

    private List<Client> clientsDisponibles;
    private ListView<String> commandesListView;

    public CommandesPage(List<Client> clientsDisponibles ,List<Commande> commandes) {
        this.clientsDisponibles = clientsDisponibles;
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


        // Affichage à l'aide d'une boucle forEach

        // Afficher la scène
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    }
