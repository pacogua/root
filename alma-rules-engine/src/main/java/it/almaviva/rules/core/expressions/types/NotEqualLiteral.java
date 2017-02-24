package it.almaviva.rules.core.expressions.types;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.exceptions.RulesException;

public class NotEqualLiteral extends ExpressionBase {

	public NotEqualLiteral (){}
	
	public NotEqualLiteral (String operando1, String operando2, OperandoTemplate typeCtr
			)throws RulesException{
		
		super(operando1,operando2,typeCtr);
		
	}
	
	@Override
	public ExpressionResult evaluate() throws RulesException {
		
		ExpressionResult result = new ExpressionResult();
		
		if(stringOperando2 == null || stringOperando2.trim().equals("")){                       
            throw new RulesException("Formula delle dipendenze campi pagina non valida: atteso secondo operando ");
		}
	
		 operando2 =  getTypeCtr().getOperando(stringOperando2, false);
        
		boolean error = false;
		StringBuffer msg; 
		StringBuffer msgErr;
		String valoreConfronto = "";
		if(operando2.isConstantField())
			valoreConfronto = operando2.getValueConstant().replaceAll("\\^", " ");
		else
			valoreConfronto = operando2.getFieldList().get(0).getValue();
		
		for (int x = 0; x < operando1.getFieldList().size(); x++) {
			if(operando1.getFieldList().get(x).getValue().trim().equalsIgnoreCase(valoreConfronto.trim())) {
				error = true;
				break;
			}  
			
		}
					
		result.setValid(!error);
		
		if( operando1.isMoreFields()){	
      	    msgErr = new StringBuffer(" il valore dei dati "+(operando1.getFieldLabel())+" non deve essere uguale");
      	    msg = new StringBuffer(" il valore dei dati "+(operando1.getFieldLabel())+" non � uguale");

      	    if(operando2.isConstantField()) {
      	    	msgErr.append(" alla costante "+operando2.getValueConstant().replaceAll("\\^", " "));
      	    	msg.append(" alla costante "+ operando2.getValueConstant().replaceAll("\\^", " "));
      	    }else {
      	    	msgErr.append(" al valore del dato "+ operando2.getFieldLabel());
      	    	msg.append(" al valore del dato "+ operando2.getFieldLabel());
      	    }
        	        	        	 		        
		}else{
			msgErr = new StringBuffer(" il valore del dato "+(operando1.getFieldLabel())+" non deve essere uguale");
			msg = new StringBuffer(" il valore del dato "+(operando1.getFieldLabel())+" non � uguale");
			
      	    if(operando2.isConstantField()) {
      	    	msgErr.append(" alla costante "+operando2.getValueConstant().replaceAll("\\^", " "));
      	    	msg.append(" alla costante "+ operando2.getValueConstant().replaceAll("\\^", " "));
      	    }else {
      	    	msgErr.append(" al valore del dato "+ operando2.getFieldLabel());
      	    	msg.append(" al valore del dato "+ operando2.getFieldLabel());
      	    }    	        	   
		}			        			       
       
		result.setFirstConditionMessage(msg.toString());
		result.setErrorMessage(msgErr.toString());    
    				    	
		return result;
	}

	@Override
	public String getOperatore() {
		// TODO Auto-generated method stub
		return RulesConstants.OPERATORE_NOT_EQUAL_LITERAL;
	}
	
	
	@Override
	public ExpressionBase getNewObject() {
		return new NotEqualLiteral();
	}		
	
}
