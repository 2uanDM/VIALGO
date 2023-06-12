module sortingvisualizer {
    requires javafx.controls;
    requires javafx.fxml;

    opens sortingvisualizer to javafx.fxml;
    exports sortingvisualizer;
}
