package pl.kraftet.townymissions.missions;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import pl.kraftet.townymissions.TownyMissions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TownMission {
    private static TownyMissions plugin;
    private static Economy econ;
    public TownMission(TownyMissions plugin) {
        TownMission.plugin = plugin;
    }
    public static void generate(Town town) throws SQLException {
        List<String> material_list = plugin.getConfig().getStringList("missions_types");
        int material_list_size = material_list.size();
        int random = (int)(Math.random() * (material_list_size - 1) + 1);
        String mission_type = material_list.get(random-1);

        long required_amount = (int)(Math.random() * (30000 - 1) + 1);

        // add to database
        List<Resident> residents = town.getResidents();

        for (Resident resident : residents) {
            if (resident.getPlayer() == null) return;
            UUID uuid = resident.getPlayer().getUniqueId();
            String sql = "INSERT INTO towny_missions(nation, current_amount, required_amount, mission_type, uuid, stored) " +
                    "VALUES ('" + town.toString() + "', 0, " + required_amount + ", '" + mission_type + "', '" + uuid.toString() + "', 0)";

            PreparedStatement statement = plugin.getDatabase().getConnection().prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        }
    }

    public static void finish(Player player, long excessive_amount) throws SQLException {
        Town town = TownyAPI.getInstance().getTown(player);
        String material_name = plugin.getDatabase().getMissionType(town.toString());
        Material material = Material.getMaterial(material_name.toUpperCase());
        player.getInventory().addItem(new ItemStack(material, (int) excessive_amount));

        reward(town);
        generate(town);
    }

    private static void reward(Town town) throws SQLException {
        // players
        List<String> players = plugin.getDatabase().getPlayersByNation(town.toString());
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
                String townBankName = "nation-" + town.getName();
                econ.bankDeposit(townBankName, 20000*0.05);
            }
        }
    }
}