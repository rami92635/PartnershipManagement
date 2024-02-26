
package Controller;

import entities.Reward;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import services.RewardService;


public class EditRewardController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label email;
    @FXML
    private Label tel;
    @FXML
    private Label mdp;
    @FXML
    private Label role;
    @FXML
    private Button delb;
    @FXML
    private Button modb;

    private Reward reward;

    private RewardService  us=new RewardService();
    @FXML
    private ImageView pdp;
    @FXML
    private ImageView backbtn;
    @FXML
    private ImageView goBackBtn;
    @FXML
    private AnchorPane ticketListPane;
    @FXML
    private Button ret;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {



        try {






            //Show ticket list
            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource(""));
            AnchorPane content = contentLoader.load();
            ticketListPane.getChildren().setAll(content);
        } catch (IOException ex) {
            Logger.getLogger(EditPartnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    void sendreward(Reward u) {

        reward=u;
        nom.setText(reward.getTitle());
        email.setText(reward.getDescription());

        role.setText(reward.getScore());
        //ByteArrayInputStream inputStream = new ByteArrayInputStream(user.getImage());
        File imageFile = new File(reward.getImage());
        Image image = new Image(imageFile.toURI().toString());
        // Image image = new Image(inputStream);
        // pdp.setImage(image);
        pdp.setImage(image);

    }

    @FXML
    private void delete(ActionEvent event) throws IOException  {
        try {
            us.supprimer(reward);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText(null);
            alert.setContentText("Reward Deleted!");
            alert.show();
            if(!(MainPageRewardController.RewardConnected.getScore().equals("100"))){


                FXMLLoader loader = new FXMLLoader(getClass().getResource("displayReward.fxml"));
                Parent root = loader.load();


                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();

            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }






    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateReward.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Reward");
        stage.setScene(scene);
        stage.show();

        UpdateRewardController controller = loader.getController();
        controller.sendreward(reward);



    }

    @FXML
    private void back(MouseEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayReward.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Display Rewards");
        stage.setScene(scene);
        stage.show();









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
        stage.setTitle("Display Rewards");
        stage.setScene(scene);
        stage.show();
//
//       }


    }
    @FXML
    private void goBackHandler(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainContainer.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //Load CSS
            String css = this.getClass().getResource("../assets/css/app.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }





}
