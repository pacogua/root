package it.almaviva.rules.core.expressions.types.date;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.dipendenza.dto.DateUtilsBusiness;
import it.almaviva.rules.dipendenza.utils.FormatDate;
import it.almaviva.rules.exceptions.RulesException;

public class DateLessThan extends DateBase {

	public DateLessThan (){}
	
	public DateLessThan (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
		
		super(operando1,operando2, typeCtr);
	}
		
	@Override
	protected ExpressionResult evalueteDateCondition() throws Exception {
		
		ExpressionResult result = new ExpressionResult();
		
        
		boolean error = false;
 	
		  for (int x = 0; x < operando1.getFieldList().size(); x++) {
			    super.currentPrgField = operando1.getFieldList().get(x).getProgCampo();
				
				date1 =DateUtilsBusiness.stringToDate(operando1.getFieldList().get(x).getValue(),FormatDate.getDATE_FORMAT());
				
				if (date1.compareTo(date2)>=0){ 
					error = true;
					break;		
				}						
			}
		
		
		result.setValid(!error);		
		
	   	result.setErrorMessage(buildMessageOperando1() + "deve essere minore"+ buildMessageOperando2());
    	result.setFirstConditionMessage(buildMessageOperando1() +"� minore"+ buildMessageOperando2());   
    				    	
		return result;
	}

	
	@Override
	public  String getOperatore() {
		return RulesConstants.OPERATORE_DATE_LESS_THAN;
	}
	

	@Override
	public ExpressionBase getNewObject() {
		return new DateLessThan();
	}	
	
}
