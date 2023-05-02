package pl.kraftet.townymissions.events.town;

import com.palmergames.bukkit.towny.event.NewNationEvent;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.kraftet.townymissions.TownyMissions;
import pl.kraftet.townymissions.missions.NationMission;
import pl.kraftet.townymissions.missions.TownMission;

import java.sql.SQLException;

public class NewNationEvents implements Listener {
    private final TownyMissions plugin;
    public NewNationEvents(TownyMissions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNationCreation(NewNationEvent e) throws SQLException {
        Nation nation = e.getNation();
        NationMission.generate(nation);
    }
}
