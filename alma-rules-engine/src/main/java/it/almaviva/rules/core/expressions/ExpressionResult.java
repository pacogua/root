package it.almaviva.rules.core.expressions;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class ExpressionResult {


	   private BigDecimal tolleranza;
	   private boolean scostamento;
	   private String errorMessage;
	   private String firstConditionMessage;
	   private boolean valid;
	   private boolean condition;
	   
	   public ExpressionResult(){

	   }
	   
	   /**
	    * 
	    * @param tolleranza
	    * @param scostamento
	    */
	   public ExpressionResult(BigDecimal tolleranza, boolean scostamento) {
		  this.tolleranza = tolleranza;
		  this.scostamento = scostamento;
        }

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getFirstConditionMessage() {
		return firstConditionMessage;
	}
	public void setFirstConditionMessage(String firstConditionMessage) {
		this.firstConditionMessage = firstConditionMessage;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	/**
	 * In presenza di parziale e di condizione non rispettata, 
	 * il metodo valuta la validità del controllo tenendo conto della soglia di tolleranza
	 * @param importi1
	 * @param importi2
	 */
	public void setValid(BigDecimal importi1, BigDecimal importi2){
		
		/**
		 * il controllo non è condizionato alla presenza dello scostamento, ma a qeulla di "tolleranza" con valori significativi.
		 * questo rende indipendente l'applicazione della tolleranza dall'applicazione del calcolo dello scostamento
		 * 
		 */
		
		//if(scostamento){
		if (tolleranza.compareTo(BigDecimal.ZERO) > 0){
		
		    BigDecimal 	valore = importi1.subtract(importi2).abs();
			valore = valore.setScale(2,RoundingMode.HALF_UP);
			
			valid = !(valore.compareTo(tolleranza.multiply(new BigDecimal(-1)))==-1 || valore.compareTo(tolleranza)==1);
		
		}
		else{
			valid = false;
		}
		
	}
	
	public boolean isCondition() {
		return condition;
	}
	public void setCondition(boolean condition) {
		this.condition = condition;
	}

	 public BigDecimal getTolleranza() {
		return tolleranza;
	}

	public boolean isScostamento() {
		return scostamento;
	}

	public void setScostamento(boolean scostamento) {
		this.scostamento = scostamento;
	}	
	
	
    

}
