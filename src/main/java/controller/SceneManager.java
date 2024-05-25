package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import view.CommandesView;
import view.StockView;
import view.BonCommandeView;

public class SceneManager {
    private Stage stage;
    private CommandeController commandeController;

    public SceneManager(Stage stage, CommandeController commandeController) {
        this.stage = stage;
        this.commandeController = commandeController;
    }

    public void showCommandesView() {
        CommandesView commandesView = new CommandesView(commandeController);
        stage.setScene(new Scene(commandesView.render(), 800, 600));
        stage.show();
    }

    public void showStockView() {
        StockView stockView = new StockView(commandeController, stage);
        stage.setScene(new Scene(stockView.render(), 800, 600));
        stage.show();
    }

    public void afficherMessage(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void showInitialView() {
        showCommandesView();
    }
}
