package it.almaviva.rules.core.statistcs.scostamento;

import java.math.BigDecimal;
import java.math.RoundingMode;

import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoInputDTO;
import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoOutDTO;

public class ParzialeDipendenzaNumeric extends ScostamentoBase {

	
	
	public ParzialeDipendenzaNumeric(ScostamentoInputDTO dtoInput){
		super(dtoInput);
	}
	
	
		@Override
		public ScostamentoOutDTO getParzialeErroreContabileDto() {
		
             
			BigDecimal valore;		
			ScostamentoOutDTO parzialeErrore = new ScostamentoOutDTO();
					
					
					parzialeErrore.setKeyParziale(super.getInputDTO().getSetKeyParziale());
					parzialeErrore.setDescrizione(super.getInputDTO().getErrorMessage());
					parzialeErrore.setOperatore(super.getInputDTO().getOperatore());
					parzialeErrore.setTipologiaParziale(PARZIALE_SCOSTAMENTO);
					
					valore = super.getInputDTO().getOp1valore().subtract(super.getInputDTO().getOp2valore()).abs();
					valore = valore.setScale(2,RoundingMode.HALF_UP);					
				    parzialeErrore.setValore(valore);
							
				    
			 return parzialeErrore;
			
	
		}

	
		

		

}
