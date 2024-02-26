
package Controller;

import entities.Partner;
import static java.lang.ModuleLayer.Controller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.PartnerService;


public class UpdatePartnerController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField email;
    @FXML
    private ChoiceBox<String> reward_type;
    @FXML
    private Button modb;
    @FXML
    private Button annb;
    private Partner partner;
    private Partner partner_test;
    PartnerService us = new PartnerService();
    @FXML
    private ImageView pdp;
    @FXML
    private Button upbtn;

    private String imageData;
    @FXML
    private Button ret;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        reward_type.getItems().add("Money");
        reward_type.getItems().add("Discount");
        reward_type.getSelectionModel().select("Money");


    }


    public void sendpartner(Partner p) {
        partner_test = p;
        partner = p;
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(user.getImage());
//       Image image = new Image(inputStream);

        File imageFile = new File(partner.getImage());
        Image image = new Image(imageFile.toURI().toString());
        pdp.setImage(image);


    }


    @FXML
    private void modifier(ActionEvent event) throws SQLException, NumberFormatException, IOException {


        if (!email.getText().isEmpty()) {
            partner.setEmail(email.getText());
        }

        if (!name.getText().isEmpty()) {
            partner.setName(name.getText());
        }


        if (reward_type.getValue() != null) {
            partner.setReward_type(reward_type.getValue());
        }

        if (imageData != null) {
            partner.setImage(imageData);

        }
        us.modifier(partner);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);
        alert.setContentText("Successful Update !!");
        alert.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayPartner.fxml"));
        Parent root = loader.load();
        EditPartnerController controller = loader.getController();
        controller.sendpartner(partner);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Edit");
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void reset(ActionEvent event) {
    }


    @FXML
    private void uploadImgBtn(ActionEvent event) {


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            //imageData = Files.readAllBytes(selectedFile.toPath());
            imageData = selectedFile.getPath();
        }


    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
//       if (LoginController.UserConnected.getRole().equals("Admin")){
//
//       FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheUser.fxml"));
//            Parent root = loader.load();
//
//
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setTitle("Affiche Users");
//        stage.setScene(scene);
//        stage.show();
//
//       }
//
//       else if(LoginController.UserConnected==null){
//
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayPartner.fxml"));
        Parent root = loader.load();
//
//
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Display Partners");
        stage.setScene(scene);
        stage.show();
//
//       }


    }

}
