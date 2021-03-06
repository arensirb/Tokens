package no.brisner.minetime.listeners;

import java.sql.ResultSet;

import no.brisner.minetime.MySQL;
import no.brisner.minetime.Minetime;
import no.brisner.minetime.Settings;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class MinetimePlayerListener extends PlayerListener {
	public MinetimePlayerListener(Minetime instance) {
	    plugin = instance;
	}
	public Minetime plugin;
	private MySQL mysql;
	String sql;
	
	
	public void onPlayerJoin(PlayerJoinEvent event){
		mysql = new MySQL(Settings.mysqlHost,Settings.mysqlPort, Settings.mysqlDB, Settings.mysqlUser, Settings.mysqlPass);
		String playerName = event.getPlayer().getName();
		Minetime.log.info("Player joining: " + playerName);
		ResultSet rs;
		Minetime.log.info("Hostname is:" + Settings.mysqlHost);
		if(mysql.open())
		{
			if(!mysql.checkTable("mt_players")) {
				sql = "CREATE TABLE `mt_players` (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,`name` VARCHAR( 25 ) NOT NULL) ENGINE = MYISAM";
				if(!mysql.query(sql)) {
					Minetime.log.severe("Error creating table mt_players!");
				}
			}
			sql = "SELECT id FROM mt_players WHERE name = '" + playerName + "'";	// Check if player exists
			 if(mysql.query(sql)) {
				 if(mysql.next()) {
					 mysql.prepare("INSERT INTO mt_players (name) VALUES (?)");
					 mysql.setString(1,playerName);
					 mysql.queryUpdate();
				 }
			 }
		}
		mysql.close();
	}
}	
