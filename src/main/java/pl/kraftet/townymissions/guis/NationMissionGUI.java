package pl.kraftet.townymissions.guis;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.kraftet.townymissions.TownyMissions;

import java.util.ArrayList;
import java.util.List;

public class NationMissionGUI {

    private static TownyMissions plugin;
    public NationMissionGUI(TownyMissions plugin) {
        this.plugin = plugin;
    }
    private static Configuration config = plugin.getConfig();

    public static void open(Player p) {
        Inventory inventory = Bukkit.createInventory((InventoryHolder)p, 45, (config.getString("nation_mission_gui.name")));
        inventory.setItem(11, missionDepositItem());
        inventory.setItem(13, missionPreviewItem());
        inventory.setItem(15, missionStatsItem());
        inventory.setItem(44, missionsFirstTown());
        inventory.setItem(43, missionsSecondTown());
        inventory.setItem(45, missionsThirdTown());
        inventory.setItem(42, missionsFourthTown());
        inventory.setItem(46, missionsFifthTown());

        p.openInventory(inventory);
    }

    private static ItemStack missionDepositItem() {
        ItemStack depositItem = new ItemStack(Material.CHEST, 1);
        ItemMeta meta = depositItem.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + config.getString("nation_mission_gui.deposit_button.name"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "nation_mission_gui.deposit_button.lore");

        meta.setLore(lore);
        depositItem.setItemMeta(meta);
        return depositItem;
    }

    private static ItemStack missionPreviewItem() {
        ItemStack mission = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = mission.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "nation_mission_gui.preview_button.name");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "nation_mission_gui.preview_button.lore");

        meta.setLore(lore);
        mission.setItemMeta(meta);
        return mission;
    }

    private static ItemStack missionStatsItem() {
        ItemStack statsItem = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = statsItem.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "nation_mission_gui.stats_button.name");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "nation_mission_gui.stats_button.lore");

        meta.setLore(lore);
        statsItem.setItemMeta(meta);
        return statsItem;
    }

    private static ItemStack missionsFirstTown() {
        ItemStack missionsTown = new ItemStack(Material.RED_BANNER, 1);
        ItemMeta meta = missionsTown.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "CONFIGb");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "CONFIGc");

        meta.setLore(lore);
        missionsTown.setItemMeta(meta);
        return missionsTown;
    }

    private static ItemStack missionsSecondTown() {
        ItemStack missionsTown = new ItemStack(Material.RED_BANNER, 1);
        ItemMeta meta = missionsTown.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "CONFIGb");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "CONFIGc");

        meta.setLore(lore);
        missionsTown.setItemMeta(meta);
        return missionsTown;
    }

    private static ItemStack missionsThirdTown() {
        ItemStack missionsTown = new ItemStack(Material.RED_BANNER, 1);
        ItemMeta meta = missionsTown.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "CONFIGb");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "CONFIGc");

        meta.setLore(lore);
        missionsTown.setItemMeta(meta);
        return missionsTown;
    }

    private static ItemStack missionsFourthTown() {
        ItemStack missionsTown = new ItemStack(Material.RED_BANNER, 1);
        ItemMeta meta = missionsTown.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "CONFIGb");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "CONFIGc");

        meta.setLore(lore);
        missionsTown.setItemMeta(meta);
        return missionsTown;
    }

    private static ItemStack missionsFifthTown() {
        ItemStack missionsTown = new ItemStack(Material.RED_BANNER, 1);
        ItemMeta meta = missionsTown.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "CONFIGb");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "CONFIGc");

        meta.setLore(lore);
        missionsTown.setItemMeta(meta);
        return missionsTown;
    }

//    private static ItemStack missionsTopTowns() {
//        ItemStack missionsTown = new ItemStack(Material.RED_BANNER, 1);
//        ItemMeta meta = missionsTown.getItemMeta();
//        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + town);
//        List<String> lore = new ArrayList<>();
//        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "CONFIGc");
//
//        meta.setLore(lore);
//        missionsTown.setItemMeta(meta);
//        return missionsTown;
//    }
}
