
package Controller;

import entities.Partner;
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
import services.PartnerService;


public class DisplayPartner implements Initializable {

    @FXML
    private GridPane grid;
    PartnerService us = new PartnerService();
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


            List<Partner> partners = us.recuperer();
            int row = 0;
            int column = 0;



            System.out.println(partners);


            for (int i = 0; i < partners.size(); i++){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/partner.fxml"));
                AnchorPane pane = loader.load();
                PartnerController controller = loader.getController();
                if(partners.get(i).getReward_type().equals("Money")||partners.get(i).getReward_type().equals("Discount")){
                    Partner partner=partners.get(i);

                    controller.setPartner(partner);


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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddPartner.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ajouter utilisateur");
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
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
//
//       }

    }



    @FXML
    private void chercherkey(KeyEvent event) throws IOException {
        String name = cher.getText();
        try {
            List<Partner> users_ch = us.rechercherParNom(name);
            grid.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < users_ch.size(); i++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/partner.fxml"));
                AnchorPane pane = loader.load();
                PartnerController controller = loader.getController();
                if(users_ch.get(i).getReward_type().equals("Money") || users_ch.get(i).getReward_type().equals("Discount")){
                    Partner partner=users_ch.get(i);
                    controller.setPartner(partner);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/statPartner.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Statistique");
        stage.setScene(scene);
        stage.show();
    }




}
