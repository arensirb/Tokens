package no.brisner.minetime.command;

import no.brisner.minetime.Donations;
import no.brisner.minetime.Minetime;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
/* Commandhandler for donation info
 * Donating gives tokens.
 * 
 * */
public class CmdDonate implements CommandExecutor {
	
	public final static String premessage = ChatColor.RED + "[" + ChatColor.GREEN +  "MineTime" + ChatColor.RED +  "] "  + ChatColor.WHITE;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String commandLabel, String[] args) {
		try {
			String[] split = args;
			String commandName = command.getName().toLowerCase();
			
			if("donate".equals(commandName)) {
				if(split.length >= 1) {
					if("status".equals(split[0])) {
						Donations Donation = new Donations();
						sender.sendMessage(premessage + "Donated so far: [0$]. Tokens: [10]");
					}
				} else {
					sender.sendMessage(premessage + "Visit http://www.minetime.no/ and press donate");
					sender.sendMessage(premessage + "in the upper right corner.");
					sender.sendMessage(premessage + "By donating you are supporting this server.");
					sender.sendMessage(premessage + "Donating will reward you with ingame tokens that you");
					sender.sendMessage(premessage + "can use to buy money, commands, VIP etc.");
					return true;
				}
			} else {
			 return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
