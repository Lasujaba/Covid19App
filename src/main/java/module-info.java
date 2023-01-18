module com.example.esoftproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires univocity.parsers;
    requires org.json;

    opens pt.ipp.isep.g58 to javafx.fxml;
    exports pt.ipp.isep.g58;
}