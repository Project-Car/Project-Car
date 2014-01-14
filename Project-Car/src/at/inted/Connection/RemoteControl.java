package at.inted.Connection;

import at.inted.utils.Console;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteControl extends Thread{

	private static Socket socket;
	private final static int PORT = 59126;
	private static InetAddress ip;

	public RemoteControl(){



	}

	public static void  receivedUDP(String message){

		String[] commands = message.split(" ");
		String[] command1 = commands[0].split(":");
		String[] command2 = commands[1].split(":");

		if (command1[0].equalsIgnoreCase("ca") || command1[0].equalsIgnoreCase("cs")){

			if (command1[0].equalsIgnoreCase("ca")){

				try{
					double diffangle = Double.parseDouble(command1[1]);

					double diffspeed = Double.parseDouble(command2[1]);

					Car.setDiffangle(diffangle);
					Car.setDiffspeed(diffspeed);

				} catch (Exception exception){

					Console.WriteError("Wrong message from client(UDP)");
				}
			}

			else if(command1[0].equalsIgnoreCase("cs")){

				try{
					double diffangle = Double.parseDouble(command2[1]);

					double diffspeed = Double.parseDouble(command1[1]);

					Car.setDiffangle(diffangle);
					Car.setDiffspeed(diffspeed);

				} catch (Exception exception){

					Console.WriteError("Wrong message from client(UDP)");
				}
			}
		}
	}

	public static InetAddress getIp() {
		return ip;
	}

	public void run(){
		try{

			ServerSocket serverSocket = new ServerSocket(PORT);
			socket = serverSocket.accept();

			ip = socket.getInetAddress();

			Car.setStatus(Car.CarStatus.Connected);
			Console.WriteInfo("New Client connected");

		} catch (IOException exception){

			Console.WriteError(exception);

		}

		while (true){
			String message = TCP.receiveMessage(socket);

			Console.WriteInfo("Received Data (TCP):" + message);

			String[] commands = message.split(" ");
			String[] command1 = commands[0].split(":");
			String[] command2 = commands[1].split(":");

			if (command1[0].equalsIgnoreCase("aa") || command1[0].equalsIgnoreCase("as")){

				if (command1[0].equalsIgnoreCase("aa")){

					try {

						double angle = Double.parseDouble(command1[1]);

						double speed = Double.parseDouble(command2[1]);

						Car.setAngle(angle);
						Car.setSpeed(speed);

					} catch (Exception exception){

						Console.WriteError("Wrong message from client(TCP)");
					}
				}

				else if(command1[0].equalsIgnoreCase("as")){

					try {

						double angle = Double.parseDouble(command2[1]);

						double speed = Double.parseDouble(command1[1]);

						Car.setAngle(angle);
						Car.setSpeed(speed);

					} catch (Exception exception){

						Console.WriteError("Wrong message from client(TCP)");
					}
				}
			}
		}
	}
}
