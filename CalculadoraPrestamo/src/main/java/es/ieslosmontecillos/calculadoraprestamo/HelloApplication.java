package es.ieslosmontecillos.calculadoraprestamo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de Préstamo");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label tasaInteresLabel = new Label("Tasa de Interés Anual:");
        grid.add(tasaInteresLabel, 0, 0);
        TextField tasaInteresField = new TextField();
        grid.add(tasaInteresField, 1, 0);

        Label numeroAniosLabel = new Label("Número de Años:");
        grid.add(numeroAniosLabel, 0, 1);
        TextField numeroAniosField = new TextField();
        grid.add(numeroAniosField, 1, 1);

        Label montoPrestamoLabel = new Label("Monto del Préstamo:");
        grid.add(montoPrestamoLabel, 0, 2);
        TextField montoPrestamoField = new TextField();
        grid.add(montoPrestamoField, 1, 2);

        Label pagoMensualLabel = new Label("Pago Mensual:");
        grid.add(pagoMensualLabel, 0, 3);
        TextField pagoMensualField = new TextField();
        pagoMensualField.setEditable(false);
        grid.add(pagoMensualField, 1, 3);

        Label pagoTotalLabel = new Label("Pago Total:");
        grid.add(pagoTotalLabel, 0, 4);
        TextField pagoTotalField = new TextField();
        pagoTotalField.setEditable(false);
        grid.add(pagoTotalField, 1, 4);

        Button calcularButton = new Button("Calcular");
        grid.add(calcularButton, 1, 5);

        calcularButton.setOnAction(event -> {
            try {
                double tasaAnual = Double.parseDouble(tasaInteresField.getText());
                int numeroAnios = Integer.parseInt(numeroAniosField.getText());
                double montoPrestamo = Double.parseDouble(montoPrestamoField.getText());

                double tasaMensual = tasaAnual / (100 * 12);

                double pagoMensual = (montoPrestamo * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -numeroAnios * 12));
                pagoMensualField.setText(String.format("€%.2f", pagoMensual));

                double pagoTotal = pagoMensual * numeroAnios * 12;
                pagoTotalField.setText(String.format("€%.2f", pagoTotal));

            } catch (NumberFormatException e) {
                mostrarError("Por favor, ingresa valores válidos.");
            }
        });

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
