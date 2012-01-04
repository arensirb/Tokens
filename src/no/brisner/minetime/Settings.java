package no.brisner.minetime;

import java.io.File;

import no.brisner.minetime.PropertiesFile;

public class Settings {
	public static String mysqlUser = "root";
	public static String mysqlPass = "root";
	public static String mysqlDB = "jdbc:mysql://localhost:3306/minecraft";
	public static String mysqlTable = "pcsdata";
	public static String sqlEngine;


	public static void initialize(File dataFolder) {
		loadPropertiesFiles(dataFolder);
	}

	public static void onDisable(File dataFolder) {

	}

	private static void loadPropertiesFiles(File dataFolder) {
	    //Use Configuration once it's finished.
	    PropertiesFile pf = new PropertiesFile(new File(dataFolder, "Tokens.conf"));
		mysqlUser = pf.getString("mysqlUser", "root", "Username for MySQL");
		mysqlPass = pf.getString("mysqlPass", "root", "Password for MySQL)");
		mysqlDB = pf.getString("mysqlDB", "jdbc:mysql://localhost:3306/minecraft", "Connection for MySQL");
		pf.save();
	}
}
