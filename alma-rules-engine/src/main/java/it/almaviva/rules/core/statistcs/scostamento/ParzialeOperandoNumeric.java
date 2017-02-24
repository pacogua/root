package it.almaviva.rules.core.statistcs.scostamento;

import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoInputDTO;
import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoOutDTO;

public class ParzialeOperandoNumeric extends ScostamentoBase {

	
	
	public ParzialeOperandoNumeric(ScostamentoInputDTO dtoInput){
		super(dtoInput);
	}
	
	
	@Override
	public ScostamentoOutDTO getParzialeErroreContabileDto() {
		
		
		 ScostamentoOutDTO parzialeErrore = new ScostamentoOutDTO();					
					
		 parzialeErrore.setKeyParziale(super.getInputDTO().getSetKeyParziale());					
		 parzialeErrore.setDescrizione(super.getInputDTO().getOpMessage());						
		 parzialeErrore.setOperatore(null);
		 parzialeErrore.setTipologiaParziale(PARZIALE_SCOSTAMENTO_OPERANDO);
	     parzialeErrore.setValore( super.getInputDTO().getOpvalore() );
				
	  return parzialeErrore;
			
		
	}

}
