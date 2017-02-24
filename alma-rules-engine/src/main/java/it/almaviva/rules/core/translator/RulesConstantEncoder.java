package it.almaviva.rules.core.translator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import it.almaviva.rules.dipendenza.utils.Trace;
import it.almaviva.rules.exceptions.RulesException;


public class RulesConstantEncoder {

	
	public static final String CONSTANT_LABEL_IDENTIFY="K";

	private static final String JOLLY_ENCODER="\\^";
	
	private static final String JOLLY_ENCODER_APPO="^";
	
	private static final String JOLLY_DECODER="\u00a3";
	
	
	private String formula;
	
	private HashMap<Integer, String> map= new HashMap<Integer, String>(); 
	
    private boolean encoded;
	




	public String getFormulaEncoded() {
		
		if(!encoded){
	    	throw new IllegalArgumentException("Dati non codificati");
		}
		
		return formula;
	}



	public RulesConstantEncoder(String formula){	
		this.formula = formula;
	}
	

	public boolean isEncoded() {
		return encoded;
	}
	
	public void encode() throws RulesException{

		//formula=formula.replaceAll("\\^", "\u00a3");
		formula=formula.replaceAll(JOLLY_ENCODER, JOLLY_DECODER);
	
		Pattern p=null;
		int x=1;
		 
		  
	      p = Pattern.compile("[{\\}]");
		 
	      List<String> splitText =	Arrays.asList (p.split(formula));
	      
	      java.util.Iterator<String> itText = splitText.iterator();
		 
	     int resto=0;
	     String token="";
	     String l ="";
	     
	     
	     try{
	     
	      while(itText.hasNext()){

	        token=itText.next();
	        resto=x%2;
	       
	        	 if(resto==0){
	       
	             	 map.put(x, token);
	             	
	             	
	             	//costantText = costantText.replaceFirst(token, String.valueOf(x));
	                  formula = formula.substring(0,formula.indexOf(CONSTANT_LABEL_IDENTIFY, l.length()-2)).concat(CONSTANT_LABEL_IDENTIFY+"{"+String.valueOf(x)).concat(formula.substring(formula.indexOf("}",l.length()))); 
	                  token = "{" + x + "}"	    ;             	                
	        	 }

	        	 l += token; 
	        	

	        	 x++;
	              
	         }
	      
	      encoded = true;
	      
	     }catch(Exception e){
				Trace.error(this,	"error :", e);  
	    	 throw new RulesException("Errore in metodo protezione formula per valori costanti: "+e.getMessage());
	     }


	}
	
	
	public String decodeToken(String  costantTextToken) throws RulesException{
	
		
		if(!encoded){
	    	throw new IllegalArgumentException("Dati non codificati");
		}
		
		Pattern p=null;
		int x=1;
		 
		  
	      p = Pattern.compile("[{\\}]");
		 
	      List<String> splitText =	Arrays.asList (p.split(costantTextToken));
	      
	      java.util.Iterator<String> itText = splitText.iterator();
		 
	     int resto=0;
	     String token;
	     String costantValue="";
	     String l ="";
	     
	     try{
	     
	      while(itText.hasNext()){

	        token=itText.next();
	        resto=x%2;
	        
	        	if(resto==0){
	        		costantValue=(String) map.get(Integer.parseInt(token)); 
	        	
			        	if (costantValue!=null && !costantValue.equals("")){
			        		costantTextToken = costantTextToken.substring(0,costantTextToken.indexOf(CONSTANT_LABEL_IDENTIFY, l.length()-2)).concat("K{"+costantValue).concat(costantTextToken.substring(costantTextToken.indexOf("}",l.length())));
			        		
			        		//costantTextToken=costantTextToken.replaceAll("£", "^");
			             	costantTextToken=costantTextToken.replaceAll(JOLLY_DECODER, JOLLY_ENCODER_APPO);
			             }
			        	token = "{" + x + "}";
	        	 }
	        	
	        	 l += token; 

	        	 x++;
	              
	         }
	      
	      
	     }catch(Exception e){
			 Trace.error(this,"errore rules ",	e);  
	    	 throw new RulesException("Errore in metodo di decodifica della formula per valori costanti: "+e.getMessage());
	     }
	      
		return costantTextToken;
	}	
}
