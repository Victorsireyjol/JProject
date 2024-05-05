package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.BonDeLivraison;
import java.util.List;

public class BonDeLivraisonView {
    private ObservableList<BonDeLivraison> bonData;

    public BonDeLivraisonView(List<BonDeLivraison> bons) {
        this.bonData = FXCollections.observableArrayList(bons);
    }

    public VBox render() {
        VBox layout = new VBox(10);

        TableView<BonDeLivraison> table = new TableView<>();
        table.setItems(bonData);

        TableColumn<BonDeLivraison, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<BonDeLivraison, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<BonDeLivraison, String> clientDetailsColumn = new TableColumn<>("Client Details");
        clientDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("detailsClient"));

        TableColumn<BonDeLivraison, String> productDetailsColumn = new TableColumn<>("Product Details");
        productDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("detailsProduit"));

        table.getColumns().addAll(idColumn, dateColumn, clientDetailsColumn, productDetailsColumn);

        layout.getChildren().add(table);
        return layout;
    }
}
