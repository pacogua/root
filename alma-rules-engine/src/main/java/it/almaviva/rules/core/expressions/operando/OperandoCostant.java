package it.almaviva.rules.core.expressions.operando;

import it.almaviva.rules.dipendenza.dto.OperandoDTO;
import it.almaviva.rules.dipendenza.utils.CurrencyUtils;
import it.almaviva.rules.exceptions.RulesException;

import java.math.BigDecimal;
import java.util.Locale;

public class OperandoCostant extends OperandoBase {
	
	
	public OperandoCostant (){}
	
	
	public OperandoCostant (String  stringToCheck, boolean dipendenzaNumeric, OperandoTemplate controller){
		super(stringToCheck, dipendenzaNumeric,controller);
	}
	
	@Override
    OperandoDTO build()throws RulesException {
    //
	  stringToCheck = stringToCheck.substring(2,stringToCheck.length()-1);			
      BigDecimal   tot=new BigDecimal(0);
	  boolean constantField = true;
    
	  OperandoDTO operandoDto = new OperandoDTO();
		//
		if(isDipendenzaNumeric()){					
			try{	
				//
				 BigDecimal fNum  = (getStringToCheck() == null || getStringToCheck().trim().equals(""))? new BigDecimal(0):CurrencyUtils.parseString(getStringToCheck(),Locale.ITALY,3);					
			     tot = tot.add(fNum) ;
			 }catch(Exception e){
				 //
				 throw new  RulesException(" Il campo non � stato inserito nel formato corretto ");
			 }
		}else {
			operandoDto.setValueConstant(getStringToCheck());
		}
		//
		operandoDto.setMoreFields(false);
		operandoDto.setSommaImporti(tot);
		operandoDto.setConstantField(constantField);
		return operandoDto;
	}

	@Override
	public String getCode() {
		//
		// set
		//
		return OPERANDO_COSTANT;
	}

}
