package it.almaviva.rules.core.expressions.types.date;

import it.almaviva.rules.core.expressions.ExpressionBase;
import it.almaviva.rules.core.expressions.ExpressionResult;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.dipendenza.dto.DateUtilsBusiness;
import it.almaviva.rules.dipendenza.utils.FormatDate;
import it.almaviva.rules.exceptions.RulesException;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;


public abstract class DateBase extends ExpressionBase {

	Date date1 ; 
	Date date2 ;
	String date2String;
	boolean moreField;
	protected String currentPrgField;

	
	public DateBase(){}
	
	public DateBase (String operando1, String operando2, 
			OperandoTemplate typeCtr)throws RulesException{
		
		super(operando1,operando2,typeCtr);
		
		
	}
	
	
	
	@Override
	public ExpressionResult evaluate()throws RulesException {
		
        if(stringOperando2== null || stringOperando2.trim().equals("")){                       
            throw new RulesException("Formula delle dipendenze campi pagina non valida: atteso secondo operando dopo l'operatore date ");
		}
	
        operando2 =  getTypeCtr().getOperando(stringOperando2, false);   
        
       try{
        
		if(operando2.isConstantField()){
			
			if(operando2.getValueConstant().equalsIgnoreCase(RulesConstants.TODAY)){				
				date2 =	DateUtilsBusiness.stringToDate(DateUtilsBusiness.dateToString(new Date(), FormatDate.getDATE_FORMAT()), FormatDate.getDATE_FORMAT());
			}
			else{
				date2 = DateUtilsBusiness.stringToDate(operando2.getValueConstant(),FormatDate.getDATE_FORMAT());				
			}
		}	
		else{
		  	 date2 = DateUtilsBusiness.stringToDate(operando2.getFieldList().get(0).getValue(),FormatDate.getDATE_FORMAT());
		}
    
  
		moreField =  (operando1.getFieldList().size()>1)?true:false;
		// Rimuove i valori vuoti			
		Iterator<DataFieldPageDTO> it = operando1.getFieldList().iterator();
		
		 while (it.hasNext()){
			 DataFieldPageDTO field  = it.next();			 
			 if(field.getValue() == null || field.getValue().equals("")){
				 it.remove();
			 }			 
		 }
						
		 
         return evalueteDateCondition();
        
       }
       catch(ParseException e){
		  	throw new RulesException(" Il campo "+ currentPrgField +" deve essere inserito nel formato data gg/mm/aaaa");		
	   }	
       catch(Exception e){
		  	throw new RulesException(" Errore in Formula dipendenza");		
	   }			
	 }		
	
	
	   protected abstract ExpressionResult evalueteDateCondition()throws Exception; 

	   
	   
	   public String buildMessageOperando1(){
			   
		   if(operando1.isConstantField()){
			   return " "+ operando1.getValueConstant() + " ";	 
		   }	  	   
		   else if(moreField){
		    	 return " la data dei campi " + operando1.getFieldLabel() + " ";	    	 
		   }
		   else{
		    	 return " la data del campo " + operando1.getFieldLabel()+ " "	; 
		   }

	    }
	   
	   
	   public String buildMessageOperando2(){
		   
		     if(operando2.isConstantField()){
		    	 
		    	 if(operando2.getValueConstant().equalsIgnoreCase(RulesConstants.TODAY)){
		    		 return " della data corrente ";
		    	 }
		    	 else{
		    		 return " della data " + operando2.getValueConstant() + " "; 
		    	 }			   	
		     }
		     else{
		    	 return " della data indicata nel campo " + operando2.getFieldLabel() + " "	; 
		     }

	    }	   
	   
	   
	}




