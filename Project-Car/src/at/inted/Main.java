package at.inted;

import at.inted.Connection.Car;
import at.inted.Connection.RemoteControl;
import at.inted.Connection.UDP;
import at.inted.commands.CommandHandler;
import at.inted.commands.HelpCommand;
import at.inted.commands.ReloadCommand;
import at.inted.commands.StopCommand;
import at.inted.utils.Console;
import at.inted.utils.Log;

public class Main {

	public static boolean closeCar = false;

	public static void main(String[] args) {
		Console.WriteInfo("Starting Car....");
		//TODO init

		CommandHandler ch = new CommandHandler();
		ch.Add(new HelpCommand());
		ch.Add(new StopCommand());
        ch.Add(new ReloadCommand());
		ConsoleInput ci = new ConsoleInput();
		ci.start();

		Car.setStatus(Car.CarStatus.readyForConnection);
		UDP.receiveMessage();
		RemoteControl rc = new RemoteControl();
		rc.start();

		Console.WriteInfo("Car started!");

		while(!closeCar){
			try{
				Thread.sleep(100);
			}catch(InterruptedException exception){
				Console.WriteError(exception.getStackTrace());
			}
		}


		Console.WriteInfo("Stopping Car...");
		//TODO closing

		Console.WriteInfo("Car stopped!");
		Log.close();




	}
}
