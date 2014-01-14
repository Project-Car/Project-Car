package at.inted.commands;

import java.util.ArrayList;
import java.util.List;

public class Command {


	protected String name = "";
	protected List<String> aliases;

	public void execute(String command, String[] args){

	}

	public Command(){
		aliases = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public List<String> getAliases() {
		return aliases;
	}
}
