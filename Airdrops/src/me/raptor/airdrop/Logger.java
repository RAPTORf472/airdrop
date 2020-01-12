package me.raptor.airdrop;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
 
public class Logger {
   
    public static void broadcast(String s) {
        Bukkit.getServer().broadcastMessage(prefix() + chat(s));
    }
   
    public static void chatP(Player p, String s) {
        p.sendMessage(prefix() + chat(s));
    }
   
    public static void console(String s) {
        Bukkit.getConsoleSender().sendMessage(prefix() + chat(s));
    }
   
    public static String prefix() {
        return chat("&8&l[&4&lAirdrop&8&l] &e");
    }
   
    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
   
  public static String getHelpMessage() {
    StringBuilder builder = new StringBuilder();
    builder.append("\n");
    builder.append("&e&m----------------------&r &l&8[&4&lAirdrop&l&8] &r&e&m----------------------\n");
    builder.append("&e&l/&7Airdrop help &e- Open this help page.\n");
    builder.append("&e&l/&7Airdrop setdrop &e- Open the set drop GUI\n");
    builder.append("&e&l/&7Airdrop seedrop &e- See the drop's content\n");
    builder.append("&e&m-----------------------------------------------------\n");
   
   
        return Logger.chat(builder.toString());
  }
   
   
}