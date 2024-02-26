package services;
import entities.Reward;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.RService;

import tools.Myconnection;

public class RewardService implements RService<Reward> {

    Connection cnx;

    public RewardService() {
        cnx = Myconnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reward t) throws SQLException {

        String req = "INSERT INTO reward (title,description,image,score) VALUES(?,?,?,?)";
        //String req = "INSERT INTO yyy (nom,prenom) VALUES(?,?)";
        PreparedStatement stmt = cnx.prepareStatement(req);
        stmt.setString(1, t.getTitle());
        stmt.setString(2, t.getDescription());
        stmt.setString(3, t.getImage());
        stmt.setString(4, t.getScore());
        int result = stmt.executeUpdate();


        System.out.println(result + " enregistrement ajouté.");


    }

    public boolean existemail(String title) throws SQLException {
        boolean exist = false;
        String query = "SELECT * FROM reward WHERE title = ?";
        PreparedStatement ps = cnx.prepareStatement(query);
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            exist = true;

        }
        return exist;
    }


    @Override
    public void modifier(Reward t) throws SQLException {
        String req = "Update reward set title=?, description=?, score=?, image=? where id=?";
        PreparedStatement stmt = cnx.prepareStatement(req);
        stmt.setString(1, t.getTitle());
        stmt.setString(2, t.getDescription());
        stmt.setString(3, t.getImage());
        stmt.setString(4, t.getScore());
        stmt.setInt(5, t.getId());

        stmt.executeUpdate();

        System.out.println(" modification etablie!");
    }


    @Override
    public void supprimer(Reward t) throws SQLException {
        String req = "Delete from reward where id=?";
        PreparedStatement stmt = cnx.prepareStatement(req);
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();
        System.out.println(" suppression etablie!");


    }



    public List<Reward> rechercherParNom(String title) throws SQLException {
        List<Reward> partners = new ArrayList<>();
        String req = "SELECT * FROM reward WHERE (score='100' OR score='200') AND (title LIKE '%" + title + "%' OR description LIKE '%" + title + "%')";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        while(rs.next()){
            Reward p = new Reward();
            p.setId(rs.getInt("id"));
            p.setTitle(rs.getString("title"));
            p.setDescription(rs.getString("description"));
            p.setScore(rs.getString("score"));
            // p.setImage(rs.getString("image"));


            p.setImage(rs.getString("image"));


            partners.add(p);


        }




        return partners;
    }

    @Override
    public List<Reward> recuperer() throws SQLException  {
        List<Reward> rewards = new ArrayList<>();
        String req="select * from reward";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(req);
        while(rs.next()){
            Reward p = new Reward();
            p.setId(rs.getInt("id"));
            p.setTitle(rs.getString("title"));
            p.setDescription(rs.getString("description"));
            p.setScore(rs.getString("score"));
            // p.setImage(rs.getString("image"));


            p.setImage(rs.getString("image"));

            rewards.add(p);


        }




        return rewards;
    }

    public String getName(int id) throws SQLException {
        String req = "SELECT title FROM reward WHERE id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        String title = "";
        if (rs.next()) {
            title = rs.getString("title");
        }
        return title;
    }




    @Override
    public List<Reward> getAllData () throws SQLException {
        List<Reward> data = new ArrayList<>();
        String requete = "Select * From reward";
        Statement st = Myconnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Reward p = new Reward();
            p.setId(rs.getInt(1));
            p.setTitle(rs.getString("title"));
            p.setId(rs.getInt(1));
            p.setDescription(rs.getString("description"));
            p.setImage(rs.getString("image"));
            p.setScore(rs.getString("score"));

            data.add(p);


        }
        return data;
    }


}





