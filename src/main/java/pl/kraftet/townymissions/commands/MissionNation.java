package pl.kraftet.townymissions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.kraftet.townymissions.TownyMissions;
import pl.kraftet.townymissions.guis.NationMissionGUI;

public class MissionNation implements CommandExecutor {
    TownyMissions plugin;
    public MissionNation(TownyMissions plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        NationMissionGUI.open(p);
        return false;
    }
}
