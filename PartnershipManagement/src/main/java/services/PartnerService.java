package services;
import entities.Partner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.PService;

import tools.Myconnection;

public class PartnerService implements PService<Partner> {

    Connection cnx;

    public PartnerService() {
        cnx = Myconnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Partner t) throws SQLException {

        String req = "INSERT INTO partner (name,email,reward_type,image) VALUES(?,?,?,?)";
        //String req = "INSERT INTO yyy (nom,prenom) VALUES(?,?)";
        PreparedStatement stmt = cnx.prepareStatement(req);
        stmt.setString(1, t.getName());
        stmt.setString(2, t.getEmail());
        stmt.setString(3, t.getReward_type());
        stmt.setString(4, t.getImage());
        int result = stmt.executeUpdate();


        System.out.println(result + " enregistrement ajouté.");


    }

    public boolean existemail(String email) throws SQLException {
        boolean exist = false;
        String query = "SELECT * FROM partner WHERE email = ?";
        PreparedStatement ps = cnx.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            exist = true;

        }
        return exist;
    }


    @Override
    public void modifier(Partner t) throws SQLException {
        String req = "Update partner set name=?, email=?, reward_type=?, image=? where id=?";
        PreparedStatement stmt = cnx.prepareStatement(req);
        stmt.setString(1, t.getName());
        stmt.setString(2, t.getEmail());
        stmt.setString(3, t.getReward_type());
        stmt.setString(4, t.getImage());
        stmt.setInt(5, t.getId());

        stmt.executeUpdate();

        System.out.println(" modification etablie!");
    }


    @Override
    public void supprimer(Partner t) throws SQLException {
        String req = "Delete from partner where id=?";
        PreparedStatement stmt = cnx.prepareStatement(req);
        stmt.setInt(1, t.getId());
        stmt.executeUpdate();
        System.out.println(" suppression etablie!");


    }



    public List<Partner> rechercherParNom(String name) throws SQLException {
        List<Partner> partners = new ArrayList<>();
        String req = "SELECT * FROM partner WHERE (reward_type='Money' OR reward_type='Discount') AND (name LIKE '%" + name + "%' OR email LIKE '%" + name + "%')";
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        while(rs.next()){
            Partner p = new Partner();
            p.setId(rs.getInt("id"));
            p.setEmail(rs.getString("email"));
            p.setName(rs.getString("name"));
            p.setReward_type(rs.getString("reward_type"));
            // p.setImage(rs.getString("image"));


            p.setImage(rs.getString("image"));


            partners.add(p);


        }




        return partners;
    }

    @Override
    public List<Partner> recuperer() throws SQLException  {
        List<Partner> partners = new ArrayList<>();
        String req="select * from partner";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(req);
        while(rs.next()){
            Partner p = new Partner();
            p.setId(rs.getInt("id"));
            p.setEmail(rs.getString("email"));
            p.setName(rs.getString("name"));
            p.setReward_type(rs.getString("reward_type"));
            // p.setImage(rs.getString("image"));


            p.setImage(rs.getString("image"));

            partners.add(p);


        }




        return partners;
    }

    public String getName(int id) throws SQLException {
        String req = "SELECT name FROM partner WHERE id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        String name = "";
        if (rs.next()) {
            name = rs.getString("name");
        }
        return name;
    }




    @Override
    public List<Partner> getAllData () throws SQLException {
        List<Partner> data = new ArrayList<>();
        String requete = "Select * From partner";
        Statement st = Myconnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Partner p = new Partner();
            p.setId(rs.getInt(1));
            p.setName(rs.getString("nom"));
            p.setId(rs.getInt(1));
            p.setName(rs.getString("nom"));
            p.setEmail(rs.getString("email"));
            p.setReward_type(rs.getString("reward_type"));
            p.setImage(rs.getString("image"));

            data.add(p);


        }
        return data;
    }


}





