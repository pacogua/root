package it.almaviva.rules.core.statistcs.scostamento;


import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoInputDTO;
import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoOutDTO;

public abstract class ScostamentoBase  {

	
    public static final String  PARZIALE_SCOSTAMENTO = "1";
   // public static final String  PARZIALE_OPERANDO = "2";
    public static final String  PARZIALE_SCOSTAMENTO_OPERANDO = "3";
    
    public static final String  PARZIALE_SCOSTAMENTO_DESCRIZIONE = "Scostamento";
    public static final String  PARZIALE_OPERANDO_DESCRIZIONE = "Operando";
    public static final String  PARZIALE_SCOSTAMENTO_OPERANDO_DESCRIZIONE = "Scostamento/Operando";

    public static final String  LABEL_OPERANDO_1 = "Primo Operando";
    public static final String  LABEL_OPERANDO_2 = "Secondo Operando";
	
	private OperandoTemplate ctr;
	private ScostamentoInputDTO inputDTO;
	
	
	
	
	public ScostamentoBase(ScostamentoInputDTO dtoInput){
		this.inputDTO = dtoInput;
	}
	
	

    /**
     * 
     * @return
     */
	public ScostamentoOutDTO build(){
		
//		if(ctr.getTipoParziale()==null){
//			return null;
//			
//		}
//		
	
		if(!ctr.isScostamento()){
			return null;			
		}
	
		
		ScostamentoOutDTO p = getParzialeErroreContabileDto();
		
		if(p!=null){
		  p.setProgressivo(ctr.nextPrgParziale());
		}
		return p;
		
	}
	
	
	protected abstract ScostamentoOutDTO getParzialeErroreContabileDto();
	
	
	public OperandoTemplate getCtr() {
		return ctr;
	}

	public void setCtr(OperandoTemplate ctr) {
		this.ctr = ctr;
	}



	public ScostamentoInputDTO getInputDTO() {
		return inputDTO;
	}



	public void setInputDTO(ScostamentoInputDTO inputDTO) {
		this.inputDTO = inputDTO;
	}



	
}
