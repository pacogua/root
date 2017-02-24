package it.almaviva.rules.dipendenza.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class CommonUtils {


	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumberNoSignificativeDecimal(String s){
		
		// da gestire con una sola espressione regolare
		
		try{
			s=s.replaceAll("\\.", "");
			Integer.parseInt(s);
			
			return true;
		}
		catch(Exception e){
			
			Pattern p = Pattern.compile( "([0-9]*)\\,([0]*)" );

			Matcher m = p.matcher(s);
			return m.matches();
	
			
		}
				
		
	}

		public static boolean isNum(String s){
		
		
		try{
			s=s.replaceAll("\\.", "");
			Integer.parseInt(s);
			
			return true;
		}
		catch(Exception e){
			
			Pattern p = Pattern.compile( "([0-9]*))" );

			Matcher m = p.matcher(s);
			return m.matches();
	
			
		}
				
		
	}
		
	

	
	
	
		
}
