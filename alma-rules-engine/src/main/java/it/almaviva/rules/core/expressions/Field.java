package it.almaviva.rules.core.expressions;



public class Field implements Comparable<Field> {
	//
	
	private int prgPage;
	private int prgField;
	private int prgQuadro;
	private int prgSegmento;
	private String titoloPagina;
	private String titoloSegmento;
	private String label;
	private boolean isAbsolute;
	
	
	public Field(){
	}
	
	public Field(int prgPage, int prgField, String label){
	//
		this.prgPage  = prgPage;
		this.prgField = prgField;
		this.label    = label;
	}
	
	public int compareTo(Field c){
	//
		
		int retVal = 0;
		if((this.prgQuadro != 0 && this.prgSegmento != 0) && (this.getPrgField() == 0 && this.getPrgPage() == 0)) {
	//
			
			if(this.prgQuadro < c.prgQuadro){
	//
				retVal = -1; 
			}
			else if(this.prgQuadro > c.prgQuadro){
	//
				retVal = 1; 
			}
			else{
	//
				
				if(this.prgSegmento < c.prgSegmento){
	//
					retVal = -1;
				}
				else if(this.prgSegmento > c.prgSegmento){
	//
					retVal = 1;
				}
				else{
	//
					retVal = 0; 
				}
			}			
		} else if(this.getPrgField() != 0 && this.getPrgPage() != 0){
	//
			
			if(this.prgPage < c.prgPage){
	//
				retVal = -1; 
			}
			else if(this.prgPage > c.prgPage){
	//
				retVal = 1; 
			}
			else{
	//
				
				if(this.prgField < c.prgField){
	//
					retVal = -1;
				}
				else if(this.prgField > c.prgField){
	//
					retVal = 1;
				}
				else{
	//
					retVal = 0; 
				}
			}
			
		}  
		
		return retVal;

	}
	
	
	public boolean equals(Object aThat) {
	//

	    if ( this == aThat ){
	//
	    	return true;
	    }
	
	    if ( !(aThat instanceof Field) ){
	//
	    	return false;
	    }

	    Field that = (Field)aThat;


	     if(that.getPrgSegmento() != 0){
	    	 return   this.prgQuadro == that.prgQuadro && this.prgSegmento == that.prgSegmento && this.isAbsolute== that.isAbsolute;
	     }
	     else{
	 	    return   this.prgPage == that.prgPage && this.prgField == that.prgField && this.isAbsolute== that.isAbsolute;	    	 
	     }
	    

	  }
	
	public int getPrgQuadro() {
	//
		return prgQuadro;
	}

	public void setPrgQuadro(int prgQuadro) {
	//
		this.prgQuadro = prgQuadro;
	}

	public int getPrgSegmento() {
	//
		return prgSegmento;
	}

	public void setPrgSegmento(int prgSegmento) {
	//
		this.prgSegmento = prgSegmento;
	}

	public int getPrgPage() {
	//
		return prgPage;
	}
	public void setPrgPage(int prgPage) {
	//
		this.prgPage = prgPage;
	}
	public int getPrgField() {
	//
		return prgField;
	}
	public void setPrgField(int prgField) {
	//
		this.prgField = prgField;
	}
	public String getLabel() {
	//
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	/**
	 * @return the titoloPagina
	 */
	public String getTitoloPagina() {
		return titoloPagina;
	}

	/**
	 * @param the titoloPagina to set
	 */
	public void setTitoloPagina(String titoloPagina) {
		this.titoloPagina = titoloPagina;
	}
	
	

	/**
	 * @return the titoloSegmento
	 */
	public String getTitoloSegmento() {
		return titoloSegmento;
	}

	/**
	 * @param the titoloSegmento to set
	 */
	public void setTitoloSegmento(String titoloSegmento) {
		this.titoloSegmento = titoloSegmento;
	}
		

	public boolean isAbsolute() {
		return isAbsolute;
	}

	public void setAbsolute(boolean isAbsolute) {
		this.isAbsolute = isAbsolute;
	}
	
	
}