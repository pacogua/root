package it.almaviva.rules.core.statistcs.scostamento.dto;

import java.math.BigDecimal;


public class ScostamentoInputDTO  {

    private String setKeyParziale;
    private BigDecimal op1valore;
    private BigDecimal op2valore;
	private String errorMessage;      
    private String operatore;

	
    private BigDecimal opvalore;
	private String opMessage;
	private boolean isOperandoToAdd;
    
	
	public boolean isOperandoToAdd() {
		return isOperandoToAdd;
	}


	public void setOperandoToAdd(boolean isOperandoToAdd) {
		this.isOperandoToAdd = isOperandoToAdd;
	}


	public ScostamentoInputDTO(){
	}


	public BigDecimal getOp1valore() {
		return op1valore;
	}


	public void setOp1valore(BigDecimal op1valore) {
		this.op1valore = op1valore;
	}


	public BigDecimal getOp2valore() {
		return op2valore;
	}


	public void setOp2valore(BigDecimal op2valore) {
		this.op2valore = op2valore;
	}


	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getOperatore() {
		return operatore;
	}



	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}


	public String getSetKeyParziale() {
		return setKeyParziale;
	}


	public void setSetKeyParziale(String setKeyParziale) {
		this.setKeyParziale = setKeyParziale;
	}


	public BigDecimal getOpvalore() {
		return opvalore;
	}


	public void setOpvalore(BigDecimal opvalore) {
		this.opvalore = opvalore;
	}


	public String getOpMessage() {
		return opMessage;
	}


	public void setOpMessage(String opMessage) {
		this.opMessage = opMessage;
	}
	

	




	

	
}