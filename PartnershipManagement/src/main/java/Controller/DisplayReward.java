
package Controller;

import entities.Reward;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.RewardService;


public class DisplayReward implements Initializable {

    @FXML
    private GridPane grid;
    RewardService us = new RewardService();
    @FXML
    private Button ajouttbn;
    @FXML
    private Button ret;
    @FXML
    private Button stattbn;
    @FXML
    private TextField cher;
    @FXML
    private Button stat;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {


            List<Reward> rewards = us.recuperer();
            int row = 0;
            int column = 0;


            System.out.println(rewards);


            for (int i = 0; i < rewards.size(); i++) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/reward.fxml"));
                AnchorPane pane = loader.load();
                RewardController controller = loader.getController();
                if (rewards.get(i).getScore().equals("100") || rewards.get(i).getScore().equals("200")) {
                    Reward reward = rewards.get(i);

                    controller.setReward(reward);


                    grid.add(pane, column, row);
                    row++;
                    if (column > 0) {
                        column = 0;
                        row++;
                    }

                }
            }


        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());


        }

    }


    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddReward.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Reward");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayPartner.fxml"));
        Parent root = loader.load();
//
//
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Partners");
        stage.setScene(scene);
        stage.show();
//
//       }

    }


    @FXML
    private void chercherkey(KeyEvent event) throws IOException {
        String title = cher.getText();
        try {
            List<Reward> users_ch = us.rechercherParNom(title);
            grid.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < users_ch.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/reward.fxml"));
                AnchorPane pane = loader.load();
                RewardController controller = loader.getController();
                if (users_ch.get(i).getScore().equals("100") || users_ch.get(i).getScore().equals("200")) {
                    Reward reward = users_ch.get(i);
                    controller.setReward(reward);
                    grid.add(pane, column, row);
                    row++;
                    if (column > 0) {
                        column = 0;
                        row++;
                    }
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @FXML
    private void stats(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/statReward.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Statistics");
        stage.setScene(scene);
        stage.show();
    }


}
