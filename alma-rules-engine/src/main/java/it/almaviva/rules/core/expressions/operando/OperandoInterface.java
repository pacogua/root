package it.almaviva.rules.core.expressions.operando;

public interface OperandoInterface {

	
	public static final OperandoBase  [] OPERANDI  ={   new OperandoPercentuale(), 
		  												new OperandoCostant()														 												
												    };
	
    public static final String  OPERANDO_COSTANT    = "K{";
    public static final String  OPERANDO_PERCENTUALE= "%";	
	
}
