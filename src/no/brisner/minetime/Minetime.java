package no.brisner.minetime;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.brisner.minetime.Settings;
import no.brisner.minetime.handlers.SQLHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Minetime extends JavaPlugin {

	Properties props = new Properties();

	public static final Logger log = Logger.getLogger("Tokens");

	// public final String name = this.getDescription().getName();
	// public final String version = this.getDescription().getVersion();
	// public final static String premessage = ChatColor.AQUA + "[Tokens]: " +
	// ChatColor.WHITE;

	public void onEnable() {
		if (new File("Tokens").exists()) {
			updateSettings(getDataFolder()); // getDataFolder() returns
		} else if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		Settings.initialize(getDataFolder());
		SQLHandler conn = new SQLHandler();
		

	}

	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command command,	String commandLabel, String[] args) {
		String[] split = args;
		String commandName = command.getName().toLowerCase();
		if("donate".equals(commandName)) {
			return true;
		} else if("tokens".equals(commandName)){
			
		} else {
			
		}
		return false;
	}

	private void updateSettings(File dataFolder) {
		File oldDirectory = new File("PrikkSystem");
		dataFolder.getParentFile().mkdirs();
		oldDirectory.renameTo(dataFolder);
	}
}
