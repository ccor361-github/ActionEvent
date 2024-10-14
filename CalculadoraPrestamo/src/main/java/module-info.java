module es.ieslosmontecillos.calculadoraprestamo {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.ieslosmontecillos.calculadoraprestamo to javafx.fxml;
    exports es.ieslosmontecillos.calculadoraprestamo;
}