package pl.kraftet.townymissions.databases;

import org.bukkit.entity.Player;
import pl.kraftet.townymissions.guis.townmissiongui.TownMissionGUI;
import pl.kraftet.townymissions.missions.TownMission;
import pl.kraftet.townymissions.databases.models.NationsMissionsModel;
import pl.kraftet.townymissions.guis.nationmission.NationMissionGUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Database {
    private Connection connection;

    public Connection getConnection() throws SQLException {

        if(connection != null){
            return connection;
        }

        String url = "jdbc:mysql://localhost/towny_missions";
        String user = "root";
        String password = "";

        this.connection = DriverManager.getConnection(url, user, password);
        System.out.println(("Connected to the database"));

        return this.connection;
    }

    public void initializeDatabase() throws SQLException {
        Statement statement = getConnection().createStatement();
//        String sql = "CREATE TABLE IF NOT EXISTS towny_missions(nation String primary key, current_amount long, required_amount long, mission_type String, uuid varchar(36), stored long, completed int)";
        String sql = "CREATE TABLE IF NOT EXISTS towny_missions (nation VARCHAR(255) PRIMARY KEY, current_amount BIGINT, required_amount BIGINT, mission_type VARCHAR(255), uuid VARCHAR(36), stored BIGINT, completed INT)";

        statement.execute(sql);
        statement.close();
    }

    public NationsMissionsModel findNationMissionByPlayer(String uuid) throws SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "SELECT * FROM towny_missions WHERE uuid = " + uuid;
        ResultSet results = statement.executeQuery(sql);

        if (results.next()) {
            String nation = results.getString("nation");
            long current_ammount = results.getLong("current_amount");
            long required_ammount = results.getLong("required_amount");
            String mission_type = results.getString("mission_type");
            long stored = results.getLong("stored");
            int completed = results.getInt("completed");

            NationsMissionsModel nationsMissionsDb = new NationsMissionsModel(nation, current_ammount, required_ammount, mission_type, uuid, stored, completed);

            statement.close();

            return nationsMissionsDb;
        }

        statement.close();

        return null;
    }

    public NationsMissionsModel updateAmount(int amount, String nation, Player player) throws SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "UPDATE towny_missions SET current_amount = " + amount + " WHERE nation = " + nation;
        ResultSet results = statement.executeQuery(sql);

        checkAmount(amount, nation, player);

        statement.close();
        return null;
    }

    private NationsMissionsModel checkAmount(int amount, String nation, Player player) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM towny_missions WHERE nation = ? AND current_amount >= required_amount");
        statement.setString(1, nation);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            // current_amount is greater than or equal to required_amount for the specified nation
            if (result.getLong("current_amount") >= result.getLong("required_amount")) {
                long excessive_amount = result.getLong("current_amount") - result.getLong("required_amount");
                TownMission.finish(player, excessive_amount);
            }
        } else {
            // current_amount is less than required_amount for the specified nation
        }

        result.close();
        statement.close();

        statement.close();
        return null;
    }

    public String getMissionType(String nation) throws SQLException {
        String sql = "SELECT mission_type FROM towny_missions WHERE nation = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, nation);
        ResultSet result = statement.executeQuery();
        String missionType = null;
        if (result.next()) {
            missionType = result.getString("mission_type");
        }
        result.close();
        statement.close();
        return missionType;
    }

    public NationsMissionsModel updatePlayerStats(int amount, UUID uuid) throws SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "UPDATE towny_missions SET stored = " + amount + " WHERE uuid = " + uuid;
        ResultSet results = statement.executeQuery(sql);

        statement.close();
        return null;
    }

    public List<String> getPlayersByNation(String nation) throws SQLException {
        String sql = "SELECT uuid FROM towny_missions WHERE nation = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, nation);
        ResultSet result = statement.executeQuery();
        List<String> players = new ArrayList<>();
        while (result.next()) {
            players.add(result.getString("uuid"));
        }
        result.close();
        statement.close();
        return players;
    }

    public long getStoredAmountForPlayer(String playerUuid) throws SQLException {
        String sql = "SELECT stored FROM towny_missions WHERE uuid = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, playerUuid);
        ResultSet result = statement.executeQuery();
        long storedAmount = 0;
        if (result.next()) {
            storedAmount = result.getLong("stored");
        }
        result.close();
        statement.close();
        return storedAmount;
    }

    public long getRequiredAmountForPlayer(String playerUuid) throws SQLException {
        String sql = "SELECT required_amount FROM towny_missions WHERE uuid = ?;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, playerUuid);
        ResultSet result = statement.executeQuery();
        long requiredAmount = 0;
        if (result.next()) {
            requiredAmount = result.getLong("required_amount");
        }
        result.close();
        statement.close();
        return requiredAmount;
    }

    public NationsMissionsModel getNationStats() throws SQLException {
        Statement statement = getConnection().createStatement();
        String sql = "SELECT * FROM towny_missions";
        ResultSet results = statement.executeQuery(sql);

        if (results.next()) {
            String nation = results.getString("nation");
            long current_ammount = results.getLong("current_amount");
            long required_ammount = results.getLong("required_amount");
            String mission_type = results.getString("mission_type");
            String uuid = results.getString("uuid");
            long stored = results.getLong("stored");
            int completed = results.getInt("completed");

            NationsMissionsModel nationsMissionsDb = new NationsMissionsModel(nation, current_ammount, required_ammount, mission_type, uuid, stored, completed);

            statement.close();

            return nationsMissionsDb;
        }

        statement.close();

        return null;
    }

    public NationsMissionsModel getNationsByTheMostCompleted() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                "SELECT nation, COUNT(*) AS completed_count " +
                        "FROM towny_missions " +
                        "WHERE completed = 1 " +
                        "GROUP BY nation " +
                        "ORDER BY completed_count DESC " +
                        "LIMIT 5"
        );

        while (result.next()) {
            int position = 1;
            String nation = result.getString("nation");
            int completedCount = result.getInt("completed_count");

            NationMissionGUI.missionsTopTowns(nation, completedCount, position);

            position++;
        }

        statement.close();
        return null;
    }

    public NationsMissionsModel getTownsByTheMostCompleted() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                "SELECT nation, COUNT(*) AS completed_count " +
                        "FROM towny_missions " +
                        "WHERE completed = 1 " +
                        "GROUP BY town " +
                        "ORDER BY completed_count DESC " +
                        "LIMIT 5"
        );

        while (result.next()) {
            int position = 1;
            String town = result.getString("town");
            int completedCount = result.getInt("completed_count");

            TownMissionGUI.missionsTopTowns(town, completedCount, position);

            position++;
        }

        statement.close();
        return null;
    }

//    public NationsMissionsModel getFirstNationByTheMostCompleted() throws SQLException {
//        Statement statement = connection.createStatement();
//        ResultSet result = statement.executeQuery(
//                "SELECT nation, COUNT(*) AS completed_count " +
//                        "FROM towny_missions " +
//                        "WHERE completed = 1 " +
//                        "GROUP BY nation " +
//                        "ORDER BY completed_count DESC " +
//                        "LIMIT 1"
//        );
//
//        while (result.next()) {
//            String nation = result.getString("nation");
//            int completedCount = result.getInt("completed_count");
//            NationMissionGUI.
//            return String firstNationByMostCompleted = nation + completedCount;
//        }
//
//        statement.close();
//        return null;
//    }
}