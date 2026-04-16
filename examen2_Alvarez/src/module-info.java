module examen2_Alvarez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens co.edu.poli.examen2_Alvarez.controlador to javafx.fxml;

    exports co.edu.poli.examen2_Alvarez.controlador;
    exports co.edu.poli.examen2_Alvarez.modelo;
    exports co.edu.poli.examen2_Alvarez.servicios;
    exports co.edu.poli.examen2_Alvarez.vista;
}
