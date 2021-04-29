package hu.nive.ujratervezes.jurassic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection(){
            try {
                return DriverManager.getConnection(dbUrl,dbUser,dbPassword);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    public List<String> checkOverpopulation(){
        List<String> overpopulatedBreeds = new ArrayList<>();
        String SQL = "SELECT breed FROM dinosaur WHERE expected < actual ORDER by breed ASC";
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(SQL);
            if (rs.next()) {
                do {
                    overpopulatedBreeds.add(rs.getString(1));
                } while (rs.next());
            }
            return overpopulatedBreeds;
        }
        catch (SQLException e) {
            return overpopulatedBreeds;
        }
    }
}
