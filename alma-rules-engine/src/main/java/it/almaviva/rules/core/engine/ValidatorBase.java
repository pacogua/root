package it.almaviva.rules.core.engine;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionFactory;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.Field;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.core.translator.LiteralMsgFormulaProducer;
import it.almaviva.rules.core.translator.RulesConstantEncoder;
import it.almaviva.rules.dipendenza.utils.Trace;
import it.almaviva.rules.exceptions.GrammarException;
import it.almaviva.rules.exceptions.RulesException;
import it.almaviva.rules.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ValidatorBase {
  
    
    protected final OperandoTemplate operandoTemplate;
    
	public ValidatorBase(OperandoTemplate operandoTemplate) {
		super();
		this.operandoTemplate = operandoTemplate;
	}







	/**
	 * 
	 * @param isCondtion
	 * @throws RulesException
	 */
	protected void setCondition(boolean isCondtion) throws RulesException{
		
	       operandoTemplate.setCondition(false);
	       operandoTemplate.addMsgFormulaProducer(new LiteralMsgFormulaProducer());
		  		
	       /**
		  if(isCondtion){
       	    controller.addMsgFormulaProducer(new LiteralMsgFormulaProducer());
		  }
		  else{
			 controller.addMsgFormulaProducer(new ArithmeticMsgFormulaProducer());
		  }
		  */
		
	}




	/**
     * 
     * @param conditionalStatement
     * @throws ValidationException
     */
      // public void populateFieldsFormula(String conditionalStatement) throws ValidationException  {
	 public void populateFieldsFormula(String conditionalStatement) throws RulesException  {
       	    

       	
       	try{  
       		   String ar[] =  conditionalStatement.split("THEN");
                   
                  String condition  = ar[0];
                  String thenStatement  = (ar.length>1)?ar[1]:null;

                    if(thenStatement == null){                	 
                   	 if(condition.startsWith("IF")){                    
                   	   throw new GrammarException(" l'espressione \""+conditionalStatement+"\" non prevede la parola riservata \"IF\"");
                   	 }
                    }
                    else{
                   	if(!condition.startsWith("IF")){
                   	  throw new GrammarException(" espressione \""+conditionalStatement+"\" non valida ");
                       }
                   	else{
                   		condition = condition.substring(2);	
                   	}
                   }
                  
                  // azzera la lista dei valori dei campi della formula 
                  //controller.setFieldsValues(new ArrayList<FieldValue>());   
                   
                 evaluateStatement(condition);     
                    
                 if(thenStatement!=null){              	  
                     evaluateStatement(thenStatement);
                  }
    
           } 
           catch (RulesException exs){
   			Trace.error(this,	"Error while checkDipendenza :", exs.getMessage());        	   
           	throw exs;
           }        
           catch (Exception ex){
			Trace.error(this,	"Error while checkDipendenza :", ex.getMessage());  
           	throw new RulesException(ex.getMessage());
           }
           
   }    
       
   	
     
    
    
    
	

	/**
     * 
     * @param formula
     * @return
     */
        public ArrayList<Field> getFormulaFields(String formula)throws RulesException{
        	
        	
        	if(formula != null){
        
        	    List<String> conditionalStatementList = new ArrayList<String>(); 
                formula = formula.replace(" ", "").trim().toUpperCase();                   
                  
                conditionalStatementList  = Arrays.asList(formula.split("ELSE"));  
                           
    	             for(String a: conditionalStatementList){ 
    	            	     	        
    	            		populateFieldsFormula(a);
    	            
    	             }                     
        	}
        	
          return operandoTemplate.getFields();
          
        }
    
    
        
	
	
	
	/**	
	   * 
	   * @param toParse
	   * @param hashData
	   * @return
	   * @throws RulesException
	   */

	    public StatementResult evaluateStatement(String toParse) throws RulesException{                   

	        List<String> espressioniList = new ArrayList<String>();
	        
	        toParse = toParse.toUpperCase();
	        RulesConstantEncoder rulesConstantEncoder = new RulesConstantEncoder(toParse);
			rulesConstantEncoder.encode();        
	        
	        espressioniList.add(rulesConstantEncoder.getFormulaEncoded());                
	        StatementResult statementResult = new StatementResult("");
	        
	       
	        if(rulesConstantEncoder.getFormulaEncoded().split(RulesConstants.AND).length>1){  
	        	
	        	 if(rulesConstantEncoder.getFormulaEncoded().split(RulesConstants.OR).length>1){
	             	throw new RulesException(" Funzionalità non ancora supportata: gli operatori devono essere dello stesso tipo [AND] o [OR]");        		 
	        	 }        	
	        	 statementResult = new StatementResult(RulesConstants.AND);
	        	 espressioniList =  Arrays.asList(rulesConstantEncoder.getFormulaEncoded().split(RulesConstants.AND));
	        	 
	        }
	        else if(rulesConstantEncoder.getFormulaEncoded().split(RulesConstants.OR).length>1){
	        	
	        	statementResult = new StatementResult(RulesConstants.OR);
	        	espressioniList =  Arrays.asList(rulesConstantEncoder.getFormulaEncoded().split(RulesConstants.OR));
	        }
	        
	              
	        	// Verifica la validitï¿½ della formula rispetto agli operatori logici presenti  
		        for(String e: espressioniList){        	
		        	statementResult.addResult(evaluateEspressione(rulesConstantEncoder.decodeToken(e)));        	
		        }

	        return statementResult;   

	     } 

	    
	    
	    /**
	     * 
	     * @param toParse
	     * @return
	     * @throws RulesException
	     */
	protected ExpressionResult evaluateEspressione(String toParse) throws RulesException {

		ExpressionBase expression = null;

		if (toParse.startsWith("[") && toParse.endsWith("]")) {
			toParse = toParse.substring(1, toParse.length() - 1);
		} else {
			throw new GrammarException(" Espressione deve cominciare con \"[\" e finire con \"]\":" + toParse);
		}

		expression = ExpressionFactory.buildExpression(toParse, operandoTemplate);
		
		return expression.execute();

	}
	    	  
		/**
		 * 
		 * @param msg
		 * @return
		 */
		protected String buildConditionMessage(String msg){
			
		    msg = msg.substring(msg.indexOf("Se")+2);
		    msg = msg.trim();
		    msg = msg.substring(0,1).toUpperCase() + msg.substring(1);
		    
			return msg;
		}
	    
	    
	  /**  
		  public OperandoTemplate getOperandoTemplate() {
				return operandoTemplate;
			}


			public void setController(OperandoTemplate controller) {
				this.operandoTemplate = controller;
			}	    
	*/
			
	
}
