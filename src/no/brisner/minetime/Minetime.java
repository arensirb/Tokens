package no.brisner.minetime;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

import lib.PatPeter.SQLibrary.MySQL;

import no.brisner.minetime.Settings;
import no.brisner.minetime.command.CmdDonate;
import no.brisner.minetime.command.CmdPassword;
import no.brisner.minetime.command.CmdTokens;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Minetime extends JavaPlugin {

	Properties props = new Properties();
	
	public MySQL mysql;
	public static final Logger log = Logger.getLogger("Tokens");

	// public final String name = this.getDescription().getName();
	// public final String version = this.getDescription().getVersion();
	public final static String premessage = ChatColor.RED + "[" + ChatColor.GREEN +  "MineTime" + ChatColor.RED +  "] "  + ChatColor.WHITE;

	@Override
	public void onEnable() {
		log.info("[MineTime] Enabling plugin");
		
		getCommand("password").setExecutor(new CmdPassword());
		getCommand("donate").setExecutor(new CmdDonate());
		getCommand("tokens").setExecutor(new CmdTokens());
		
		if (new File("Tokens").exists()) {
			updateSettings(getDataFolder()); // getDataFolder() returns
		} else if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		Settings.initialize(getDataFolder());
		setupMySQL();
	}
	
	public boolean setupMySQL() {
		mysql = new MySQL(Minetime.log, "[Minetime]", Settings.mysqlHost , Settings.mysqlPort, Settings.mysqlDB, Settings.mysqlUser, Settings.mysqlPass);
		if(mysql.checkConnection()) {
			log.info(premessage + "MySQL connection OK.");
			if(!mysql.checkTable("votifier")) {
				String table_test = "0";
				mysql.createTable(table_test);
			}
			return true;
		} else {
			log.severe(premessage + "Error during MySQL initialization");
			return false;
		}
	}

	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command command,	String commandLabel, String[] args) {
		return false;
	}

	private void updateSettings(File dataFolder) {
		File oldDirectory = new File("PrikkSystem");
		dataFolder.getParentFile().mkdirs();
		oldDirectory.renameTo(dataFolder);
	}
}
