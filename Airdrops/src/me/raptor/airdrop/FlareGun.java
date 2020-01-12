package me.raptor.airdrop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;



public class FlareGun implements Listener{
	static Main plugin;
@SuppressWarnings("static-access")
public FlareGun(Main plugin) {
	this.plugin = plugin;
	Bukkit.getPluginManager().registerEvents(this, plugin);
}
public static void spawnDrop(Player p) {


}
@EventHandler
public void onPlayerShoot(PlayerInteractEvent e) {
	if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction() == Action.RIGHT_CLICK_AIR) {
		EquipmentSlot hand = e.getHand();
		if (!hand.equals(EquipmentSlot.HAND)) return;
		Player p = (Player) e.getPlayer();
		PlayerInventory pi = p.getInventory();
		if (pi.getItemInMainHand().getType() == Material.BLAZE_ROD) {
			if (!pi.getItemInMainHand().getItemMeta().getDisplayName().equals("§c§lFlare Gun")) return;
			SmallFireball s = p.launchProjectile(SmallFireball.class);
			new BukkitRunnable() {
				@Override
				public void run() {
					if (s.isDead() == true) return;
				s.getWorld().spawnParticle(Particle.LAVA ,s.getLocation(), 20);
			}
			}.runTaskTimer(plugin, 0, 2);
			new BukkitRunnable() {
				@Override
				public void run() {
				s.remove();
				Location l = new Location(s.getWorld(), s.getLocation().getX(), s.getLocation().getY() * 0 + 150, s.getLocation().getZ());

				if (s.getLocation().getY() < 100) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Flare gun fired wrongly!");
					return;
				} else
				p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Airdrop is comming!");
				@SuppressWarnings("deprecation")
				FallingBlock fb = s.getWorld().spawnFallingBlock(l, Material.NOTE_BLOCK, (byte) 0);
				 new BukkitRunnable() {
					 @Override
					 public void run() {
						 fb.setVelocity(new Vector(0, 0.01, 0));
						 if (fb.isOnGround() == true) cancel();
					 }
				 }.runTaskTimer(plugin, 0, 10);
			 new BukkitRunnable() {
				 @Override
				 public void run() {
					 fb.getWorld().spawnParticle(Particle.SPELL_MOB, fb.getLocation().add(0, 1, 0), 100);
					 if (fb.isOnGround() == true) cancel();
				 }
			 }.runTaskTimer(plugin, 0, 5);
			}
			}.runTaskLater(plugin, 100);
		
		}
}

}
ArmorStand am;
@EventHandler
public void onCrateDrop(EntityChangeBlockEvent e) {
	new BukkitRunnable() {
		@Override
		public void run() {
	
if (e.getBlock().getType() != Material.NOTE_BLOCK) return;
e.setCancelled(true);
e.getEntity().getLocation().getBlock().setType(Material.CHEST);
am = (ArmorStand) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation().add (0.5, 0, 0.5), EntityType.ARMOR_STAND);
am.setVisible(false);
am.setInvulnerable(true);
am.setGravity(false);
am.setSmall(true);
am.setCustomNameVisible(true);
am.setCustomName(ChatColor.RED + "Airdrop");

new BukkitRunnable() {
	@Override
	public void run() {
		if (e.getBlock().getType() != Material.CHEST) return;
		Chest c = (Chest) e.getBlock().getState();
		c.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "Airdrop");
		c.update();
		for (int i = 0; i < 27; i++) {
			Inventory in = Contents.toInventory(plugin.getConfig(), "inventory.Airdrop Settings");
			ItemStack it = in.getItem(i);
			if (it == null) continue;
			c.getInventory().setItem(i, it);

		}


	}
}.runTaskLater(plugin, 5);
	}

}.runTaskLater(plugin, 1);
}
@EventHandler
public void onPlayerBreak(BlockBreakEvent e) {
	if (e.getBlock().getType() == Material.CHEST) {
		for (Entity en : e.getBlock().getWorld().getNearbyEntities(e.getBlock().getLocation(), 1, 1, 1)) {
			if (!(en instanceof ArmorStand)) continue;
			if (en.getCustomName() == null) continue;
			if (!(en.getCustomName().equals("§cAirdrop"))) continue;
			en.remove();
		}
	}
}
}
