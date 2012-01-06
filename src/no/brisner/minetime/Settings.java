package no.brisner.minetime;

import java.io.File;

import no.brisner.minetime.PropertiesFile;

public class Settings {
	public static String mysqlUser;
	public static String mysqlPass;
	public static String mysqlHost;
	public static String mysqlDB;
	public static String mysqlPort;
	
	public static String getHost() {
		return mysqlHost;
	}
	public Settings(File dataFolder) {
		if (new File("Minetime").exists()) {
			updateSettings(dataFolder); // getDataFolder() returns
		} else if (!dataFolder.exists()) {
			dataFolder.mkdirs();
		}
		loadPropertiesFiles(dataFolder);
	}

	private static void loadPropertiesFiles(File dataFolder) {
	    //Use Configuration once it's finished.
	    PropertiesFile pf = new PropertiesFile(new File(dataFolder, "Minetime.conf"));
		mysqlUser = pf.getString("mysqlUser", "root", "Username for MySQL");
		mysqlPass = pf.getString("mysqlPass", "root", "Password for MySQL)");
		mysqlHost = pf.getString("mysqlHost", "127.0.0.1", "Hostname to MySQL server");
		mysqlDB = pf.getString("mysqlDB", "minetime", "MySQL database name");
		mysqlPort = pf.getString("mysqlPort", "3306", "Your MySQL servers port");
		pf.save();
	}
	private void updateSettings(File dataFolder) {
		File oldDirectory = new File("Minetime");
		dataFolder.getParentFile().mkdirs();
		oldDirectory.renameTo(dataFolder);
	}
}
