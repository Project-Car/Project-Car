package at.inted.commands;

import at.inted.Main;

public class StopCommand extends Command{
	public StopCommand(){
		super();
		name = "stop";
		aliases.add("exit");
	}
	public void execute(String command, String[] args){
		Main.closeCar = true;
	}
}
