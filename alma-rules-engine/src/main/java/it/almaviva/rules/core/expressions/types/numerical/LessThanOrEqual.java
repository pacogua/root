package it.almaviva.rules.core.expressions.types.numerical;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.exceptions.RulesException;

import java.math.BigDecimal;

public class LessThanOrEqual extends NumericalExpression {

	public LessThanOrEqual(){}
	
	public LessThanOrEqual (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
			
			super(operando1,operando2, typeCtr);
		}
	
	
	@Override
	protected ExpressionResult evalueteNumericCondition() {

	
		BigDecimal importi1 = operando1.getSommaImporti();
		BigDecimal importi2 = operando2.getSommaImporti();
		  
        if(importi1.compareTo(importi2)>0 ){
    		result.setValid(importi1, importi2);	                                 
        } 
        else{                     
            result.setValid(true);	                              
        }   
        
    	result.setErrorMessage(buildMessageOperando1() + "deve essere minore o uguale"+ buildMessageOperando2());        
    	result.setFirstConditionMessage(buildMessageOperando1() +"� minore o uguale"+ buildMessageOperando2());
    	
		return result;
	}
	
	@Override
	public String getOperatore() {
		return RulesConstants.OPERATORE_LESS_THAN_OR_EQUAL; 
	}
	
	@Override
	public ExpressionBase getNewObject() {
		return new LessThanOrEqual();
	}
	

}
