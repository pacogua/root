package it.almaviva.rules.core.expressions.operando;

import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.dipendenza.dto.OperandoDTO;
import it.almaviva.rules.dipendenza.utils.CurrencyUtils;
import it.almaviva.rules.exceptions.RulesException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;

public class OperandoPercentuale extends OperandoBase {
	//


	private DataFieldPageDTO field;
	private BigDecimal operando1;
	private BigDecimal   tot=new BigDecimal(0);
	private boolean constantField = false;
	private String importoPercent;


	@Override
	 OperandoDTO build()throws RulesException  {
	//
		
	    String fieldLabel =" ";		
	    OperandoDTO operandoDto = new OperandoDTO();
	    ArrayList<DataFieldPageDTO> fieldList =  new ArrayList<DataFieldPageDTO>();
	
	    try{
	//
			//mi trovo nel caso in cui ho un campo di tipoP101X3%k{
	//15}
			if(getStringToCheck().indexOf("%K{")>0 && getStringToCheck().endsWith("}")){
	//
				field = getController().getField(getStringToCheck().substring(0,getStringToCheck().indexOf("%K{")));
				operando1 = (field.getValue() == null || field.getValue().trim().equals(""))? new BigDecimal(0):CurrencyUtils.parseString(field.getValue(),Locale.ITALY,3);
				getController().addField(field.getFieldValue());
				
				importoPercent = getStringToCheck().substring(getStringToCheck().indexOf("K{")+2,getStringToCheck().indexOf("}"));
				if(importoPercent.startsWith("-") || importoPercent.startsWith("+")){
	//
					String segno = importoPercent.substring(0,1);
					importoPercent = importoPercent.substring(1).replaceAll(",", ".");
					evaluatePercent(operando1, importoPercent, segno);
					//mi devo costruire la label
					fieldLabel = buildLabel(field,importoPercent, segno);
				}
				else if(importoPercent.startsWith("ABS")){
	//
					String segno = "ABS";
					importoPercent = importoPercent.substring(3).replaceAll(",", ".");
					evaluatePercent(operando1, importoPercent, segno);
					//mi devo costruire la label
					fieldLabel = buildLabelABS(field,importoPercent);
				}
					
				else{
	//
					evaluatePercent(operando1, importoPercent.replaceAll(",", "."));
					//mi devo costruire la label
					fieldLabel = buildLabel(field,importoPercent);
				}
				
			}
			//mi trovo nel caso in cui ho un campo di tipo P101X3%P101X3
			else if(getStringToCheck().indexOf("%K{")==-1 && !getStringToCheck().endsWith("}")){
	//
				field = getController().getField(getStringToCheck().substring(0,getStringToCheck().indexOf("%")));
				operando1 = (field.getValue() == null || field.getValue().trim().equals(""))? new BigDecimal(0):CurrencyUtils.parseString(field.getValue(),Locale.ITALY,3);
				getController().addField(field.getFieldValue());
				
				String campo = getStringToCheck().substring(getStringToCheck().indexOf("%")+1);
				if(campo.startsWith("+") || campo.startsWith("-")){
	//
					DataFieldPageDTO campoPercentuale = getController().getField(campo.substring(1));
					getController().addField(campoPercentuale.getFieldValue());
					
					String segno = campo.substring(0,1);
					importoPercent = campoPercentuale.getValue().replaceAll(",", ".");
					evaluatePercent(operando1, importoPercent, segno);
					//mi devo costruire la label
					fieldLabel = buildLabel(field,campoPercentuale.getValue(), segno);
					getController().addField(campoPercentuale.getFieldValue());
				}
				else{
	//
					DataFieldPageDTO campoPercentuale = getController().getField(campo);
					getController().addField(campoPercentuale.getFieldValue());
					importoPercent = campoPercentuale.getValue().equals("")? "0": campoPercentuale.getValue().replaceAll(",", ".");
					evaluatePercent(operando1, importoPercent);
					//mi devo costruire la label
					fieldLabel = buildLabel(field,campoPercentuale.getValue());
				}
			}
		}
		catch(Exception e){
	//
			throw new  RulesException(" Il campo non � stato inserito nel formato corretto ");
		}
		fieldList.add(field); 
		operandoDto.setFieldLabel(fieldLabel);
		operandoDto.setMoreFields(false);
		operandoDto.setFieldList(fieldList);
		operandoDto.setSommaImporti(tot);
		operandoDto.setConstantField(constantField);
		return operandoDto;
	}

	private void evaluatePercent(BigDecimal operando1, String importoPercent, String segno){
	//
		if(segno.equals("-")){
	//
			BigDecimal percent = new BigDecimal(importoPercent);
			BigDecimal percentualeCalcolata = operando1.multiply(percent).divide(new BigDecimal(100));
			BigDecimal risultatoFinale = operando1.subtract(percentualeCalcolata);
			tot = risultatoFinale;
			
		}
		else if(segno.equals("+")){
	//
			BigDecimal percent = new BigDecimal(importoPercent);
			BigDecimal percentualeCalcolata = operando1.multiply(percent).divide(new BigDecimal(100));
			BigDecimal risultatoFinale = operando1.add(percentualeCalcolata);
			tot = risultatoFinale;
		}
		
		else if(segno.equals("ABS")){
	//
			BigDecimal percent = new BigDecimal(importoPercent);
			BigDecimal percentualeCalcolata = operando1.multiply(percent).divide(new BigDecimal(100));
			percentualeCalcolata=percentualeCalcolata.abs();
			tot = tot.add(percentualeCalcolata);	
		}
	}
	
	private void evaluatePercent(BigDecimal operando1, String importoPercent){
	//
		BigDecimal percent = new BigDecimal(importoPercent);
		BigDecimal percentualeCalcolata = operando1.multiply(percent).divide(new BigDecimal(100));
		tot = tot.add(percentualeCalcolata);	
	}
	
	private String buildLabel(DataFieldPageDTO operando1, String importoPercent, String segno){
	//
		
		return operando1.getLabel()+" "+segno+" "+importoPercent+"%";
	}
	
	private String buildLabelABS(DataFieldPageDTO operando1, String importoPercent){
	//
		
		return importoPercent+"% in valore assoluto del campo "+ operando1.getLabel();
	}
	
	private String buildLabel(DataFieldPageDTO operando1, String importoPercent){
	//
		return importoPercent+"% del campo "+ operando1.getLabel();
	}
	
	
	@Override
	public String getCode() {
	//
		return OPERANDO_PERCENTUALE;
	}
	
}
