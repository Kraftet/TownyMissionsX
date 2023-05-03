package pl.kraftet.townymissions.guis.nationmission;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.kraftet.townymissions.TownyMissions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NationMissionGUI {

    public static TownyMissions plugin;
    public static Configuration config;

    public NationMissionGUI(TownyMissions plugin) {
        NationMissionGUI.plugin = plugin;
        config = plugin.getConfig();
        System.out.print(config);
    }

    private static ItemStack missionDepositItem;
    private static ItemStack missionPreviewItem;
    private static ItemStack missionStatsItem;
    private static ItemStack item1;
    private static ItemStack item2;
    private static ItemStack item3;
    private static ItemStack item4;
    private static ItemStack item5;


    public static void open(Player p) throws SQLException {

        String inventoryName = config.getString("nation_mission_gui.name");
        if (inventoryName == null) {
            inventoryName = "null";
        }

        Inventory inventory = Bukkit.createInventory(p, 45, inventoryName);
        plugin.getDatabase().getNationsByTheMostCompleted();
        inventory.setItem(11, missionDepositItem);
        inventory.setItem(13, missionPreviewItem);
        inventory.setItem(15, missionStatsItem);
//        inventory.setItem(44, item1);
//        inventory.setItem(43, item2);
//        inventory.setItem(45, item3);
//        inventory.setItem(42, item4);
//        inventory.setItem(46, item5);

        p.openInventory(inventory);
    }

    private static ItemStack missionDepositItem() {
        ItemStack depositItem = new ItemStack(Material.CHEST, 1);
        ItemMeta meta = depositItem.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "a");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "a");

        meta.setLore(lore);
        depositItem.setItemMeta(meta);
        return depositItem;
    }

    private static ItemStack missionPreviewItem() {
        ItemStack mission = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = mission.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "a");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "a");

        meta.setLore(lore);
        mission.setItemMeta(meta);
        return mission;
    }

    private static ItemStack missionStatsItem() {
        ItemStack statsItem = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = statsItem.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + "a");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "a");

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

    public static void missionsTopTowns(String nation, int completed, int position) {

        ItemStack item = new ItemStack(Material.RED_BANNER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.of("#FF0F38") + "" + ChatColor.BOLD + nation);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + position + " najlepsza nacja!");
        lore.add(ChatColor.of("#BA002B") + "" + ChatColor.BOLD + "Ukończyli już " + completed + " misji");

        meta.setLore(lore);
        item.setItemMeta(meta);

        switch (position) {
            case 1 -> item1 = item;
            case 2 -> item2 = item;
            case 3 -> item3 = item;
            case 4 -> item4 = item;
            case 5 -> item5 = item;
        }
    }
}
