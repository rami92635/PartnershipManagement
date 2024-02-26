
package Controller;

import entities.Partner;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.PartnerService;


public class MainPageController implements Initializable {

    @FXML
    private Button cnbt;
    @FXML
    private Button cnbt1;
    @FXML

    private TextField email;
    @FXML
    private TextField PartnerName;


    public static Partner PartnerConnected;
    PartnerService us= new PartnerService();
    @FXML
    private ImageView logo;
    @FXML
    private Text slogan;
    @FXML
    private Text bien;


    @Override
    public void initialize(URL url, ResourceBundle rb) {


        RotateTransition rotate = new RotateTransition(Duration.seconds(0.5), bien);
        rotate.setByAngle(360);
        rotate.setCycleCount(3);
        rotate.play();



        /*TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), slogan);
        translateTransition.setFromX(-slogan.getBoundsInParent().getWidth());
        translateTransition.setToX(logo.getBoundsInParent().getWidth());
        translateTransition.setCycleCount(Timeline.INDEFINITE);
        translateTransition.play();*/




    }

    @FXML
    private void connect(ActionEvent event) throws SQLException, IOException {

        if (email.getText().isEmpty()||PartnerName.getText().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("champ vide");
            alert.setHeaderText(null);
            alert.setContentText("Fill the Fields!");
            alert.show();
        }



        else if (!email.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email non valide");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email!");
            alert.show();  }

        else {
            Boolean verif =false;
            List<Partner> users = us.recuperer();

            for (int i = 0; i < users.size(); i++){

                if (users.get(i).getEmail().equals(email.getText()))
                {
                    PartnerConnected=users.get(i);
                    verif=true;
                    break;
                }
            }


            if (verif==true){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setHeaderText(null);
                alert.setContentText("Welcome"+" "+PartnerConnected.getName());
                alert.show();



            }
            else{

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText(null);
                alert.setContentText("User Not Found!");
                alert.show();

            }


            if(PartnerConnected != null){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayPartner.fxml"));
                Parent root = loader.load();


                Scene scene = new Scene(root);
                //Load CSS


                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("SnapGreen");
                stage.setScene(scene);
                stage.show();


            }
        }

    }
















    @FXML
    private void inscrire(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayReward.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rewards");
        stage.setScene(scene);
        stage.show();







    }

    @FXML
    private void mdp_ob(MouseEvent event) {
    }

    @FXML
    private void passwrd(ActionEvent event) throws IOException {



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/email_check.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("changement de mot de passe");
        stage.setScene(scene);
        stage.show();


    }











}

