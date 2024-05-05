package view;

import controller.SceneManager;
import controller.CommandeController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Client;
import model.Produit;
import model.Commande;
import java.util.ArrayList;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Produit> produits = new ArrayList<>();
        ArrayList<Commande> commandes = new ArrayList<>();  // Création d'une liste vide pour les commandes

        clients.add(new Client("Client 1"));
        produits.add(new Produit("Produit A", 2,50));
        produits.add(new Produit("Produit B", 2,50));

        // Création du contrôleur avec des données initiales
        CommandeController commandeController = new CommandeController(clients, produits, commandes);

        primaryStage.setTitle("Gestion des Commandes et Stocks");
        SceneManager sceneManager = new SceneManager(primaryStage, commandeController);

        // Assurez-vous que SceneManager est passé à CommandeController
        commandeController.setSceneManager(sceneManager);
        sceneManager.showCommandesView();  // Affiche la vue initiale
    }

    public static void main(String[] args) {
        launch(args);
    }
}
