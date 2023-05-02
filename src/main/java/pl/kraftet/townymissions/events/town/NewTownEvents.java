package pl.kraftet.townymissions.events.town;

import com.palmergames.bukkit.towny.event.NewNationEvent;
import com.palmergames.bukkit.towny.event.NewTownEvent;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.kraftet.townymissions.missions.TownMission;
import pl.kraftet.townymissions.TownyMissions;

import java.sql.SQLException;

public class NewTownEvents implements Listener {
    private final TownyMissions plugin;
    public NewTownEvents(TownyMissions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTownCreation(NewTownEvent e) throws SQLException {
        Town town = e.getTown();
        TownMission.generate(town);
    }
}
