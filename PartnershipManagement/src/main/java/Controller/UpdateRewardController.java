
package Controller;

import entities.Reward;
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
import services.RewardService;


public class UpdateRewardController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextField description;

    @FXML
    private ChoiceBox<String> score;
    @FXML
    private Button modb;
    @FXML
    private Button annb;
    private Reward reward;
    private Reward reward_test;
    RewardService us = new RewardService();
    @FXML
    private ImageView pdp;
    @FXML
    private Button upbtn;

    private String imageData;
    @FXML
    private Button ret;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        score.getItems().add("100");
        score.getItems().add("200");
        score.getSelectionModel().select("100");


    }


    public void sendreward(Reward p) {
        reward_test = p;
        reward = p;
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(user.getImage());
//       Image image = new Image(inputStream);

        File imageFile = new File(reward.getImage());
        Image image = new Image(imageFile.toURI().toString());
        pdp.setImage(image);


    }


    @FXML
    private void modifier(ActionEvent event) throws SQLException, NumberFormatException, IOException {


        if (!title.getText().isEmpty()) {
            reward.setDescription(title.getText());
        }

        if (!description.getText().isEmpty()) {
            reward.setTitle(description.getText());
        }


        if (score.getValue() != null) {
            reward.setScore(score.getValue());
        }

        if (imageData != null) {
            reward.setImage(imageData);

        }
        us.modifier(reward);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);
        alert.setContentText("Successful Update !!");
        alert.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayReward.fxml"));
        Parent root = loader.load();
        EditRewardController controller = loader.getController();
        controller.sendreward(reward);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayReward.fxml"));
        Parent root = loader.load();
//
//
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Affiche Users");
        stage.setScene(scene);
        stage.show();
//
//       }


    }

}
