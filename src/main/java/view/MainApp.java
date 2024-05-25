package view;

import controller.CommandeController;
import controller.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Client;
import model.Commande;
import model.Produit;

import java.util.ArrayList;
import java.util.Arrays;

public class



















MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialisation des clients
        Client client1 = new Client(1, "Client A");
        Client client2 = new Client(2, "Client B");

        // Initialisation des produits
        Produit produit1 = new Produit(1, "Produit 1", 100);
        Produit produit2 = new Produit(2, "Produit 2", 200);

        // Initialisation des listes
        ArrayList<Client> clients = new ArrayList<>(Arrays.asList(client1, client2));
        ArrayList<Produit> produits = new ArrayList<>(Arrays.asList(produit1, produit2));
        ArrayList<Commande> commandes = new ArrayList<>();  // Liste de commandes initialement vide

        // Création du contrôleur
        CommandeController commandeController = new CommandeController(clients, produits, commandes);

        // Configuration du gestionnaire de scènes
        SceneManager sceneManager = new SceneManager(primaryStage, commandeController);
        commandeController.setSceneManager(sceneManager);

        // Affichage de la vue initiale
        sceneManager.showInitialView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
