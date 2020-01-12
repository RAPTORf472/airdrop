package me.raptor.airdrop;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public Contents c = new Contents();
	FileConfiguration config;
	File cfile;

public void onEnable() {
	new FlareGun(this);
	new Contents(this);
	Logger.console("Plugin Airdrop enabled!");
    config = getConfig();
    config.options().copyDefaults(true);
    saveConfig();
    cfile = new File(getDataFolder(), "config.yml");
	}
public void onDisable() {
	Logger.console("Plugin Airdrop disabled!");
}
public boolean onCommand(CommandSender sd, Command cmd, String cmdLabel, String[] args) {
	if (!(sd instanceof Player)) return true;
	Player p = (Player) sd;
	if (cmd.getName().equalsIgnoreCase("Airdrop")) {
		if (args.length ==  0) {
			p.sendMessage(Logger.getHelpMessage());
			return true;
		} 
			if (args[0].equalsIgnoreCase("help")) {
			p.sendMessage(Logger.getHelpMessage());
			return true;
		}
		 if (args[0].equalsIgnoreCase("setdrop")) {
			c.settingContents(p);

			return true;
		}
		 if (args[0].equalsIgnoreCase("seedrop")) {
			p.openInventory(Contents.toInventory(getConfig(), "inventory.Airdrop Settings"));

			return true;
		}
		 if (args[0].equalsIgnoreCase("Flaregun")) {
			 ItemStack fg = new ItemStack(Material.BLAZE_ROD);
			 ItemMeta fgm = fg.getItemMeta();
			 fgm.setDisplayName("§c§lFlare Gun");
			 fg.setItemMeta(fgm);
			 p.getInventory().addItem(fg);
			 return true;
		 }
	}
	return true;
}
}
