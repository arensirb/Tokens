package no.brisner.minetime.listeners;

import java.sql.ResultSet;

import no.brisner.minetime.MySQL;
import no.brisner.minetime.Minetime;
import no.brisner.minetime.Settings;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class MinetimePlayerListener extends PlayerListener {
	private MySQL mysql = new MySQL();
	public Minetime plugin;
	String sql;
	
	public MinetimePlayerListener(Minetime instance) {
	    plugin = instance;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event){
		String playerName = event.getPlayer().getName();
		ResultSet rs;
		Minetime.log.info("Hosntame is:" + Settings.mysqlHost);
		if(!mysql.checkConnection()) {
			mysql.getConnection();
		} else {
			if(!mysql.checkTable("mt_players")) {
				sql = "CREATE TABLE `mt_players` (`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,`name` VARCHAR( 25 ) NOT NULL) ENGINE = MYISAM";
				if(!mysql.createTable(sql)) {
					Minetime.log.severe("Error creating table mt_players!");
				}
			}
			sql = "SELECT id FROM mt_players WHERE name = '" + playerName + "'";	// Check if player exists
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
