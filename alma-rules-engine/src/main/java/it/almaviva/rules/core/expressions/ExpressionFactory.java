package it.almaviva.rules.core.expressions;

import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.exceptions.RulesException;

public class ExpressionFactory {

	
		
	public static ExpressionBase buildExpression(String toParse, OperandoTemplate operandoTemplate)throws RulesException{
			


		     ExpressionBase newObject =null;
		     String operatore=null;    
			 String field1Index="";
			 String field2Index="";
		    
	          try{
				    for(ExpressionBase a : RulesConstants.DIPENDENZE ){
				    	
				    	  if(toParse.indexOf(a.getOperatore())!=-1){ 
				    		  	   	    		 
					          operatore    = a.getOperatore();                      
					          field1Index  = toParse.substring(0,toParse.indexOf(operatore)).trim();
					          field2Index  = toParse.substring(toParse.indexOf(operatore)+operatore.length()).trim();
				    		  	    
					          	          
						      /**Da non usare la reflection che impatterebbe  sulle prestazioni
					          newObject = a.getClass().newInstance();
					          **/
					          
					          newObject = a.getNewObject();	 
					          newObject.init(field1Index, field2Index, operandoTemplate);
					          
				    		  return  newObject;
				    		  
				    	  }	    	
				    }
		
				}
				catch(Exception e){
					throw new RulesException("Errore nella creazione di una nuova istanza dipendenza: "+e.getMessage());
				}
				    
			
	         if(operatore==null){                	   
				      throw new RulesException(" Formula Dipendenze non valida - Operatore non riconosciuto in formula:"+toParse);
		      }
					   
				    		  
			return newObject;
			
		}
		
	
	
}
