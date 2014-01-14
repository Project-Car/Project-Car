package at.inted.Connection;

import at.inted.utils.Console;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP {

	private final static int PORT = 59126;

	public static void receiveMessage(){
		Thread thread = new Thread(new Runnable() {
			public void run() {

				DatagramSocket socket;
				DatagramPacket in;

				try {

					socket = new DatagramSocket(PORT);

				} catch (SocketException exception) {

					Console.WriteError(exception);
					return;
				}

				while(true){

					byte[] inData = new byte[1024];
					byte[] outData;
					String message;

					in = new DatagramPacket(inData, inData.length);

					try {
						socket.receive(in);

					} catch (IOException exception) {

						Console.WriteError(exception);
					}

					InetAddress senderIp = in.getAddress();
					int sendePort = in.getPort();
					message = new String(in.getData(), 0, in.getLength());
					Console.WriteInfo("Received Data (UDP):" + message);

					if (message.equalsIgnoreCase("car:anyserver")) {

						int status = Car.getStatus().getStatus();
						String answer;

						answer = "is n:Raspberry s:" + status;
						Console.WriteInfo("Answered with:" + answer);

						outData = answer.getBytes();
						DatagramPacket out = new DatagramPacket(outData, outData.length, senderIp, PORT);

						try {
							socket.send(out);
							socket.close();

						} catch (IOException exception) {

							Console.WriteError(exception);
						}
					}

					else if (RemoteControl.getIp() == in.getAddress()){

						RemoteControl.receivedUDP(message);
					}


				}
			}
		});
		thread.start();

	}
}

