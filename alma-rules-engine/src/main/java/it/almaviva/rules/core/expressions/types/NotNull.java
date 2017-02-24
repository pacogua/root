package it.almaviva.rules.core.expressions.types;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.exceptions.RulesException;

import java.util.ArrayList;
import java.util.Iterator;

public class NotNull extends ExpressionBase {

	
	public NotNull (){}
	
	public NotNull (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
		
		super(operando1,operando2,typeCtr);
		
	}
	
	
	
	@Override
	public ExpressionResult evaluate()throws RulesException {

		ExpressionResult result = new ExpressionResult();
		
		boolean error = false;
		String msg = "";
		String msgErr = ""; 
		
        if(stringOperando2!= null && !stringOperando2.trim().equals("")){                       
			  throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \"NOT NULL \" non prevede un secondo operando");
		}
	    if(operando1.isConstantField()){
	    	 throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \"NOT NULL \" non applicabile ad operando con valore costante");
	    }
        
         ArrayList<DataFieldPageDTO>  list = operando1.getFieldList();
		 Iterator<DataFieldPageDTO> field1Iterator =  list.iterator();		
		 DataFieldPageDTO f = null;

		  
		   try{
			   
			 while(field1Iterator.hasNext()){		   	 	                    		                     	
				f =  field1Iterator.next();				
			    if(f.getValue() == null || f.getValue().trim().equals("")){		        	
			        	error = true;
			        	break;
			        }		        		      		        		        
		      }
			 
//		        if(error){ 
//		        	
//  		           if( operando1.isMoreFields()){	
//			        	msg = " i campi "+(operando1.getFieldLabel())+" devono essere valorizzati ";
//			       }else{
//			    	    msg = " il campo "+(operando1.getFieldLabel())+" deve essere valorizzato ";
//			       }		
//
//		            formula.setErrorMessage(msg);       	
//		        	formula.setValid(false);
//		        	
//		        }else{
//
//	  		         if( operando1.isMoreFields()){	
//				        	msg = " i campi "+(operando1.getFieldLabel())+" sono valorizzati ";
//				     }else{
//				    	    msg = " il campo "+(operando1.getFieldLabel())+" � valorizzato ";
//				     }		        	
//		        	
//		        	formula.setFirstConditionMessage(msg);
//		        	formula.setValid(true);		        	
//		        }
			 
			 
			           result.setValid(!error);
		        	
	  		           if( operando1.isMoreFields()){	
	  		        	    msgErr = " i dati "+(operando1.getFieldLabel())+" devono essere valorizzati ";
				        	msg = " i dati "+(operando1.getFieldLabel())+" sono valorizzati ";				        	
				       }else{
				    	    msgErr = " il dato "+(operando1.getFieldLabel())+" deve essere valorizzato ";
				    	    msg = " il dato "+(operando1.getFieldLabel())+" � valorizzato ";
				       }		

			            result.setErrorMessage(msgErr);       	
			        	result.setFirstConditionMessage(msg);
			        	
	        	
			        			 
			 
		        return result;
		        
		  }	 
		  catch(NumberFormatException ex){
			    throw new  RulesException(" errore di conversione numerica nel controllo delle dipendenze! Formula: ["+operando1+"NOT_NULL"+operando2+"]");			  
		  }catch(Exception e){                                                                      
		    throw new  RulesException(" errore in DipendenzaNull. Formula: ["+operando1+"NOT_NULL"+operando2+"]");			  
		  }
			
          	 
		
		}			 

	@Override
	public String getOperatore() {
		// TODO Auto-generated method stub
		return RulesConstants.OPERATORE_NOT_NULL;
	}
	
	@Override
	public ExpressionBase getNewObject() {
		return new NotNull();
	}
	
	
}


