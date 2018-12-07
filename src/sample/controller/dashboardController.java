package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.model.Expense;
import sample.model.TableViewProps;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    private int total;

    private static DecimalFormat df2 = new DecimalFormat(".##");

    @FXML
    private Button add;

    @FXML
    private PieChart chart;

    @FXML
    private Label caption;

    @FXML
    private TableView<TableViewProps> table;

    @FXML
    private TableColumn<TableViewProps,String> type;

    @FXML
    private TableColumn<TableViewProps,String> amount;

    // when user click on addExpense send user to addExpense page
    @FXML private void addExpense(ActionEvent e) {
        Stage stage;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/sample/view/addExpense.fxml").toURI().toURL());
            root = loader.load();
            stage = (Stage) add.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }



    @FXML private void showPercentage(MouseEvent e) {

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    caption.setTranslateX(event.getSceneX()-75);
                    caption.setTranslateY(event.getSceneY()-60);
                    caption.setText(String.valueOf(df2.format(data.getPieValue()/total*100)) + "%");
                    focusRow(data.getName());
                }
            });
        }
    }

    private void focusRow(String name) {
        if (name.equals("Rent")) {
            table.requestFocus();
            table.getSelectionModel().select(0);
        } else if (name.equals("Utilities")) {
            table.requestFocus();
            table.getSelectionModel().select(1);
        } else if (name.equals("Groceries")) {
            table.requestFocus();
            table.getSelectionModel().select(2);
        } else if (name.equals("Eating Out")) {
            table.requestFocus();
            table.getSelectionModel().select(3);
        } else if (name.equals("Going Out")) {
            table.requestFocus();
            table.getSelectionModel().select(4);
        } else if (name.equals("Gas")) {
            table.requestFocus();
            table.getSelectionModel().select(5);
        } else {
            table.requestFocus();
            table.getSelectionModel().select(6);
        }
    }

    private int calculateTotal(Expense e) {
        return e.getRent() + e.getUtilities() + e.getGroceries() + e.getEatingOut() + e.getGoingOut() + e.getGas() + e.getCoffeeTea();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 15 arial;");

        Expense e = new Expense();
        e = e.getExpense();
        this.total = this.calculateTotal(e);
        System.out.println(e.toString());


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Rent", e.getRent()),
                new PieChart.Data("Utilities", e.getUtilities()),
                new PieChart.Data("Groceries", e.getGroceries()),
                new PieChart.Data("Eating Out", e.getEatingOut()),
                new PieChart.Data("Going Out", e.getGoingOut()),
                new PieChart.Data("Gas", e.getGas()),
                new PieChart.Data("Coffee Shop", e.getCoffeeTea()));
        chart.setData(pieChartData);
        chart.setPrefSize(343,343);
        chart.setLabelsVisible(false);
        chart.setLegendSide(Side.LEFT);

        type.setCellValueFactory(new PropertyValueFactory<TableViewProps, String>("type"));
        amount.setCellValueFactory(new PropertyValueFactory<TableViewProps, String>("amount"));
        List<TableViewProps> list = new ArrayList<>();
        list.add(new TableViewProps("Rent", "$"+String.valueOf(e.getRent())));
        list.add(new TableViewProps("Utilities", "$"+String.valueOf(e.getUtilities())));
        list.add(new TableViewProps("Groceries", "$"+String.valueOf(e.getGroceries())));
        list.add(new TableViewProps("Eating Out", "$"+String.valueOf(e.getEatingOut())));
        list.add(new TableViewProps("Going Out", "$"+String.valueOf(e.getGoingOut())));
        list.add(new TableViewProps("Gas", "$"+String.valueOf(e.getGas())));
        list.add(new TableViewProps("Coffee Shop", "$"+String.valueOf(e.getCoffeeTea())));
        list.add(new TableViewProps("TOTAL SPENT", "$"+String.valueOf(this.total)));
        table.getItems().setAll(list);
    }

}
