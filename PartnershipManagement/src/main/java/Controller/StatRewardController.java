
package Controller;

import entities.Reward;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.RewardService;
import tools.Myconnection;


public class StatRewardController implements Initializable {

    @FXML
    private BarChart<String, Integer> bar;
    int compA=0;
    int compS=0;
    RewardService us = new RewardService();
    @FXML
    private Label userP;
    Connection cnx;
    @FXML
    private Button ret;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series<String, Integer> artisteSeries = new XYChart.Series<>();
        artisteSeries.setName("100");

        XYChart.Series<String, Integer> simpleSeries = new XYChart.Series<>();
        simpleSeries.setName("200");

        try {
            List<Reward> users = us.recuperer();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getScore().equals("100")) {
                    compA=compA+1;
                    artisteSeries.getData().add(new XYChart.Data<>("100", compA));
                } else if (users.get(i).getScore().equals("200")) {
                    compS=compS+1;
                    simpleSeries.getData().add(new XYChart.Data<>("200", compS));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        bar.getData().addAll(artisteSeries, simpleSeries);



        try {
            Statement stmt = cnx.createStatement();
            String query = "SELECT u.nom, COUNT(*) AS ticket_count " +
                    "FROM ticket t " +
                    "JOIN user u ON t.user_id = u.id_user " +
                    "GROUP BY u.id_user " +
                    "ORDER BY ticket_count DESC " +
                    "LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String username = rs.getString("nom");
                int ticketCount = rs.getInt("ticket_count");
                //System.out.println(username + " a " + ticketCount + " tickets");
                userP.setText(username +" avec "+ Integer.toString(ticketCount)+" tickets ");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }










    }

    public StatRewardController() {
        cnx = Myconnection.getInstance().getCnx();
    }

    @FXML
    private void goBackHandler(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("displayReward.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
//
//
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayReward.fxml"));
        Parent root = loader.load();
//
//
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Display Users");
        stage.setScene(scene);
        stage.show();
//
//       }

    }}


