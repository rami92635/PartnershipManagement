package interfaces;
import entities.Partner;

import java.sql.SQLException;
import java.util.List;
public interface PService <Partner> {
    void ajouter(Partner t) throws SQLException;

    void modifier(Partner t) throws SQLException;

    void supprimer(Partner t) throws SQLException;

    List<entities.Partner> recuperer() throws SQLException;

    List <Partner> getAllData()throws SQLException;
}
