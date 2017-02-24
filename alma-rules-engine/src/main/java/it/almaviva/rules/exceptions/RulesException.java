/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.almaviva.rules.exceptions;


public class RulesException extends Exception {

  
	private static final long serialVersionUID = 1L;
	
    private String ruleFormula;
	
    protected static String DETAIL_LABEL="tokenFormula: ";

	private static String ERROR_LABEL="Grammar Rule Exception: ";
    
	public RulesException(String message) {
        super(ERROR_LABEL.concat(message));
     
    }

	public RulesException(String message, String tokenFormula) {
		super(ERROR_LABEL.concat(message).concat(DETAIL_LABEL+tokenFormula));
     
    }
	
  
    public RulesException(String message, Throwable t) {
        super(message,t);
    }

    public String getRuleFormula() {
		return ruleFormula;
	}


	public void setRuleFormula(String ruleFormula) {
		this.ruleFormula = ruleFormula;
	}

   


}
