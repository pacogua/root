/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.almaviva.rules.dipendenza.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public class CurrencyUtils {

    private static final Locale DEFAULT_LOCALE = Locale.ITALY;
    private static final int DEFAULT_CIFRE_DECIMALI = 2;
    private static final int DEFAULT_CIFRE_PERCENTUALI = 3;


	
    public static Double parse(String value, int cifreDecimali) throws ParseException {
        return parse(value,DEFAULT_LOCALE, cifreDecimali);
    }

    public static Double parse(String value) throws ParseException {
        return parse(value,DEFAULT_LOCALE,getDEFAULT_CIFRE_DECIMALI());
    }

    public static Double parse(String value, Locale locale) throws ParseException {
        return parse(value, locale,DEFAULT_CIFRE_DECIMALI);
    }

    public static Double parse(String value, Locale locale, int cifreDecimali) throws ParseException {
        NumberFormat f = NumberFormat.getInstance(locale);
        f.setMinimumFractionDigits(cifreDecimali);
        f.setMaximumFractionDigits(cifreDecimali);
        return new Double(f.parse(value).doubleValue());
    }

    public static String format(Double value, Locale locale, int cifreDecimali) {
        NumberFormat f = NumberFormat.getInstance(locale);

        f.setMinimumFractionDigits(cifreDecimali);
        f.setMaximumFractionDigits(cifreDecimali);

        String res = f.format(value);
        return res;
    }
    
    public static String format(BigDecimal value, Locale locale, int cifreDecimali) {
        NumberFormat f = NumberFormat.getInstance(locale);

        f.setMinimumFractionDigits(cifreDecimali);
        f.setMaximumFractionDigits(cifreDecimali);

        String res = f.format(value);
        return res;
    }
    
    public static String formatInteri(Integer value, Locale locale, int cifreDecimali) {
        NumberFormat f = NumberFormat.getInstance(locale);

        f.setMinimumFractionDigits(cifreDecimali);
        f.setMaximumFractionDigits(cifreDecimali);

        String res = f.format(value);
        return res;
    }
    

    public static String format(Double value, int cifreDecimali) {
        return format(value,DEFAULT_LOCALE, cifreDecimali);
    }
    
    public static String format(BigDecimal value, int cifreDecimali) {
        return format(value,DEFAULT_LOCALE, cifreDecimali);
    }

    public static String format(Double value, Locale locale) {
        return format(value, locale,DEFAULT_CIFRE_DECIMALI);
    }

    public static String format(Double value) {
        return format(value,DEFAULT_LOCALE,DEFAULT_CIFRE_DECIMALI);
    }
    
    
    public static String formatPercentuale(Double value, int cifreDecimali) {
        return format(value,DEFAULT_LOCALE, cifreDecimali);
    }

    public static String formatPercentuale(Double value, Locale locale) {
        return format(value, locale,DEFAULT_CIFRE_PERCENTUALI);
    }
    
    public static BigDecimal formatStringToDecimal(String value) {
    	
    	return new BigDecimal(value.replaceAll("\\.", "").replaceAll(",", "\\.")).setScale(2,RoundingMode.HALF_UP);
    }

    /**
     * @return the DEFAULT_LOCALE
     */
    public static Locale getDEFAULT_LOCALE() {
        return DEFAULT_LOCALE;
    }

    /**
     * @return the DEFAULT_CIFRE_DECIMALI
     */
    public static int getDEFAULT_CIFRE_DECIMALI() {
        return DEFAULT_CIFRE_DECIMALI;
    }

    /**
     * @return the DEFAULT_CIFRE_PERCENTUALI
     */
    public static int getDEFAULT_CIFRE_PERCENTUALI() {
        return DEFAULT_CIFRE_PERCENTUALI;
    }

    /**
     * @return 
     */
    public static boolean isInt(String s) {   
        return java.util.regex.Pattern.matches("\\d+", s);   
    } 
    
    public static BigDecimal parseString(String value, Locale locale, int cifreDecimali) throws Exception{
        NumberFormat f = NumberFormat.getInstance(locale);
        f.setMinimumFractionDigits(cifreDecimali);
        f.setMaximumFractionDigits(cifreDecimali);
        return new BigDecimal(f.parse(value).toString());  
    }  
    
    
    public static boolean isNumber(String str){
		
		try{
			Double.parseDouble(str);
		} catch (NumberFormatException nfe){
			return false;
		}
		return true;
	}
    
    private void metriche() {
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    	// Metriche
    }
    
    
}
