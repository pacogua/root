package it.almaviva.rules.dipendenza.dto;

import it.almaviva.rules.core.translator.BaseMsgFormulaProducer;
import it.almaviva.rules.core.engine.Rule;
import it.almaviva.rules.core.facts.DocumentBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RendicontoSirtelDocument extends DocumentBase {

  
	private Map<String, ControlloSirtelDto> cacheControlli = new HashMap<String,ControlloSirtelDto>();  
	private List<Rule> controlliNonApplicabili = new ArrayList<Rule>();	
	private boolean cachable = true;

     
     


	public boolean isCachable() {
		//is
		return cachable;
	}

	public void setCachable(boolean cachable) {
		//set
		this.cachable = cachable;
	}



	public List<Rule> getControlliNonApplicabili() {
		//get
		return controlliNonApplicabili;
	}

	public void setControlliNonApplicabili(
			//set
			List<Rule> controlliNonApplicabili) {
		this.controlliNonApplicabili = controlliNonApplicabili;
	}

	public Map<String, ControlloSirtelDto> getCacheControlli() {
		//get
		return cacheControlli;
	}

	public void setCacheControlli(Map<String, ControlloSirtelDto> cacheControlli) {
		//set
		this.cacheControlli = cacheControlli;
	}

     public void addSegmento(ControlloSirtelDto controlloSirtelDto){
    	 
    	 if(!getCacheControlli().containsValue(controlloSirtelDto)){
    		 //aggiunge
    		 getCacheControlli().put(controlloSirtelDto.getQuadro() + "-" + controlloSirtelDto.getSegmento(), controlloSirtelDto);
    	 }
     }

	public void add(BaseMsgFormulaProducer msgProducer) {
		// TODO Auto-generated method stub
		
	}

	
}
