package no.brisner.minetime.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
/* Commandhandler for membership activation
 *  Basicly just check MySQL for the correct password, and if correct change user to member
 * */
public class CmdPassword implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String commandLabel, String[] args) {
		String[] split = args;
		String commandName = command.getName().toLowerCase();
		return false;
	}
}
