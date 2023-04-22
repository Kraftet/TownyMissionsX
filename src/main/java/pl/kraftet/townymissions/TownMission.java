package pl.kraftet.townymissions;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.checkerframework.checker.units.qual.A;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TownMission {
    private static TownyMissions plugin;
    private static Economy econ;
    public TownMission(TownyMissions plugin) {
        this.plugin = plugin;
    }
    public static String generate(Nation nation) {
        ItemStack item;
        List<String> material_list = plugin.getConfig().getStringList("missions_types");
        int material_list_size = material_list.size();
        int random = (int)(Math.random() * (material_list_size - 1) + 1);
        String material_name = material_list.get(random-1);
        Material material = Material.getMaterial(material_name.toUpperCase());
        String mission_type = material.toString();

        long required_amount = (int)(Math.random() * (30000 - 1) + 1);
        // add to database

        List<Resident> residents = nation.getResidents();

        for (Resident resident : residents) {
            UUID uuid = resident.getPlayer().getUniqueId();
            String sql = "INSERT INTO towny_missions(nation String primary key, current_amount long, required_amount long, mission_type String, uuid varchar(36), stored long) VALUES (" +
                    nation.toString() + ", 0," + required_amount + ", " + mission_type + ", " + uuid + ", 0)";
        }

        return "Zbierz" + material;
    }

    public static void finish(Player player, long excessive_amount) throws SQLException {
        Nation nation = TownyAPI.getInstance().getNation(player);
        String material_name = plugin.getDatabase().getMissionType(nation.toString());
        Material material = Material.getMaterial(material_name.toUpperCase());
        player.getInventory().addItem(new ItemStack(material, (int) excessive_amount));

        reward(nation);
        generate(nation);
    }

    private static void reward(Nation nation) throws SQLException {
        // players
        List<String> players = plugin.getDatabase().getPlayersByNation(nation.toString());
        for (String player : players) {
            Player p = Bukkit.getPlayer(player);
            long stored = plugin.getDatabase().getStoredAmountForPlayer(p.getUniqueId().toString());
            long required = plugin.getDatabase().getRequiredAmountForPlayer(p.getUniqueId().toString());
            long value = (stored/required)*20000;

            econ.depositPlayer(p, value);
        }

        //nation
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            Economy econ = rsp.getProvider();
            if (econ.hasBankSupport()) {
                String townBankName = "nation-" + nation.getName();
                econ.bankDeposit(townBankName, 20000*0.05);
            }
        }
    }
}