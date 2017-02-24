package it.almaviva.rules.exceptions;

public class SemanticException extends RulesException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static String ERROR_LABEL="Semantic Rule Exception: ";
	
	
	public SemanticException(String message) {
		super(ERROR_LABEL+message);

	}

	public SemanticException(String message, String tokenFormula){
		super(ERROR_LABEL.concat(message).concat(DETAIL_LABEL+tokenFormula));
	}	
	

	

}
