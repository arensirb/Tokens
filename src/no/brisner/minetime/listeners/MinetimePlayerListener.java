package no.brisner.minetime.listeners;

import java.sql.ResultSet;

import lib.PatPeter.SQLibrary.MySQL;
import no.brisner.minetime.Minetime;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class MinetimePlayerListener extends PlayerListener {

	public Minetime plugin;
	MySQL mysql = Minetime.mysql;
	String sql;
	
	public MinetimePlayerListener(Minetime instance) {
	    plugin = instance;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event){
		String playerName = event.getPlayer().getName();
		ResultSet rs;
		
		if(mysql.checkConnection()) {
			if(!mysql.checkTable("mt_players")) {
				sql = "CREATE TABLE `mt_players` (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,`name` VARCHAR( 25 ) NOT NULL) ENGINE = MYISAM";
				if(!mysql.createTable(sql)) {
					Minetime.log.severe("Error creating table mt_players!");
				}
			}
			sql = "SELECT id FROM mt_players WHERE player = '" + playerName + "'";	// Check if player exists
			rs = mysql.query(sql);
			try {
				if(!rs.next()) {
					sql = String.format("INSERT INTO mt_players (name) VALUES ('%1')",playerName);
					rs = mysql.query(sql);
					try {
						if(!rs.next()) {
							 Minetime.log.severe("There was a problem adding user to database.");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mysql.close();
	}
}	
