package at.inted;

import at.inted.commands.Command;
import at.inted.commands.CommandHandler;
import at.inted.utils.Console;

import java.util.Arrays;
import java.util.List;

public class ConsoleInput extends Thread {


	public ConsoleInput() {
	}

	public void run() {
		do{
			String input = Console.ReadLine();

			String[] args = input.split(" ");

			boolean executet = false;
			final List<Command> commands = CommandHandler.getInstance().getCommands();
			for(int i = 0; i < commands.size();i++){
				final Command cmd = commands.get(i);
				if(cmd.getName().equalsIgnoreCase(args[0])){
					cmd.execute(args[0], Arrays.copyOfRange(args, 1, args.length));
					executet = true;
				}
				else{
					List<String> aliases = cmd.getAliases();
					for(int j = 0; j< aliases.size();j++){
						String alias = aliases.get(j);
						if(alias.equalsIgnoreCase(args[0])){
							cmd.execute(args[0],Arrays.copyOfRange(args,1,args.length));
							executet = true;
						}
					}
				}
			}
			if(!executet){
				Console.WriteInfo("Command not found! Type help for help!");
			}
		}while(!Main.closeCar);
	}
}
