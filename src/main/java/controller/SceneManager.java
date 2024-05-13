package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.CommandesView;
import view.StockView;

import java.util.List;

public class SceneManager {
    private Stage stage;
    private CommandeController commandeController;  // Ajout d'une référence au contrôleur

    public SceneManager(Stage stage, CommandeController commandeController) {
        this.stage = stage;
        this.commandeController = commandeController;  // Initialisation du contrôleur
    }

    public void showCommandesView() {
        CommandesView view = new CommandesView(commandeController);  // Passage du contrôleur au constructeur
        stage.setScene(new Scene(view.render(), 400, 400));
        stage.show();
    }


    public void showStockView() {
        StockView stockView = new StockView(commandeController, stage);  // Assurez-vous de passer 'stage'
        Scene scene = new Scene(stockView.render(), 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void showInitialView() {
        CommandesView commandesView = new CommandesView(commandeController);
        Scene scene = new Scene(commandesView.render(), 400, 400);
        stage.setScene(scene);
        stage.show();
    }

//    public void showBonDeLivraisonView(List<BonDeLivraison> bons) {
//        BonDeLivraisonView bonDeLivraisonView = new BonDeLivraisonView(bons);
//        Scene scene = new Scene(bonDeLivraisonView.render(), 400, 400);
//        stage.setScene(scene);
//        stage.show();
//    }

//    public void showBonDeLivraisonView(List<BonDeLivraison> bons) {
//        BonDeLivraisonView bonDeLivraisonView = new BonDeLivraisonView();
//        bonDeLivraisonView.setBons(bons);  // Assurez-vous que BonDeLivraisonView a une méthode pour définir les bons
//        Scene scene = new Scene(bonDeLivraisonView.render(), 400, 400);
//        stage.setScene(scene);
//        stage.show();
//    }


    // Ajoutez d'autres méthodes pour gérer d'autres vues si nécessaire
}
