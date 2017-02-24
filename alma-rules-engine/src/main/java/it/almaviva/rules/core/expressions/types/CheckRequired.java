package it.almaviva.rules.core.expressions.types;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.exceptions.RulesException;

import java.util.ArrayList;
import java.util.Iterator;

public class CheckRequired extends ExpressionBase {

	public CheckRequired(){}
	
	public CheckRequired(String operando1, String operando2,	
			OperandoTemplate typeCtr)throws RulesException{
		
		super(operando1,operando2,typeCtr);
		
	}
	
	@Override
	public ExpressionResult evaluate() throws RulesException {
		
		boolean error =false;
		String msg = ""; 
		String msgErr="";
		
		ExpressionResult result = new ExpressionResult();
		
		if(stringOperando2!= null && !stringOperando2.trim().equals("")){                       
			  throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \" CHECK_REQUIRED \" non prevede un secondo operando");
		}
	    if(operando1.isConstantField()){
	    	 throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \" CHECK_REQUIRED \" non applicabile ad operando con valore costante");
	    }
	    
	    ArrayList<DataFieldPageDTO>  list = operando1.getFieldList();
		Iterator<DataFieldPageDTO> field1Iterator =  list.iterator();		
		DataFieldPageDTO f = null;
		
		while(field1Iterator.hasNext()){
         		
			f =  field1Iterator.next();

//	        if( f.getTypeChecking().equals(CONTROLLO_OBBLIGATORIO)){                        
//	         	  throw new SiquelException(" Formula Dipendenze non valida - restrizione obbligatoriet� campo violata! Il campo "+ f.getProgCampo() +  " � obbligatorio");                                                                     
//	         }
	        
	      // controllo condizione  
	        if(f.getValue() == null || f.getValue().trim().equals("")){		        	
	        	error = true;
	        	break;
	        }		        		      
        }
		
			result.setValid(!error); 
		
      	    msgErr = "Selezionare almeno una delle opzioni proposte ";
        	msg = " almeno una opzione � selezionata ";				        	
       		        			       
       
        	result.setFirstConditionMessage(msg);
        	result.setErrorMessage(msgErr);       				         	

        	return result;
		
		}

	@Override
	public String getOperatore() {
		return RulesConstants.OPERATORE_CHECK_REQUIRED;
	}

	@Override
	public ExpressionBase getNewObject() {
		return new CheckRequired();
	}



}
