package it.almaviva.rules.core.expressions.operando;

import it.almaviva.rules.dipendenza.dto.OperandoDTO;
import it.almaviva.rules.exceptions.RulesException;

public abstract class OperandoBase implements OperandoInterface{

	protected String  stringToCheck;
	private OperandoTemplate controller; 
	private boolean dipendenzaNumeric;
	
	public OperandoBase (){}
	
	
//	public OperandoBase (String  stringToCheck,  boolean dipendenzaNumeric,CtrBase controller){
//		this.stringToCheck = stringToCheck;
//		this.controller = controller; 			
//   	    this.dipendenzaNumeric = dipendenzaNumeric;
//	}
	public OperandoBase (String  stringToCheck,  boolean dipendenzaNumeric,OperandoTemplate controller){
		//
		// Constructor
		//
		this.stringToCheck = stringToCheck;
		this.controller = controller; 			
   	    this.dipendenzaNumeric = dipendenzaNumeric;
	}
		
	public abstract String getCode();
	


	public OperandoTemplate getController() {
		//
		// get
		//
		return controller;
	}

	public void setController(OperandoTemplate controller) {
		//
		// set
		//
		this.controller = controller;
	}

	public OperandoDTO init()throws RulesException{
		
		//
		// Init
		//
		if(stringToCheck==null || controller ==null){
		  throw new RulesException("OperandoBase error: aggiungere il controller o stringToCheck alla classe !");	
		}
		
		return build();
	}
	
	abstract OperandoDTO build()throws RulesException;


	public String getStringToCheck() {
		//
		// get
		//
		return stringToCheck;
	}


	public void setStringToCheck(String stringToCheck) {
		//
		// set
		//
		this.stringToCheck = stringToCheck;
	}
	
	public boolean isDipendenzaNumeric() {
		//
		// get
		//
		return dipendenzaNumeric;
	}


	public void setDipendenzaNumeric(boolean dipendenzaNumeric) {
		//
		// set
		//
		this.dipendenzaNumeric = dipendenzaNumeric;
	}
	
}
