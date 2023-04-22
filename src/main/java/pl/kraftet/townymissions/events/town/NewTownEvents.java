package pl.kraftet.townymissions.events.town;

import com.palmergames.bukkit.towny.event.NewNationEvent;
import com.palmergames.bukkit.towny.event.NewTownEvent;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.kraftet.townymissions.TownMission;
import pl.kraftet.townymissions.TownyMissions;

public class NewTownEvents implements Listener {
    TownyMissions plugin;

    @EventHandler
    public void onNationCreation(NewNationEvent e) {
        Nation nation = e.getNation();
        TownMission.generate(plugin, nation);
    }
}
