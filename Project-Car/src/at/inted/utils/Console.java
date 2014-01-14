package at.inted.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

	public static void WriteInfo(Object obj){
		StringBuilder sb = new StringBuilder(getDateTime());
		sb.append(" [INFO] ");
		sb.append(obj.toString());
		System.out.println(sb.toString());
		Log.writeToLog(sb.toString());
	}

	public static void WriteWarning(Object obj){
		StringBuilder sb = new StringBuilder(getDateTime());
		sb.append(" [Warning] ");
		sb.append(obj.toString());
		System.out.println(sb.toString());
		Log.writeToLog(sb.toString());
	}

	public static void WriteError(Object obj){
		StringBuilder sb = new StringBuilder(getDateTime());
		sb.append(" [Error] ");
		sb.append(obj.toString());
		System.err.println(sb.toString());
		Log.writeToLog(sb.toString());
	}

	public static String ReadLine(){
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String zeile = "";
		try {
			zeile = input.readLine();
			StringBuilder sb = new StringBuilder(getDateTime());
			sb.append(" [INPUT] ");
			sb.append(zeile);
			Log.writeToLog(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			StringBuilder sb = new StringBuilder(getDateTime());
			sb.append(" [INPUTERROR] ");
			sb.append("Error while reading consoleinput!");
			System.out.println(sb.toString());
			Log.writeToLog(sb.toString());
			return "";
		}
		return zeile;
	}



	private static String getDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}


}
