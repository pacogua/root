package it.almaviva.rules.core.expressions.types.numerical;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.exceptions.RulesException;

import java.math.BigDecimal;

public class Equal extends NumericalExpression {

	public Equal (){}
	
	public Equal (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
			
			super(operando1,operando2, typeCtr);
		}
	
	
	@Override
	protected ExpressionResult evalueteNumericCondition() {

		BigDecimal importi1 = operando1.getSommaImporti();
		BigDecimal importi2 = operando2.getSommaImporti();
		  
		
        if(importi1.compareTo(importi2)!=0 ){
        	
			result.setValid(importi1, importi2);        	
                                        
        } 
        else{                      
            result.setValid(true);	                              
        }   
        
    	result.setFirstConditionMessage(buildMessageOperando1() +"� uguale"+ buildMessageOperando2());
    	result.setErrorMessage(buildMessageOperando1() + "deve essere uguale"+ buildMessageOperando2());
				
		return result;
	}
	
	
	   public String buildMessageOperando2(){
		   
		   
		   if (operando2.isArithmetic()){
			   
			   return " al "+operando2.getFieldLabel().substring(2);
			   
		   }else{
		   
			     if(operando2.isConstantField()){
			    	 return " a "+ buildMessageCostant(operando2.getSommaImporti()) +" ";	    	 
			     }
			     else if(operando2.getFieldList().size()>1){
			    	 return " alla somma degli importi dei dati "+ operando2.getFieldLabel() +" ";	    	 
			     }
			     else{
			    	 return " al valore del dato "+ operando2.getFieldLabel()+" "	; 
			     }
		   }
		     
	    }

   @Override
	public  String getOperatore() {
		return RulesConstants.OPERATORE_EQUAL;
	}	
		

	@Override
	public ExpressionBase getNewObject() {
		return new Equal();
	}	

  
   
}
