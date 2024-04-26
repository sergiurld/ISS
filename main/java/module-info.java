module ro.mpp2024.carrenting {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.logging.log4j;


    opens ro.mpp2024.carrenting to javafx.fxml;
    exports ro.mpp2024.carrenting;
}