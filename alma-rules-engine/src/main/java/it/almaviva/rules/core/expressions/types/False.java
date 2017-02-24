package it.almaviva.rules.core.expressions.types;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.exceptions.RulesException;

import java.util.ArrayList;
import java.util.Iterator;

public class False extends ExpressionBase {

	public False (){}
	
	public False (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
		
		super(operando1,operando2,typeCtr);
		
	}
	
	
	
	@Override
	public ExpressionResult evaluate()throws RulesException {

		ExpressionResult result = new ExpressionResult();
		
		boolean error = false;

        if(stringOperando2!= null && !stringOperando2.trim().equals("")){                       
			  throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \" TRUE \" non prevede un secondo operando");
		}
	    if(operando1.isConstantField()){
	    	 throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \" TRUE \" non applicabile ad operando con valore costante");
	    }
        
        
         ArrayList<DataFieldPageDTO>  list = operando1.getFieldList();
		 Iterator<DataFieldPageDTO> field1Iterator =  list.iterator();		
		 DataFieldPageDTO f = null;

		  
		   try{
			   
			 while(field1Iterator.hasNext()){		   	 	                    		                     	
					f =  field1Iterator.next();
					

						if( (f.getValue()==null ||f.getValue().equals("") ) ||						    		 
							(!f.getValue().toUpperCase().equals("FALSE")  && !f.getValue().toUpperCase().equals("NO"))){
							
					        	error = true;
					        	break;
						}					

		      }
			 
			 
	        	result.setValid(!error);
			 
		        	
  		           if( operando1.isMoreFields()){	
  		        	    result.setErrorMessage(" il valore dei dati "+(operando1.getFieldLabel())+" deve essere negativo ");
  		        	    result.setFirstConditionMessage(" il valore dei dati "+(operando1.getFieldLabel())+" � negativo ");
			       }else{
			    	    result.setErrorMessage(" il valore del dato "+(operando1.getFieldLabel())+" deve essere negativo ");
			    	    result.setFirstConditionMessage(" il valore del dato "+(operando1.getFieldLabel())+" � negativo ");
			       }		
   	
			 
		        return result;
		        
		  }	 
		  catch(NumberFormatException ex){
			    throw new  RulesException(" errore di conversione numerica nel controllo delle dipendenze! Formula: ["+operando1+"FALSE"+operando2+"]");			  
		  }catch(Exception e){                                                                      
		    throw new  RulesException(" errore in DipendenzaNull. Formula: ["+operando1+"FALSE"+operando2+"]");			  
		  }
			
          	 
		
		}			 

	@Override
	public String getOperatore() {
		// TODO Auto-generated method stub
		return RulesConstants.OPERATORE_FALSE;
	}
	

	@Override
	public ExpressionBase getNewObject() {
		return new False();
	}
	
	
}


