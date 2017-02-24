package it.almaviva.rules.core.expressions.types.numerical;

import java.math.BigDecimal;

import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.core.statistcs.scostamento.ParzialeDipendenzaNumeric;
import it.almaviva.rules.core.statistcs.scostamento.ParzialeOperandoNumeric;
import it.almaviva.rules.core.statistcs.scostamento.ScostamentoBase;
import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoInputDTO;
import it.almaviva.rules.dipendenza.dto.OperandoDTO;
import it.almaviva.rules.dipendenza.utils.CurrencyUtils;
import it.almaviva.rules.exceptions.RulesException;


public abstract class NumericalExpression extends ExpressionBase {

	protected ExpressionResult result = null;
	
	public NumericalExpression(){}
	
	public NumericalExpression (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
		
		super(operando1,operando2,typeCtr);
	}
	
	
  
	
	@Override
	public ExpressionResult evaluate()throws RulesException {

		
       // boolean scostamento = getTypeCtr().getTipoParziale()!=null && !getTypeCtr().getTipoParziale().equals(""); 
        result = new ExpressionResult(getTypeCtr().getTolleranza(),getTypeCtr().isScostamento());
		
        
	        validate();
	        
	        buildOperando2();
	        
	        if (operando1 != null && operando2 != null) {
		        operando1.formatScaleCompareWith(operando2.getSommaImporti());
		        operando2.formatScaleCompareWith(operando1.getSommaImporti());
			}
                
		    evalueteNumericCondition();
		    
			getTypeCtr().setArithmeticRule(true);
			
			// Aggiunta del parziale se il controllo lo prevede						
			if(!getTypeCtr().isCondition() && (getTypeCtr().isScostamento())){					
				
			    boolean isOperandoToAdd= false;
			
			    ScostamentoInputDTO pDip = new ScostamentoInputDTO();
			    pDip.setErrorMessage(result.getErrorMessage());
			    pDip.setOp1valore(operando1.getSommaImporti());
			    pDip.setOp2valore(operando2.getSommaImporti());
			    pDip.setOperatore(getOperatore());
			    pDip.setSetKeyParziale(stringOperando1.concat(getOperatore()).concat(stringOperando2));
			    
				ParzialeDipendenzaNumeric parzialeDipendenzaNumeric = new ParzialeDipendenzaNumeric(pDip);				
				isOperandoToAdd = getTypeCtr().addScostamento(parzialeDipendenzaNumeric);
			
				
   		        //  Aggiunta del parziale degli Operandi se sono aggiunti gli Scostamenti				
				if(isOperandoToAdd){
					
					
					    ScostamentoInputDTO pOp1 = new ScostamentoInputDTO();
					    pOp1.setOpMessage(ScostamentoBase.LABEL_OPERANDO_1);
					    pOp1.setOpvalore(operando1.getSommaImporti());
					    pOp1.setSetKeyParziale(stringOperando1);	
					    pOp1.setOperandoToAdd(isOperandoToAdd);
					    
						ParzialeOperandoNumeric parzialeOperandoNumeric1 = new ParzialeOperandoNumeric(pOp1); 
						getTypeCtr().addScostamento(parzialeOperandoNumeric1);
						
						
						
					    ScostamentoInputDTO pOp2 = new ScostamentoInputDTO();
					    pOp2.setOpMessage(ScostamentoBase.LABEL_OPERANDO_2);;
					    pOp2.setOpvalore(operando2.getSommaImporti());
					    pOp2.setSetKeyParziale(stringOperando2);
					    pOp2.setOperandoToAdd(isOperandoToAdd);
					    
						ParzialeOperandoNumeric parzialeOperandoNumeric2 = new ParzialeOperandoNumeric(pOp2); 
						getTypeCtr().addScostamento(parzialeOperandoNumeric2);
						
				}
			}

		return result;
		
	  }		
	
	  /**
	   * 
	   */
	  public void validate() throws RulesException{
		  
	        if (stringOperando2 == null || stringOperando2.trim().equals("")) {
				throw new RulesException("Formula delle dipendenze campi pagina non valida: atteso secondo operando dopo l'operatore numerico ");
			}
	
		 
	  }
	
	
	  /*
	   * 
	   */
	  public void buildOperando2()throws RulesException{
		  
			operando2 = getTypeCtr().getOperando(stringOperando2, true);
		    
	  }
	  
	   protected abstract ExpressionResult evalueteNumericCondition(); 

	  
	   public String buildMessageOperando(OperandoDTO operando){
		
		  
		   if(operando.isArithmetic()){
			   if(operando.isConstantField()){
				   return " "+ buildMessageCostant(operando.getSommaImporti()) + " ";	 
			   }	
			   
			   else if(operando.getFieldLabel().startsWith(" risultante da (")){
				   return " il valore " + operando.getFieldLabel(); 
			   }
			   else{
			    	 return " "+ operando.getFieldLabel()+ " "	; 
			   }
		   } else {
			   
			   if(operando.isConstantField()){
				   return " "+ buildMessageCostant(operando.getSommaImporti()) + " ";	 
			   }		   
			   else if(operando.getFieldList().size()>1){
				   return " la somma degli importi dei dati " + operando.getFieldLabel() + " ";	    	 
			   }
			   else{
				   return " il valore "+(operando.isAbsoluteValue()?RulesConstants.ABSOLUTE_VALUE_LABEL:"" )+" del dato " + operando.getFieldLabel()+ " "	; 
			   }
			   
		   }

	    }
	   
	   
	   
	   
	   public String buildMessageOperando1(){
			 
		   return  buildMessageOperando(operando1);

	    }
	   
	   
	   public String buildMessageOperando2(){
		   
		  
		   if(operando2.isArithmetic()){
			   
			   if(operando2.isConstantField()){
				   return " di " + buildMessageCostant(operando2.getSommaImporti()) + " ";	 
			   }
			   
			   else if(operando2.getFieldLabel().startsWith(" risultante da (")){
				   return " del valore " + operando2.getFieldLabel(); 
			   }
			   else{
				   return " del " + operando2.getFieldLabel().substring(operando2.getFieldLabel().indexOf("il")+2) + " "	; 
			   }
			   
		   } else {
			   
			   if(operando2.isConstantField()){
				   return " di " + buildMessageCostant(operando2.getSommaImporti()) + " ";	 
			   }
			   else if(operando2.getFieldList().size()>1){
				   return " della somma degli importi dei dati " + operando2.getFieldLabel() + " " ;	    	 
			   }
			   else{
				   return " del valore "+(operando2.isAbsoluteValue()?RulesConstants.ABSOLUTE_VALUE_LABEL:"" ) +" del dato " + operando2.getFieldLabel() + " "	; 
			   }
			   
		   }

	    }	
	   
	   
	   /**
	    * 
	    * @param importo
	    * @return
	    */
	  protected String buildMessageCostant(BigDecimal importo) {
		  
		  return (importo.equals(BigDecimal.ZERO.setScale(importo.scale()))? "0" : CurrencyUtils.format(importo,importo.scale()));
 
	  }
	  
	  
	  
	}




