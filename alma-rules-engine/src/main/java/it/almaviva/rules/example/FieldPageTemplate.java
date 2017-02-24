package it.almaviva.rules.example;


import java.util.HashMap;

import it.almaviva.rules.core.expressions.Field;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.dipendenza.dto.PaginaDocument;
import it.almaviva.rules.dipendenza.utils.CurrencyUtils;
import it.almaviva.rules.exceptions.RulesException;
import it.almaviva.rules.core.facts.DocumentBase;
import it.almaviva.rules.core.translator.LiteralMsgFormulaProducer;

public class FieldPageTemplate extends OperandoTemplate {

	
//	public CtrField(){
//	}
	

	public FieldPageTemplate(DocumentBase document){
		super(document);
	}
	
	
	
	public DataFieldPageDTO getField(String indexF)throws RulesException {
		//
		// getField
		//
		
		PaginaDocument pagina = (PaginaDocument) getDocument();
		HashMap<String,DataFieldPageDTO> hashData = pagina.getHashData();

		DataFieldPageDTO field;
		
			if(!CurrencyUtils.isInt(indexF)){                    	  
				throw new RulesException(" Formula Dipendenze non valida. Atteso un progressivo campo in formato numerico ["+indexF +"]");                    	 
			}
		
			field      = hashData.get(indexF);
		   
			
			if(field==null){                            
			 throw new RulesException(" Formula Dipendenze non valida. Progressivo campo "+ indexF+" non valido per la pagina del questionario selezionata");                            
			}  
									
			field.setLabel(LiteralMsgFormulaProducer.buildFieldName(indexF,field.getNomeCampo()));		
			
			Field f = new  Field();
			f.setPrgPage(pagina.getPrgPage().intValue());  
			f.setPrgField(Integer.parseInt(indexF));  			
			f.setLabel(LiteralMsgFormulaProducer.buildPageFieldLabel(pagina.getTitolo(),field.getLabel(),field.getValue()));
			
			field.setFieldValue(f);
	
	   // return;
	   return field;	
	}
	
	

}
