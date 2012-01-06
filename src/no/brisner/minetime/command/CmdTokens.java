package no.brisner.minetime.command;

import lib.PatPeter.SQLibrary.MySQL;
import no.brisner.minetime.Minetime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
/* Commandhandler for Tokens.
 * Keep track of how much someone has donated
 * How much tokens they have
 * Automatic upgrade levels (Reach 100usd and get some nice group) 
 * Commands: 
 * no argument - shows donation info
 * shop buy <itemid>- lets the user buy commands/VIP packages or other stuff.
 * shop list - List available shopitems
 * admin - Admin set of commands
 * admin.add <amount> - Add amount of tokens
 * 
 * 
 * */
public class CmdTokens implements CommandExecutor{
	
	String pm = Minetime.premessage;
	String msg;
	String sql;
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String commandLabel, String[] args) {
		try {
			String[] split = args;
			String commandName = command.getName().toLowerCase();
			if("tokens".equals(commandName)) {
				if(split.length >= 1) {
					if("shop".equals(split[0])) {
						// Buy
						// List
						// Help
					}
				} else {
					// Show users donation/token info
						//if(mysql.checkConnection()) {
						//mysql.query(sql);
						//sender.sendMessage(pm + msg.format("Donated so far: [%1$]. Tokens: [%2]",var1, var2));
					//}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	} 
}
