package it.almaviva.rules.dipendenza.dto;

public class ControlloSirtelDto {



	private String quadro;
	private String segmento;
	private String valore;
	private String labelSegmento;
	private String labelQuadro;	
	

	@Override
	public boolean equals(Object obj) {
		//eq
		boolean eq = false;
		
		if(obj != null){
			if (obj instanceof ControlloSirtelDto) {
				if(getQuadro().equals(((ControlloSirtelDto)obj).getQuadro()) && getSegmento().equals(((ControlloSirtelDto)obj).getSegmento())){
					eq = true;
				}
			}
		} 
		
		return eq;
	}
	
	public String getQuadro() {
		//
		//get
		//
		return quadro;
	}
	public void setQuadro(String quadro) {
		//
		//set
		//
		this.quadro = quadro;
	}
	public String getSegmento() {
		//
		//get
		//
		return segmento;
	}
	public void setSegmento(String segmento) {
		//
		//set
		//
		this.segmento = segmento;
	}
	public String getValore() {
		//
		//get
		//
		return valore;
	}
	public void setValore(String valore) {
		//
		//set
		//
		this.valore = valore;
	}

	public String getLabelSegmento() {
		//
		//get
		//
		return labelSegmento;
	}

	public void setLabelSegmento(String labelSegmento) {
		//
		//set
		//
		this.labelSegmento = labelSegmento;
	}

	public String getLabelQuadro() {
		//
		//get
		//
		return labelQuadro;
	}

	public void setLabelQuadro(String labelQuadro) {
		//
		//set
		//
		this.labelQuadro = labelQuadro;
	}


	private void metriche(){
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
		// metriche
	}
}
