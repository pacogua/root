package it.almaviva.rules.core.expressions.types.numerical;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.exceptions.RulesException;

import java.math.BigDecimal;

public class GreaterThan extends NumericalExpression {

	public GreaterThan (){}
	
	public GreaterThan (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
			
			super(operando1,operando2, typeCtr);
		}
	
	
	@Override
	protected ExpressionResult evalueteNumericCondition() {

		//FormulaDipendenzaDTO formula = new FormulaDipendenzaDTO();
		BigDecimal importi1 = operando1.getSommaImporti();
		BigDecimal importi2 = operando2.getSommaImporti();
		  
        if(importi1.compareTo(importi2)<1 ){        	
    		result.setValid(importi1, importi2);	                                 
        } 
        else{                     
            result.setValid(true);	                              
        }   
				
        result.setErrorMessage(buildMessageOperando1() + "deve essere maggiore"+ buildMessageOperando2());
    	result.setFirstConditionMessage(buildMessageOperando1() +"� maggiore"+ buildMessageOperando2());
        
		return result;
	}
	
	@Override
	public String getOperatore() {	
		return RulesConstants.OPERATORE_GREATER_THAN;
	}


	@Override
	public ExpressionBase getNewObject() {
		return new GreaterThan();
	}
		
	
	
}
