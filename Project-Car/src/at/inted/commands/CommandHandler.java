package at.inted.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
	List<Command> commands = new ArrayList<Command>();
	private static CommandHandler commandHandler = null;

	public CommandHandler(){
		commandHandler = this;
	}


	public void Add(Command command){
		commands.add(command);
	}

	public List<Command> getCommands() {
		return commands;
	}

	public static CommandHandler getInstance() {
		return commandHandler;
	}

}
