package it.almaviva.rules.core.expressions.types.numerical;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.exceptions.RulesException;

import java.math.BigDecimal;

public class GreaterThanOrEqual extends NumericalExpression {

	public GreaterThanOrEqual (){}
	
	public GreaterThanOrEqual (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
			
			super(operando1,operando2, typeCtr);
		}
	
	
	@Override
	protected ExpressionResult evalueteNumericCondition() {

	
		BigDecimal importi1 = operando1.getSommaImporti();
		BigDecimal importi2 = operando2.getSommaImporti();
		  
        if(importi1.compareTo(importi2)==-1 ){
    		result.setValid(importi1, importi2);                                 
        } 
        else{                  
            result.setValid(true);	                              
        }   
        
//        if(operando2.isConstantField() && importi2.compareTo(new BigDecimal(1000)) == 0) {
//        	
//        	formula.setErrorMessage("Controllare che tutti i dati siano esposti in euro, anche con l'indicazione dei centesimi ");        
//	    	formula.setFirstConditionMessage(buildMessageOperando1() +"� espresso in migliaia");
//        }else {        
        	result.setErrorMessage(buildMessageOperando1() + "deve essere maggiore o uguale"+ buildMessageOperando2());
        	result.setFirstConditionMessage(buildMessageOperando1() +"� maggiore o uguale"+ buildMessageOperando2()); 
        //}
    	
		return result;
	}

	@Override
	public String getOperatore() {
		return RulesConstants.OPERATORE_GREATER_THAN_OR_EQUAL; 
	}
	
	@Override
	public ExpressionBase getNewObject() {
		return new GreaterThanOrEqual();
	}

	
}
