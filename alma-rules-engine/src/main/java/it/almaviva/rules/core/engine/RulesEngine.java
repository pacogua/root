package it.almaviva.rules.core.engine;

import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.core.statistcs.scostamento.ScostamentoBase;
import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoOutDTO;
import it.almaviva.rules.core.translator.ArithmeticMsgFormulaProducer;
import it.almaviva.rules.core.translator.LiteralMsgFormulaProducer;
import it.almaviva.rules.dipendenza.utils.Trace;
import it.almaviva.rules.exceptions.GrammarException;
import it.almaviva.rules.exceptions.RulesException;
import it.almaviva.rules.exceptions.ValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class RulesEngine extends ValidatorBase{


    public RulesEngine(OperandoTemplate operandoTemplate){    
		super(operandoTemplate);
    }
    
    
    
/**
 * 
 * @param result
 * @param campiPagina
 * @return
 */
    public Result execute(Rule rule) throws RulesException{
    	
    	Result er = new Result();
    	
    	operandoTemplate.setScostamento(rule.isScostamento());
    	operandoTemplate.setTolleranza(rule.getTolleranza());    	    
    	
    	String formula = rule.getFormula();    			
    	
    	if(formula != null){  
    
    	    List<String> conditionalStatementList = new ArrayList<String>(); 
            formula = formula.replace(" ", "").trim().toUpperCase();

            conditionalStatementList  = Arrays.asList(formula.split("ELSE"));  
        
            try{
             
	             for(String a: conditionalStatementList){            	
	            	parseConditionalStatement(a);
	             }
             
            }
            catch(ValidationException d){             	            	  
                                        
		              er.setMessage(rule.getDefaultMsgError() == null ?d.getMessage():rule.getDefaultMsgError());	
		              er.setFields(d.getFields());
		              er.setScostamenti(d.getScostamenti());
		              er.setArithmeticRule(operandoTemplate.isArithmeticRule());

              
              return er;
              
            }catch(RulesException e){
            	
            	e.setRuleFormula(rule.getFormula());
            	throw e;
            	
            }
                                   
    	}
      return er;    	
    }
    
    
  
    
    /**
     * 
     * @param conditionalStatement
     * @throws ValidationException
     */

    private void parseConditionalStatement(String conditionalStatement) throws ValidationException, RulesException  {
    	    
    	
    	try{ 
    		
    		
    		if(operandoTemplate.getMsgFormulaProducer()!=null && operandoTemplate.getMsgFormulaProducer()  instanceof ArithmeticMsgFormulaProducer) {

   			   throw new GrammarException("Funzionalità non ancora supportata: ArithmeticMsgFormulaProducer non consentito in PageFieldValidator");  
   		   		
   		    }
    		
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
                	  //throw new ResultException(" espressione \""+conditionalStatement+"\" non valida ",true);
                    }
                	else{
                		condition = condition.substring(2);	
                	}
                 }
               
           // azzera la lista dei valori dei campi e dei parziali della formula           
           operandoTemplate.resetFields();
           operandoTemplate.resetScostamento();
           operandoTemplate.setArithmeticRule(false);
     	   operandoTemplate.addMsgFormulaProducer(new LiteralMsgFormulaProducer());   
             
            ValidationException validationException;
                 
               if(thenStatement!=null){
            	   
            	   operandoTemplate.setCondition(true);
            	   StatementResult f1 = evaluateStatement(condition);
            	   
                    if(f1.isValid()){
                    	
                    	operandoTemplate.setCondition(false);
                    	StatementResult f2 = evaluateStatement(thenStatement);
                    	
                    	if(!f2.isValid()){
                    		
                    		
                    	    validationException = new ValidationException(f1.getIfMsg().concat(f1.getNewLine()).concat(f2.getThenMsg()));
                    		validationException.setFields(operandoTemplate.getFields());
                    		validationException.setScostamenti(operandoTemplate.getScostamenti());
                    		
                    		throw validationException;                    		              
                    		
                         }
                    }
               }
               else{
            	   
            	   operandoTemplate.setCondition(false);
            	   StatementResult f1 = evaluateStatement(condition);
            	   
                    if(!f1.isValid()){
                    	
                    	String msg = f1.getThenMsg();
                    	
                    	if(f1.getThenMsg().trim().startsWith("allora")){
                    	  msg = msg.replaceFirst("allora","");	
                    	}                    	
                    	
                    	
	                	    validationException = new ValidationException(msg);
	                		validationException.setFields(operandoTemplate.getFields());
	                		validationException.setScostamenti(operandoTemplate.getScostamenti());
	                		
	                		throw validationException;
                    	                   	
                    }                                  
        
               }
    	}       
        catch(ValidationException dc){
    		Trace.error(this,	"Error while checkDipendenza :", dc.getMessage()); 
			Trace.error(this,	"### Dipendenza : " + conditionalStatement); 
        	throw dc;                                             
        }catch (RulesException exs){

			Trace.error(this,	"Error while checkDipendenza :", exs.getMessage());  
			Trace.error(this,	"### Dipendenza : " + conditionalStatement); 
        	throw exs;
        }        
        catch (Exception ex){
			Trace.error(this,	"Error while checkDipendenza :", ex.getMessage());  
			Trace.error(this,	"### Dipendenza : " + conditionalStatement); 
        	
        	throw new RulesException (ex.getMessage(),conditionalStatement);
        }
        
}
 




	/**
	 * Controlla che almeno un parziale di tipo Scostamento, se presente, non rispetti la soglia di tolleranza
	 *  per essere considerato un errore significativo da essere memorizzato.
	 *  
	 * @param parzialiErroriList
	 * @param tolleranza
	 * @return
	 */
    @Deprecated
	private boolean checkListParziali(ArrayList<ScostamentoOutDTO> parzialiErroriList, BigDecimal tolleranza){
		
	
		 
		
		if(parzialiErroriList==null || parzialiErroriList.size()<1){
			return true;
		}
		
		
			for (ScostamentoOutDTO parzialeDTO : parzialiErroriList) {
				
		
				
				if(parzialeDTO.getTipologiaParziale().equals(ScostamentoBase.PARZIALE_SCOSTAMENTO) &&
				    (
					 parzialeDTO.getValore().compareTo(tolleranza.multiply(new BigDecimal(-1)))==-1 ||
				     parzialeDTO.getValore().compareTo(tolleranza)==1 
				    ) 
				 ){
		
				    return true;
		
				}
		
			}
	
		
		return false;
	
	
	}




    


}

