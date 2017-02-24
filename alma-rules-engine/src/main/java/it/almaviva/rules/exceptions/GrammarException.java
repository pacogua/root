package it.almaviva.rules.exceptions;

public class GrammarException extends RulesException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String ERROR_LABEL="Grammar Rule Exception: ";
	
	
	public GrammarException(String message) {
		super(ERROR_LABEL+message);

	}

	public GrammarException(String message, String tokenFormula){
        super(ERROR_LABEL.concat(message).concat(DETAIL_LABEL+tokenFormula));
	}	
	

	

	

}
