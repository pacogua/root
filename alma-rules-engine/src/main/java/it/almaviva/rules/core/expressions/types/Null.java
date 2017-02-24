package it.almaviva.rules.core.expressions.types;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.exceptions.RulesException;

import java.util.ArrayList;
import java.util.Iterator;

public class Null extends ExpressionBase {

	public Null(){}
	
	public Null (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{

		super(operando1,operando2, typeCtr); 
	
	}
	
	
	
	@Override
	public ExpressionResult evaluate()throws RulesException {

		ExpressionResult result = new ExpressionResult();
		
		boolean error =false;
		String msg = ""; 
		String msgErr="";
		
		
        if(stringOperando2!= null && !stringOperando2.trim().equals("")){                       
			  throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \" NULL \" non prevede un secondo operando");
		}
	    if(operando1.isConstantField()){
	    	 throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \" NULL \" non applicabile ad operando con valore costante");
	    }
	
         ArrayList<DataFieldPageDTO>  list = operando1.getFieldList();
		 Iterator<DataFieldPageDTO> field1Iterator =  list.iterator();		
		 DataFieldPageDTO f = null;

		  
		   try{
			   
			 while(field1Iterator.hasNext()){
		   	 	                    		                     		
					f =  field1Iterator.next();
	
			        if(f.getTypeChecking() != null && f.getTypeChecking().equals(RulesConstants.CONTROLLO_OBBLIGATORIO)){                        
			         	  throw new RulesException(" Formula Dipendenze non valida - restrizione obbligatoriet� campo violata! Il campo "+ f.getProgCampo() +  " � obbligatorio");                                                                     
			         }
			        
			      // controllo condizione  
			        if(f.getValue() != null && !f.getValue().trim().equals("")){		        	
			        	error = true;
			        	break;
			        }		        		      

		      }
			 	        			        			       
			        result.setValid(!error); 
	  		           if( operando1.isMoreFields()){	
	  		        	    msgErr = " i dati "+(operando1.getFieldLabel())+" non devono essere valorizzati ";
				        	msg = " i dati "+(operando1.getFieldLabel())+" non sono valorizzati ";				        	
				       }else{
				    	    msgErr = " il dato "+(operando1.getFieldLabel())+" non deve essere valorizzato ";
				    	    msg = " il dato "+(operando1.getFieldLabel())+" non � valorizzato ";
				       }			        			       
	  		         
	  		        result.setFirstConditionMessage(msg);
			        result.setErrorMessage(msgErr);       				         	
	
			 
		        return result;
		        
		  }	 
		  catch(NumberFormatException ex){
			    throw new  RulesException(" errore di conversione numerica nel controllo delle dipendenze! Formula: ["+operando1.getFieldLabel()+"NULL"+operando2.getFieldLabel()+"]");			  
		  }catch(RulesException se){
			  throw se;
		  }
		  catch(Exception e){                                                                      
		    throw new  RulesException(" errore in DipendenzaNull. Formula: ["+operando1.getFieldLabel()+"NULL"+operando2.getFieldLabel()+"]");			  
		  }
			
          	 
		
		}			 

	@Override
	public String getOperatore() {
		// TODO Auto-generated method stub
		return RulesConstants.OPERATORE_NULL;
	}
	

	@Override
	public ExpressionBase getNewObject() {
		return new Null();
	}	
	
}


