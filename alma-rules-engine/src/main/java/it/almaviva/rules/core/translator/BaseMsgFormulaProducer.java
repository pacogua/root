package it.almaviva.rules.core.translator;

import it.almaviva.rules.exceptions.RulesException;


/**
 * 
 * @author GUARINO
 *
 */
public abstract class BaseMsgFormulaProducer {

	protected StringBuffer msg = new StringBuffer();
	

	
	public BaseMsgFormulaProducer(){}
	
	
	/**
	 * Ripulisce il contenuto del messagio per un successivo riutilizzo
	 */
	public void reset(){
		
		msg = new StringBuffer();
		
	}
	


	
	/**
	 * 
	 */
	public abstract void addField(boolean isAbsoluteValue, String labelField, String value);

	
	/**
	 * 
	 */
	public abstract void addOperatore(String operatore);	
	
    /**
     * 
     */
	public abstract void openParenthesis();
	
	
	/**
	 * 
	 * @param msg2
	 */
	public void addStringBuffer(StringBuffer msg2 ){
		
		msg.append(msg2);
		
	}
	
	
	
	/**
	 * 
	 * @param dip
	 * @param value
	 * @throws RulesException
	 */
	public void addConstantValue(String value)throws RulesException{
		
		msg.append(value.substring(value.indexOf("{")+1, value.indexOf("}")));
		
	}
	

	public abstract String getLabelAbsoluteValue(String v);
	
	
    /**
     * 
     */
	public void closeParenthesis() {
		
		msg.append(" ) ");
		
	}


	public StringBuffer getMsg() {
		return msg;
	}


	public void setMsg(StringBuffer msg) {
		this.msg = msg;
	}
	
	
}
