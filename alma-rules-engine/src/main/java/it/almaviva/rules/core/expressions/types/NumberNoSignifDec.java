package it.almaviva.rules.core.expressions.types;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.dipendenza.utils.CommonUtils;
import it.almaviva.rules.exceptions.RulesException;

import java.util.ArrayList;
import java.util.Iterator;

public class NumberNoSignifDec extends ExpressionBase {

	public NumberNoSignifDec (){}
	
	public NumberNoSignifDec (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
		
		super(operando1,operando2, typeCtr);
	}
	
	
	
	@Override
	public ExpressionResult evaluate()throws RulesException {

		ExpressionResult result = new ExpressionResult();
		
		boolean error = false;

		
        if(stringOperando2!= null && !stringOperando2.trim().equals("")){                       
			  throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \" LONG \" non prevede un secondo operando");
		}     
	
         ArrayList<DataFieldPageDTO>  list = operando1.getFieldList();
		 Iterator<DataFieldPageDTO> field1Iterator =  list.iterator();		
		 DataFieldPageDTO f = null;

		  
		   try{
			   
			 while(field1Iterator.hasNext()){		   	 	                    		                     	
					f =  field1Iterator.next();

						if( f.getValue()!=null && !f.getValue().equals("")  && !CommonUtils.isNumberNoSignificativeDecimal(f.getValue())){
							error = true;
							break;
						}					
		      }
			 

	        	   result.setValid(!error);  
	        	   
		           if( operando1.isMoreFields()){	
		        	   result.setFirstConditionMessage(" i valori dei dati "+(operando1.getFieldLabel())+" non contengono decimali significativi ");
		        	   result.setErrorMessage(" i valori dei dati "+(operando1.getFieldLabel())+" non devono contenere decimali significativi ");
			       }else{
			    	   result.setFirstConditionMessage(" il valore del dato "+(operando1.getFieldLabel())+" non contiene decimali significativi ");
			    	   result.setErrorMessage(" il valore del dato "+(operando1.getFieldLabel())+" non deve contenere decimali significativi ");
			       }		        
		        
			 
		        return result;
		        
		  }	 
		  catch(NumberFormatException ex){
			    throw new  RulesException(" errore di conversione numerica nel controllo delle dipendenze! Formula: ["+operando1+"LONG"+operando2+"]");			  
		  }catch(Exception e){                                                                      
		    throw new  RulesException(" errore in DipendenzaLongNumber. Formula: ["+operando1+"LONG"+operando2+"]");			  
		  }
			
          	 
		
		}			 


	@Override
	public String getOperatore() {
		return RulesConstants.OPERATORE_NO_SIGNIF_DEC;
	}

	
	@Override
	public ExpressionBase getNewObject() {
		return new NumberNoSignifDec();
	}		
	
	
}


