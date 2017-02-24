package it.almaviva.rules.core.expressions.types.numerical;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.exceptions.RulesException;

import java.math.BigDecimal;

public class Positive extends NumericalExpression {

	public Positive(){}
	
	public Positive(String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
		
		super(operando1,operando2, typeCtr);
	}
	

	
	
	@Override
	public ExpressionResult evalueteNumericCondition() {
		
	
		BigDecimal importi1 = operando1.getSommaImporti();
		BigDecimal importi2 = operando2.getSommaImporti();
		
		if(importi1.compareTo(importi2) <=0 ){        	
			result.setValid(importi1,importi2);	                                 
        } 
        else{                     
            result.setValid(true);	                              
        }
		
		result.setErrorMessage(buildMessageOperando1() + " deve essere " + (operando1.isMoreFields() ? " positiva " : " positivo "));
    	result.setFirstConditionMessage(buildMessageOperando1() + " � " + (operando1.isMoreFields() ? " positiva " : " positivo "));
    	
		return result;		
	}

	@Override
	public String getOperatore() {
		return RulesConstants.OPERATORE_POSITIVE;
	}

	
	@Override
	public ExpressionBase getNewObject() {
		return new Positive();
	}		
		
	@Override
	public void validate() throws RulesException {
	    if(operando1.isConstantField()){
	    	 throw new RulesException(" Formula delle dipendenze campi pagina non valida: l'operatore \" POSITIVE \" non applicabile ad operando con valore costante");
	    }
	}
	
	@Override
	public void buildOperando2() throws RulesException {

		operando2 = getTypeCtr().getOperando("K{0}", true);
		
	}
	
}
