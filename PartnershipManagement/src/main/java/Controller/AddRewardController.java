
package Controller;

import entities.Reward;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.RewardService;


public class AddRewardController implements Initializable {

    @FXML
    private ChoiceBox<String> choices;

    @FXML
    private TextField descriptiontf;
    @FXML
    private TextField titletf;
    @FXML
    private Button ajouter;

    RewardService us= new RewardService();
    @FXML
    private Button annuler;
    @FXML
    private Button uploadImgBtn;

    private String imageData;
    @FXML
    private Text iscriL;
    @FXML
    private Button ret;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choices.getItems().add("100");
        choices.getItems().add("200");
        choices.getSelectionModel().select("100");





    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {


        String title=titletf.getText();
        String description= descriptiontf.getText();




            try {


                Reward p = new Reward();
                p.setTitle(titletf.getText());
                p.setDescription(descriptiontf.getText());
                p.setScore(choices.getValue());
                p.setImage(imageData);
                us.ajouter(p);



                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Reward Added!");
                alert.show();
            }

            catch (SQLException ex) {
                System.out.println("error" + ex.getMessage());
            }

        }


    @FXML
    private void annuler(ActionEvent event) {


        titletf.setText("");
        descriptiontf.setText("");
    }

    @FXML
    private void onUploadButtonClick(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            //imageData = Files.readAllBytes(selectedFile.toPath());
            imageData=selectedFile.getPath();
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
