package me.raptor.airdrop;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class Contents implements Listener{
	Main plugin;
public Contents() {}
public Contents(Main plugin) {
	this.plugin = plugin;
	Bukkit.getPluginManager().registerEvents(this, plugin);
}

public void settingContents(Player p) {
	Inventory inv = Bukkit.getServer().createInventory(null , 27, ChatColor.RED + "" + ChatColor.BOLD + "Airdrop Settings");
	p.openInventory(inv);
}
@EventHandler
public void onPlayerCloseInventory(InventoryCloseEvent e) {
	if (!e.getView().getTitle().equals("§c§lAirdrop Settings")) return;
Inventory a = e.getInventory();
	for (ItemStack i : a) {
		if (i == null) continue;
		e.getPlayer().getInventory().addItem(i);
	}
	for (ItemStack it : a.getContents()) {
		if (it != null) {
			break;
		}
		return;
	}
	saveInventory(a, plugin.getConfig(), "inventory.Airdrop Settings");
	plugin.saveConfig();
}
public static Inventory toInventory(FileConfiguration config, String path) {
	Inventory in = Bukkit.createInventory(null, 27);
for (int i = 0; i < 27; i++) {
	if (config.isItemStack(path + "." + i)) {
		in .setItem(i, config.getItemStack(path + "." + i));
	}
}
return in;
	}

public void saveInventory(Inventory inv, FileConfiguration config, String path) {
	for (int i = 0; i < inv.getSize(); i++) {
		if (inv.getItem(i) != null) {
			config.set(path + "." + i, inv.getItem(i));
		} else {
			if (config.isItemStack(path + "." + i)) {
				config.set(path + "." + i, null);
			}
		}
	}
	}
}



