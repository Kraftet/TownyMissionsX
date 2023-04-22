package pl.kraftet.townymissions.stats;

import org.bukkit.configuration.Configuration;
import pl.kraftet.townymissions.TownyMissions;
import pl.kraftet.townymissions.database.models.TownyMissionsDB;

import java.sql.SQLException;

public class BestNations {
    private static TownyMissions plugin;
    public BestNations(TownyMissions plugin) {
        this.plugin = plugin;
    }
    private static Configuration config = plugin.getConfig();

    public String firstTownByMostCompleted;
    public String secondTownByMostCompleted;
    public String thirdTownByMostCompleted;
    public String fourthTownByMostCompleted;
    public String fifthTownByMostCompleted;

    public String getFirstTownByMostCompleted() {
        return firstTownByMostCompleted;
    }

    public void setFirstTownByMostCompleted(String firstTownByMostCompleted) {
        this.firstTownByMostCompleted = firstTownByMostCompleted;
    }

    public String getSecondTownByMostCompleted() {
        return secondTownByMostCompleted;
    }

    public void setSecondTownByMostCompleted(String secondTownByMostCompleted) {
        this.secondTownByMostCompleted = secondTownByMostCompleted;
    }

    public String getThirdTownByMostCompleted() {
        return thirdTownByMostCompleted;
    }

    public void setThirdTownByMostCompleted(String thirdTownByMostCompleted) {
        this.thirdTownByMostCompleted = thirdTownByMostCompleted;
    }

    public String getFourthTownByMostCompleted() {
        return fourthTownByMostCompleted;
    }

    public void setFourthTownByMostCompleted(String fourthTownByMostCompleted) {
        this.fourthTownByMostCompleted = fourthTownByMostCompleted;
    }

    public String getFifthTownByMostCompleted() {
        return fifthTownByMostCompleted;
    }

    public void setFifthTownByMostCompleted(String fifthTownByMostCompleted) {
        this.fifthTownByMostCompleted = fifthTownByMostCompleted;
    }

    //    public void getByMostCompleted() throws SQLException {
//        TownyMissionsDB stats = plugin.getDatabase().getNationStats();
//
//    }
}
