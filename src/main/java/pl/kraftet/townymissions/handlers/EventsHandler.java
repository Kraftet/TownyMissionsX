package pl.kraftet.townymissions.handlers;

import pl.kraftet.townymissions.TownyMissions;
import pl.kraftet.townymissions.events.gui.NationMissionGUIEvents;
import pl.kraftet.townymissions.events.town.NewTownEvents;
import pl.kraftet.townymissions.guis.NationMissionGUI;

import static org.bukkit.Bukkit.getServer;

public class EventsHandler {

    TownyMissions plugin;

    public EventsHandler(TownyMissions townyMissions) {
        plugin = townyMissions;
    }

    public EventsHandler() {
        getServer().getPluginManager().registerEvents(new NationMissionGUIEvents(plugin), plugin);
        getServer().getPluginManager().registerEvents(new NewTownEvents(), plugin);

    }
}
