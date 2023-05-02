package pl.kraftet.townymissions;

import org.bukkit.plugin.java.JavaPlugin;
import pl.kraftet.townymissions.databases.Database;
import pl.kraftet.townymissions.guis.NationMissionGUI;
import pl.kraftet.townymissions.handlers.CommandsHandler;
import pl.kraftet.townymissions.handlers.EconomyHandler;
import pl.kraftet.townymissions.handlers.EventsHandler;
import pl.kraftet.townymissions.handlers.FilesHandler;

import java.sql.SQLException;
import java.util.logging.FileHandler;

public final class TownyMissions extends JavaPlugin {

    private Database database;
    public Database getDatabase() {
        return database;
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        System.out.println("Hello");
        new EventsHandler(this);
        new CommandsHandler(this);
        new EconomyHandler();
        new FilesHandler();
        new NationMissionGUI(this);


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
