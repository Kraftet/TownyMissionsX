package pl.kraftet.townymissions.guis.townmissiongui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.kraftet.townymissions.TownyMissions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TownMissionGUI {

    public static TownyMissions plugin;
    public static Configuration config;

    public TownMissionGUI(TownyMissions plugin) {
        TownMissionGUI.plugin = plugin;
        config = plugin.getConfig();
        System.out.print(config);
    }

    private static ItemStack item1;
    private static ItemStack item2;
    private static ItemStack item3;
    private static ItemStack item4;
    private static ItemStack item5;

    public static void open(Player p) throws SQLException {
        String inventoryName = config.getString("town_mission_gui.name");

        Inventory inventory = Bukkit.createInventory(p, 45, inventoryName);

        loadItems(inventory);
        loadBestTowns(inventory);

        int slots[];

        int slot1 = config.getInt("town_mission_gui.first_best_town");
        int slot2 = config.getInt("town_mission_gui.second_best_town");
        int slot3 = config.getInt("town_mission_gui.third_best_town");
        int slot4 = config.getInt("town_mission_gui.fourth_best_town");
        int slot5 = config.getInt("town_mission_gui.fifth_best_town");

        inventory.setItem(slot1, item1);
        inventory.setItem(slot2, item2);
        inventory.setItem(slot3, item3);
        inventory.setItem(slot4, item4);
        inventory.setItem(slot5, item5);

        p.openInventory(inventory);
    }

    private static void loadItems(Inventory inventory) {
        // Wybiera sekcje itemy z configu
        ConfigurationSection items = config.getConfigurationSection("items");
        // Tworzy listę itemów
        Set<String> keys = items.getKeys(false);
        // Dla każdego itemu tworzy
        for (String key : keys) {
            ConfigurationSection item = items.getConfigurationSection(key);
            ConfigurationSection slotSection = item.getConfigurationSection("slot");

            int slot = slotSection.getInt("value");
            String material = item.getConfigurationSection("material").toString();
            String name = item.getConfigurationSection("name").toString();
            String lore = item.getConfigurationSection("lore").toString();

            ItemStack itemEQ = new ItemStack(Material.getMaterial(material), 1);
            ItemMeta meta = itemEQ.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            List<String> loreList = new ArrayList<>();
            loreList.add(ChatColor.translateAlternateColorCodes('&', lore));
            meta.setLore(loreList);
            itemEQ.setItemMeta(meta);

            inventory.setItem(slot, itemEQ);
        }
    }

    private static void loadBestTowns(Inventory inventory) throws SQLException {
        plugin.getDatabase().getTownsByTheMostCompleted();

        ConfigurationSection items = config.getConfigurationSection("items");
        Set<String> keys = items.getKeys(false);
        for (String key : keys) {
            ConfigurationSection slot = items.getConfigurationSection(key);

        }
    }

    public static void missionsTopTowns(String nation, int completed, int position) {
        if (nation == null) {
            ItemStack item = new ItemStack(Material.BLUE_BANNER, 1);
            switch (position) {
                case 1 -> item1 = item;
                case 2 -> item2 = item;
                case 3 -> item3 = item;
                case 4 -> item4 = item;
                case 5 -> item5 = item;
            }
        }

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
