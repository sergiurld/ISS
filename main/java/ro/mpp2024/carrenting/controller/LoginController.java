package ro.mpp2024.carrenting.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.mpp2024.carrenting.HelloApplication;
import ro.mpp2024.carrenting.HelloController;
import ro.mpp2024.carrenting.domain.Admin;
import ro.mpp2024.carrenting.domain.Client;
import ro.mpp2024.carrenting.service.Service;

public class LoginController {
    public Button buttonLogin;
    public Button buttonCreateNewUserLogin;
    private Service service;
    @FXML
    private TextField usernameFieldLogin;
    @FXML
    private PasswordField passwordFieldLogin;
    @FXML
    private ChoiceBox<String> choiceBoxLogin;

    public void initialize() {
        ObservableList<String> roles = FXCollections.observableArrayList("Admin", "Client");
        choiceBoxLogin.setItems(roles);
    }
    public void handleLogin(ActionEvent actionEvent){
        try{
            String username =usernameFieldLogin.getText();
            String password =passwordFieldLogin.getText();
            String selectedRole = choiceBoxLogin.getValue();

            if ("Admin".equals(selectedRole)) {
                Admin a = service.getAdminByUsername(username,password);
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ro.mpp2024.carrenting/adminwindow.fxml");
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                AdminController adminController = fxmlLoader.getController();
                adminController.setService(service,a);
                stage.setTitle("Salut Adminule:"+a.getName());
                stage.show();

            } else if ("Client".equals(selectedRole)) {
                Client c = service.getClientByUsername(username,password);
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ro.mpp2024.carrenting/abonatwindow.fxml");
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                ClientController clientController = fxmlLoader.getController();
                clientController.setService(service,c);
                stage.setTitle("Salut Clientule:"+c.getName());
                stage.show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
