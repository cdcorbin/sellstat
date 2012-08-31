package jobs;

import java.io.BufferedReader;
import java.io.FileReader;
import models.Event;

import org.apache.commons.lang.StringUtils;

public class EventHole {

	/**
	 * 
	 * <when>2012-08-20T10:15:38.661-07:00</when>
	   <gx:coord>-105.2246855 40.0257356 0</gx:coord>
	 * @param fn
	 */
	public static void parse(String fn) {
		 try {
				FileReader input = new FileReader(fn);
	            
				BufferedReader bufRead = new BufferedReader(input);
	            String line = null; 	// String that holds current file line
	            do {
	            	line = bufRead.readLine();
	            	if (null != line) {
			            String linea = split(line,"<when>","</when>");
		                String lineb = split(bufRead.readLine(),"<gx:coord>","</gx:coord>");
		                String[] ll = StringUtils.split(lineb);
		                Double longr = Double.parseDouble(ll[0]);
		                Double latr = Double.parseDouble(ll[1]);
		                Event ev = new Event("1",longr,latr, linea);
		                ev.save();
	            	}
	            }while (line != null);
	            
	            bufRead.close();
				
			}catch (Exception e){
	            e.printStackTrace();
	        }
	        
	}
	private static String split(String src, String a, String b) {
        String pa = StringUtils.removeStart(src, a);
        return StringUtils.removeEnd(pa, b);
	}
}
