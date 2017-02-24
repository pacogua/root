package it.almaviva.rules.core.engine;

import java.util.ArrayList;

import it.almaviva.rules.core.expressions.Field;
import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoOutDTO;

public class Result {
	

	private String message;
	
	private ArrayList<Field> fields;
	
    private ArrayList<ScostamentoOutDTO> scostamenti; 
    
    private boolean arithmeticRule;

	
	


	public boolean isArithmeticRule() {
		return arithmeticRule;
	}
	public void setArithmeticRule(boolean arithmeticRule) {
		this.arithmeticRule = arithmeticRule;
	}
	public ArrayList<ScostamentoOutDTO> getScostamenti() {
		return scostamenti;
	}
	
	public void setScostamenti(ArrayList<ScostamentoOutDTO> scostamenti) {
		this.scostamenti = scostamenti;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ArrayList<Field> getFields() {
		return fields;
	}
	
	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}
	
	
	   public String getFieldsToString(){

			   String fieldsValues ="";
			   
			 	  for(Field f: getFields()){
		
					  fieldsValues += f.getLabel()+"|";
				  }
		 	  
		 	  return fieldsValues;
			   
		   }
	
	
	



	
	
}
