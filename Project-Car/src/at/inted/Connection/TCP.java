package at.inted.Connection;

import at.inted.utils.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCP {

	public static String receiveMessage(Socket socket){

		String nachricht = null;
		try{

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			char[] buffer = new char[200];
			int anzahlZeichen = bufferedReader.read(buffer, 0, 200);
			nachricht = new String(buffer, 0, anzahlZeichen);

		} catch (IOException exception){

			Console.WriteError(exception);
		}

		return nachricht;
	}
}
