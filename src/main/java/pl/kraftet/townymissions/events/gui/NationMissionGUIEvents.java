package pl.kraftet.townymissions.events.gui;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.kraftet.townymissions.TownyMissions;
import pl.kraftet.townymissions.database.Database;
import pl.kraftet.townymissions.database.models.TownyMissionsDB;

import java.sql.SQLException;
import java.util.UUID;

public class NationMissionGUIEvents implements Listener  {

    private final TownyMissions plugin;
    public NationMissionGUIEvents(TownyMissions plugin) {
        this.plugin = plugin;
    }

    public void itemClicked(InventoryClickEvent e) throws SQLException, TownyException {
        Player p = (Player) e.getWhoClicked();
        Inventory inventory = e.getClickedInventory();
        if (!(e.getView().getTitle().equals("CONFIGa"))) return;
        if (e.getView().getBottomInventory() == inventory) return;

        UUID uuid = p.getUniqueId();
        TownyMissionsDB townyMissionsDB = this.plugin.getDatabase().findNationMissionByPlayer(uuid.toString());


        if (e.getCurrentItem().getItemMeta().getCustomModelData() == 4000) { // mission deposit item
            Material mission_type = Material.getMaterial(townyMissionsDB.getMission_type().toUpperCase());
            for(ItemStack item : p.getInventory().getContents()) {
                if(item != null && item.getType() == mission_type) {
                    int amount = item.getAmount();
                    p.getInventory().remove(item);
                    Resident resident = TownyAPI.getInstance().getResident(p);
                    if (!(resident.hasNation())) return;
                    String nation = resident.getNation().toString();

                    this.plugin.getDatabase().updateAmount(amount, nation, p);
                    this.plugin.getDatabase().updatePlayerStats(amount, uuid);
                }
            }


        } else e.setCancelled(true);

    }

}
