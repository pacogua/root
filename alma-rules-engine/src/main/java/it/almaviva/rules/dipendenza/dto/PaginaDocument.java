package it.almaviva.rules.dipendenza.dto;



import it.almaviva.rules.core.facts.DocumentBase;

import java.util.HashMap;


public class PaginaDocument  extends DocumentBase{

	
    private Long idPagina;
    private String titolo;
    private Long prgPage;
    private HashMap<String,DataFieldPageDTO> hashData = new HashMap<String,DataFieldPageDTO>();    


	
	@Override
	public boolean equals(Object obj) {
		//eq
		boolean eq = false;
		
		if(obj != null){
			if (obj instanceof PaginaDocument) {
				if(getIdPagina().compareTo(((PaginaDocument)obj).getIdPagina())==0){
					eq = true;
				}
			}
		} 
		
		return eq;
	}
	
	


	public Long getIdPagina() {
		//get
		return idPagina;
	}

	public void setIdPagina(Long idPagina) {
		//set
		this.idPagina = idPagina;
	}

	public String getTitolo() {
		//get
		return titolo;
	}

	public void setTitolo(String titolo) {
		//set
		this.titolo = titolo;
	}

	public HashMap<String, DataFieldPageDTO> getHashData() {
		//get
		return hashData;
	}

	public void setHashData(HashMap<String, DataFieldPageDTO> hashData) {
		//set
		this.hashData = hashData;
	}

	public Long getPrgPage() {
		//get
		return prgPage;
	}

	public void setPrgPage(Long prgPage) {
		//set
		this.prgPage = prgPage;
	}





	


	
	
	
}
