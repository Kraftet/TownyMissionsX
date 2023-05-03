package pl.kraftet.townymissions.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kraftet.townymissions.TownyMissions;
import pl.kraftet.townymissions.guis.nationmission.NationMissionGUI;
import pl.kraftet.townymissions.guis.townmissiongui.TownMissionGUI;

import java.sql.SQLException;

public class MissionCommand implements CommandExecutor {
    TownyMissions plugin;
    public MissionCommand(TownyMissions plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof  Player)) return true;
        Player p = (Player) sender;

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /mycommand <arg1> <arg2>");
            return true;
        }
        String arg1 = args[0];
        try {
            switch (arg1) {
                case "nation" -> NationMissionGUI.open(p);
                case "town" -> TownMissionGUI.open(p);
//                case "global" -> GlobalMissionGUI.open(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
