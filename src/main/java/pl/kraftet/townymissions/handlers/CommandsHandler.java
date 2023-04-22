package pl.kraftet.townymissions.handlers;

import pl.kraftet.townymissions.TownyMissions;
import pl.kraftet.townymissions.commands.MissionNation;

public class CommandsHandler {
    TownyMissions plugin;

    public CommandsHandler(TownyMissions townyMissions) {
        plugin = townyMissions;
    }

    public void commandsHandler() {
        plugin.getCommand("missionnation").setExecutor(new MissionNation(plugin));
    }
}
