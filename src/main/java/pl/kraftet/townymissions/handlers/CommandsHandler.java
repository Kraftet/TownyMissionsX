package pl.kraftet.townymissions.handlers;

import pl.kraftet.townymissions.TownyMissions;
import pl.kraftet.townymissions.commands.MissionCommand;
import pl.kraftet.townymissions.commands.MissionNation;

public class CommandsHandler {
    TownyMissions plugin;

    public CommandsHandler(TownyMissions townyMissions) {
        plugin = townyMissions;
        handler();
    }

    public void handler() {
        plugin.getCommand("mission").setExecutor(new MissionCommand(plugin));
        plugin.getCommand("tiers").setExecutor(new MissionCommand(plugin));
    }
}
