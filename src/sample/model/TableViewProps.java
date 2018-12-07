package sample.model;

import javafx.beans.property.SimpleStringProperty;

// this class is used to work with TableView
public class TableViewProps {
    private final SimpleStringProperty type;
    private final SimpleStringProperty amount;

    public TableViewProps(String type, String amount) {
        this.type = new SimpleStringProperty(type);
        this.amount = new SimpleStringProperty(amount);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }
}
