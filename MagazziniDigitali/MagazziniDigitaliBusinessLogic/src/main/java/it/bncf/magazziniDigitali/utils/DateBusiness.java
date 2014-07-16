/**
 * 
 */
package it.bncf.magazziniDigitali.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author massi
 *
 */
public class DateBusiness {


	public static String convert(Timestamp dati){
		GregorianCalendar gc = null;
		String sDati = "";
		DecimalFormat df2 = new DecimalFormat("00");
		DecimalFormat df4 = new DecimalFormat("0000");
		
		if (dati != null){
			gc = new GregorianCalendar();
			gc.setTimeInMillis(dati.getTime());
			sDati = df2.format(gc.get(Calendar.DAY_OF_MONTH))+"/"+
					df2.format(gc.get(Calendar.MONTH)+1)+"/"+
					df4.format(gc.get(Calendar.YEAR))+" "+
					df2.format(gc.get(Calendar.HOUR_OF_DAY))+":"+
					df2.format(gc.get(Calendar.MINUTE))+":"+
					df2.format(gc.get(Calendar.SECOND));
		}
		return sDati;
	}

}
