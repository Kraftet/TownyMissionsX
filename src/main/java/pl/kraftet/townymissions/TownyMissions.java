package pl.kraftet.townymissions;

import org.bukkit.plugin.java.JavaPlugin;
import pl.kraftet.townymissions.database.Database;
import pl.kraftet.townymissions.handlers.CommandsHandler;
import pl.kraftet.townymissions.handlers.EventsHandler;

import java.sql.SQLException;

public final class TownyMissions extends JavaPlugin {

    private Database database;
    public Database getDatabase() {
        return database;
    }

    @Override
    public void onEnable() {
        System.out.println("Hello");
        new EventsHandler(this);
        new CommandsHandler(this);


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
