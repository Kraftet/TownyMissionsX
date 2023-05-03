package pl.kraftet.townymissions;

import org.bukkit.plugin.java.JavaPlugin;
import pl.kraftet.townymissions.databases.Database;
import pl.kraftet.townymissions.guis.nationmission.NationMissionGUI;
import pl.kraftet.townymissions.handlers.CommandsHandler;
import pl.kraftet.townymissions.handlers.EconomyHandler;
import pl.kraftet.townymissions.handlers.EventsHandler;
import pl.kraftet.townymissions.handlers.FilesHandler;

import java.sql.SQLException;

public final class TownyMissions extends JavaPlugin {

    private Database database;
    public Database getDatabase() {
        return database;
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        new EventsHandler(this);
        new CommandsHandler(this);
        new EconomyHandler();
        new FilesHandler(this);


        // <❗Database>
        try {
            this.database = new Database();
            database.initializeDatabase();
        } catch (SQLException e) {
            System.out.println("Unable to connect to databse and create tables.");
            throw new RuntimeException(e);
        }
        // </❗Database>
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
