/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.almaviva.rules.dipendenza.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Eidos_Samperi
 */
public class DateUtilsBusiness {

    public static synchronized String dateToString(Date date, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static synchronized Date stringToDate(String date, String pattern) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(date);
    }
    
    public static String getDataIeri(String pattern) {
    	
    	long aDay = 1000 * 60 * 60 * 24;
    	long now = System.currentTimeMillis();
    	Date yesterday = new Date(now - aDay);
    	SimpleDateFormat format = new SimpleDateFormat(pattern);    	
    	return format.format(yesterday);
    }
    
    public static Date getGiornoPrecedente(Date data) {
    	
    	//Date dataVariazioneDenominazione = DateUtilsBusiness.stringToDate(dto.getDataVariazioneDenominazione(), FormatDate.getDATE_FORMAT());
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(data);
	    calendar.add(Calendar.DATE, -1);
	    Date pastDate = calendar.getTime();
	    
	    return pastDate;
	      
    }
    
    public static String getYearFromDate(Date date) {
    	
    	SimpleDateFormat simpleDateformat=new SimpleDateFormat("yyyy");
    	return simpleDateformat.format(date);
		
	}
    
    public static synchronized String formatStringDate(String date){
    	date = date.trim().substring(0,10);
        return date;
    }

}
