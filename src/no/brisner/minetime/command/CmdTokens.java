package no.brisner.minetime.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
/* Commandhandler for Tokens.
 * Keep track of how much someone has donated
 * How much tokens they have
 * Automatic upgrade levels (Reach 100usd and get some nice group) 
 * Commands: 
 * no argument - shows info about yourself
 * buy - lets the user buy commands/VIP packages or other stuff.
 * admin - Admin set of commands
 * admin.add <amount> - Add amount of tokens
 * admin.set <amount> - Set amount of tokens
 * admin.remove <amount> - Remove amount of tokens
 * 
 * */
public class CmdTokens implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String commandLabel, String[] args) {
		String[] split = args;
		String commandName = command.getName().toLowerCase();
		return false;
	}
}
