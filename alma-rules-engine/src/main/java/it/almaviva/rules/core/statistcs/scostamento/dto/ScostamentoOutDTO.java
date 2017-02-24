package it.almaviva.rules.core.statistcs.scostamento.dto;

import it.almaviva.rules.core.statistcs.scostamento.ScostamentoBase;
import it.almaviva.rules.dipendenza.utils.CurrencyUtils;

import java.math.BigDecimal;
import java.util.Locale;


public class ScostamentoOutDTO implements Comparable<ScostamentoOutDTO> {



	private String keyParziale;
    private BigDecimal valore;
    private String descrizione;
    private String tipologiaParziale;
    private String operatore;  
    private int progressivo;		


	public ScostamentoOutDTO(){
	}
	

	/**
	 * 
	 */
	public int compareTo(ScostamentoOutDTO c){
		
		if(c != null){
			if(this.progressivo < c.progressivo){
				return -1; 
	 		}
			else if(this.progressivo > c.progressivo){
				return 1; 
			}
			else{
				return 0;
			}	
		}else{
			return 1;
		}
			
	}
	
	
  /**
   * 
   */
	public boolean equals(Object aThat) {
	//

	    if ( this == aThat ){
	//
	    	return true;
	    }
	
	    if ( !(aThat instanceof ScostamentoOutDTO) ){
	//
	    	return false;
	    }

	    ScostamentoOutDTO that = (ScostamentoOutDTO)aThat;
   
	     return   this.keyParziale.equals(that.keyParziale) ;	    	 

	    

	  }


	public BigDecimal getValore() {
	return valore;
}


public void setValore(BigDecimal valore) {
	this.valore = valore;
}


	public String getDescrizione() {
		return descrizione;
	}



	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	public String getTipologiaParziale() {
		return tipologiaParziale;
	}



	public void setTipologiaParziale(String tipologiaParziale) {
		this.tipologiaParziale = tipologiaParziale;
	}



	public String getOperatore() {
		return operatore;
	}



	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}



	public String getKeyParziale() {
		return keyParziale;
	}



	public void setKeyParziale(String keyParziale) {
		this.keyParziale = keyParziale;
	}


	public int getProgressivo() {
		return progressivo;
	}


	public void setProgressivo(int progressivo) {
		this.progressivo = progressivo;
	}
	
	//il metodo mi restituisce descrizione = valore per il dettaglio degli errori
	public String getDescrXerrori() {
		if(descrizione !=null && descrizione.equalsIgnoreCase(ScostamentoBase.LABEL_OPERANDO_1) || descrizione.equalsIgnoreCase(ScostamentoBase.LABEL_OPERANDO_2)){
			return " - " + CurrencyUtils.format(valore.doubleValue(), Locale.ITALY, 2) + " (" + descrizione + ")";
		}	
		else{
			return " " + CurrencyUtils.format(valore.doubleValue(), Locale.ITALY, 2) + " - " + descrizione;
		}	
	}
	
	
}