module ubx.project.javarts {
    requires javafx.controls;
    requires javafx.fxml;


    opens ubx.project.javarts to javafx.fxml;
    exports ubx.project.javarts;
}