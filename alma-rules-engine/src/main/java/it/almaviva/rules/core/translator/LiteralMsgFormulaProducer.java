package it.almaviva.rules.core.translator;

import it.almaviva.rules.core.expressions.RulesConstants;



public class LiteralMsgFormulaProducer extends BaseMsgFormulaProducer{


	 public static final String LABEL_OPEN_PARENTHESIS ="il dato risultante da ( "; 

	
    /**
     * 
     */
	public void openParenthesis() {
		
		if(msg.toString().endsWith(LABEL_OPEN_PARENTHESIS)){
		
		  msg.append("( ");
		
		}
		else{
			msg.append(LABEL_OPEN_PARENTHESIS);
		}
	}

	@Override
	public void addField(boolean isAbsoluteValue, String labelField, String value) {
		
		String valueAbsoluteLabel= ( isAbsoluteValue)?" "+RulesConstants.ABSOLUTE_VALUE_LABEL: "";			   	
	
		msg.append("il valore"+valueAbsoluteLabel+" del").append(labelField).append(value);

		
	}

	@Override
	public void addOperatore(String operatore) {
	
        if(operatore.equals(")")){

   			msg.append(" ) ");
   		}   		
        else if(operatore.equals("+")){

   			msg.append(" più ");
   		}	
   		else if(operatore.equals("-")){

   			msg.append(" meno ");
   		}	
   		else if(operatore.equals("*")){

   			msg.append(" moltiplicato per ");
   		}	
   		else if(operatore.equals("/")){

   			msg.append(" diviso per ");
   		}
		
		
	}
	
	@Override
	public  String getLabelAbsoluteValue(String v){
		
		return "il valore assoluto di (".concat(v).concat(")");
	}
	
	
	
	  public static String buildFieldValue(String a){
			 
		  if(a==null || a.equals("")){	
			  return RulesConstants.FIELD_VOID;
		  }
		  else{
		//
			 return a; 
		  }
	  }
	

	   public static String buildPageFieldLabel(String page, String field, String value){
		   return "Pag. '"+page+"' - Campo "+field+" = "+buildFieldValue(value);
		   
		}	
	   
	  
  
	   public static String buildFieldName(String indexF, String fieldName){
		   	   
		   return indexF+ ((fieldName!=null && !fieldName.equals(""))? " ("+fieldName+")" : "");
	   }
	
	
	
}
