package it.almaviva.rules.dipendenza.utils.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum Resource {
	
    ERRORS("RulesErrors"),
    MESSASGES("RulesMessages");

		
	public static final String SEPARATORE=".";
	
	private ResourceBundle bundle;
	
	private Resource(String fileName){
    	 bundle =ResourceBundle.getBundle(fileName);		
	}
	
    public  String get(String key) {
        return bundle.getString(key);
    }
	
	
	
	 public  String get(Object t, String key){		  			  
		 return  bundle.getString(t.getClass().getSimpleName().concat(SEPARATORE).concat(key));
	  }
	  
	  
	  public  String get(String key, Object ... arguments) {
	        return MessageFormat.format(get(key), arguments);
	  }
	  

}
