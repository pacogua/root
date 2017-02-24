package it.almaviva.rules.core.engine;

import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.ExpressionResult;

import java.util.ArrayList;
import java.util.List;


public class StatementResult { /*implements RulesConstants */

	
	private boolean valid = true;
	private List<ExpressionResult> expressionResult;
	private String logicOperator;
	private String ifMsg;
	private String thenMsg;
	
	
	
	public StatementResult(String logicoperator){
		this.logicOperator =logicoperator;
		expressionResult = new ArrayList<ExpressionResult>();
	}
	
	public void addResult(ExpressionResult f) {
		
		if(expressionResult.size()<1){
			valid = f.isValid();
		}
		
		if(logicOperator==null || logicOperator.equals("") ||logicOperator.equals(RulesConstants.AND)){
			valid = (valid  && f.isValid());
		}
		else if(logicOperator.equals(RulesConstants.OR)){
			valid = (valid  || f.isValid());			
		}
		
		this.expressionResult.add(f);
		
	}
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean isValid) {
		this.valid = isValid;
	}
	public List<ExpressionResult> getExpressionResultList() {
		return expressionResult;
	}

	public String getIfMsg() {
		
		if(expressionResult.size()>1){
			
			if(logicOperator.equals(RulesConstants.AND)){
				ifMsg = " Se sono vere le seguenti asserzioni:";
			}
			else if(logicOperator.equals(RulesConstants.OR)){
				ifMsg = " Se e' vera almeno una delle seguenti asserzioni:";
			}
			
			  for(ExpressionResult f: expressionResult){				  
				  ifMsg += "<br> -"+f.getFirstConditionMessage(); 					  				
			  }					
		}
		else{
			ifMsg = "Se " +expressionResult.get(0).getFirstConditionMessage(); 	
		}		
		return ifMsg;
	}
	
	public String getNewLine(){
    	if(getExpressionResultList().size()>1){
    		return " <br> ";
    	}
    	else{
    		return "";
    	}
	}
		

	public void setIfMsg(String ifMsg) {
		this.ifMsg = ifMsg;
	}

	public String getThenMsg() {
		
		if(expressionResult.size()>1){
			
			if(logicOperator.equals(RulesConstants.AND)){
				thenMsg = " devono essere rispettate le seguenti condizioni: ";
			}
			else if(logicOperator.equals(RulesConstants.OR)){
				thenMsg = " deve essere rispettata almeno una delle seguenti condizioni: ";
			}
			
			  for(ExpressionResult f: expressionResult){				  
				  thenMsg += "<br> -"+f.getErrorMessage(); 					  				
			  }					
		}
		else{
			thenMsg = " allora "+expressionResult.get(0).getErrorMessage(); 	
		}	
		
		return thenMsg;
	}

	public void setThenMsg(String thenMsg) {
		this.thenMsg = thenMsg;
	}  
	


	
}
