package no.brisner.Tokens;

import java.io.File;
import java.sql.*;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Tokens extends JavaPlugin {
	
	public static final Logger log = Logger.getLogger("Tokens");
	
	public final String name = this.getDescription().getName();
    public final String version = this.getDescription().getVersion();
    public final static String premessage = ChatColor.AQUA + "[Tokens]: " + ChatColor.WHITE;

	public void onEnable() {
		getCommand("banana").setExecutor(new Commands());

	
	}
	public void onDisable() {
		
	}
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
    	String[] split = args;
        String commandName = command.getName().toLowerCase();
        
        return false;
	}
}
