package no.brisner.minetime.command;

import no.brisner.minetime.Minetime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
/* Commandhandler for donation info
 * Donating gives tokens.
 * 
 * */
public class CmdDonate implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String commandLabel, String[] args) {
		String[] split = args;
		String commandName = command.getName().toLowerCase();
		if("donate".equals(commandName)) {
			Minetime.log.info("DONATE COMMAND!");
			return true;
		}
		return false;
	}
}
