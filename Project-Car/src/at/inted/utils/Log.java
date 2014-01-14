package at.inted.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private static PrintWriter pWriter = null;

	private static void init(){
		try {
			File dir = new File("logs/");
			dir.mkdir();
			dir.setExecutable(true, false);
			dir.setReadable(true, false);
			dir.setWritable(true, false);


			File file = new File("logs/latest.log");
			file.setExecutable(true, false);
			file.setReadable(true, false);
			file.setWritable(true, false);
			if(file.exists()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				file.renameTo(new File("logs/"+sdf.format(new Date()) + ".log"));
			}
			file.createNewFile();

			pWriter = new PrintWriter(new FileWriter(file));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null)
				pWriter.flush();
		}
	}

	public static void writeToLog(Object obj){
		if(pWriter == null){
			init();
		}

		pWriter.println(obj);
		pWriter.flush();
	}

	public static void close(){
		pWriter.close();
	}
}
