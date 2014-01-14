package at.inted.commands;

import at.inted.utils.Console;

public class HelpCommand extends Command{

	public HelpCommand(){
		super();
		name = "help";
		aliases.add("?");
		aliases.add("h");
	}


	public void execute(String command, String[] args){
		//TODO help output
		Console.WriteInfo("Help...");
	}
}
