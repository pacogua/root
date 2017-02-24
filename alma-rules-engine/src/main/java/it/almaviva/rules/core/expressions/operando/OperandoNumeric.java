package it.almaviva.rules.core.expressions.operando;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;

import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.dipendenza.dto.DataFieldPageAbsDecorator;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.dipendenza.dto.OperandoDTO;
import it.almaviva.rules.dipendenza.utils.CurrencyUtils;
import it.almaviva.rules.exceptions.RulesException;

public class OperandoNumeric extends OperandoBase {

	
	public OperandoNumeric(String  stringToCheck,boolean dipendenzaNumeric, OperandoTemplate controller){
		//
		super(stringToCheck, dipendenzaNumeric,controller);
		//
		//
		//
	}
	
	
	@Override
	OperandoDTO build() throws RulesException {
		//

	   BigDecimal   tot=new BigDecimal(0);
	   boolean constantField = false;
	   DataFieldPageDTO field;
	   boolean moreFields    = false;
       String fieldLabel =" ";
       boolean absoluteValue =false;
		//
       ArrayList<DataFieldPageDTO> fieldList =  new ArrayList<DataFieldPageDTO>();
//
	  OperandoDTO operandoDto = new OperandoDTO();

  		
			
			/*
			 * In caso di valore assoluto si richiama
			 * un DEcorator che gestisce il valore e la label
			 */  
		      
			 if( getStringToCheck().startsWith(RulesConstants.ABSOLUTE_VALUE)){		    	   
				 
				 field  = new DataFieldPageAbsDecorator(getController().getField(getStringToCheck().substring(RulesConstants.ABSOLUTE_VALUE.length())));
				 // field  =  getController().getField(getStringToCheck().substring(DipendenzaInterface.ABSOLUTE_VALUE.length())).toAbsoluteValue();
			     absoluteValue = true;
		     }else{		    	 
			     field  =  getController().getField(getStringToCheck());	 
		     }
			 			
			 
			 if(isDipendenzaNumeric()){					
				try{	
					BigDecimal fNum   = (field.getValue() == null || field.getValue().trim().equals(""))? new BigDecimal(0):CurrencyUtils.parseString(field.getValue(),Locale.ITALY,3);					
	    			tot = tot.add(fNum) ;
				}catch(Exception e){
					throw new  RulesException("Errore di conversione numerica per parametro: "+field.getLabel() +" Atteso valore numerico trovato: "+field.getValue()  ); 
							
				}
			}				
	
			fieldLabel   += field.getLabel();
			getController().addField(field.getFieldValue());
			fieldList.add(field); 
	
		operandoDto.setFieldLabel(fieldLabel);
		operandoDto.setMoreFields(moreFields);
		operandoDto.setFieldList(fieldList);
		operandoDto.setSommaImporti(tot);
		operandoDto.setConstantField(constantField);
		operandoDto.setAbsoluteValue(absoluteValue);

		return operandoDto;



	}

	@Override
	public String getCode() {
		return null;

	}

   
}
