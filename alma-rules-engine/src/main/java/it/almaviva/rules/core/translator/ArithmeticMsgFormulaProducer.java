package it.almaviva.rules.core.translator;

import it.almaviva.rules.core.expressions.RulesConstants;



public class ArithmeticMsgFormulaProducer extends BaseMsgFormulaProducer{


	private boolean isValueDetail=true;
	

	/**
     * 
     */
	public void openParenthesis() {
		
		msg.append(" ( ");
	}

	
	@Override
	public void addField(boolean isAbsoluteValue, String labelField, String value) {

		String initMsg="";
		String endMsg ="";
		
		if (isAbsoluteValue){			
			initMsg=RulesConstants.ABSOLUTE_VALUE+"(";
			endMsg =")";
		}			   	
	
		if(isValueDetail){
		  value = "il valore del".concat(labelField).concat(value);
		}

		
		msg.append(initMsg).append(value).append(endMsg);
		
		
	}


	@Override
	public void addOperatore(String operatore) {

		msg.append(" "+operatore+" ");
		
	}

	
    public boolean isValueDetail() {
		return isValueDetail;
	}


	public void setValueDetail(boolean isValueDetail) {
		this.isValueDetail = isValueDetail;
	}
	
	
	@Override
	public  String getLabelAbsoluteValue(String v){
			
		return RulesConstants.ABSOLUTE_VALUE+"(".concat(v).concat(")");
	}
	
}
