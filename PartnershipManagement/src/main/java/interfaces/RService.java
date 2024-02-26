package interfaces;
import entities.Reward;

import java.sql.SQLException;
import java.util.List;
public interface RService <Reward> {
    void ajouter(Reward t) throws SQLException;

    void modifier(Reward t) throws SQLException;

    void supprimer(Reward t) throws SQLException;

    List<entities.Reward> recuperer() throws SQLException;

    List <Reward> getAllData()throws SQLException;
}
