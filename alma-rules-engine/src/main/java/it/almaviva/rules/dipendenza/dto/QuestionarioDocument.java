package it.almaviva.rules.dipendenza.dto;

import it.almaviva.rules.core.facts.DocumentBase;

import java.util.HashMap;

public class QuestionarioDocument extends DocumentBase {

    private String idQuestEnte;   


    private HashMap<String, PaginaDocument> listPagineQuest = new HashMap<String, PaginaDocument>();
    
    
    public HashMap<String, PaginaDocument> getListPagineQuest() {
    	//get
		return listPagineQuest;
	}

	public void setListPagineQuest(HashMap<String, PaginaDocument> listPagineQuest) {
    	//set
		this.listPagineQuest = listPagineQuest;
	}

	public QuestionarioDocument() {
    }

	public String getIdQuestEnte() {
    	//get
		return idQuestEnte;
	}

	public void setIdQuestEnte(String idQuestEnte) {
    	//set
		this.idQuestEnte = idQuestEnte;
	}

	
	public void addField(PaginaDocument paginaDocument, String progCampo){
		
		
		
		 if(!getListPagineQuest().containsValue(paginaDocument)){
    		 //aggiunge
    		 getListPagineQuest().put(paginaDocument.getIdPagina().toString(), paginaDocument);

    	 }
		 else{
			 PaginaDocument paginaOld;
			 paginaOld = getListPagineQuest().get(paginaDocument.getIdPagina().toString());
			 HashMap<String, DataFieldPageDTO> campiPaginaOld = paginaOld.getHashData();
			 
			 DataFieldPageDTO campiPaginaNew = paginaDocument.getHashData().get(progCampo);
			 
             campiPaginaOld.put(progCampo, campiPaginaNew);
             
             paginaOld.setHashData(campiPaginaOld);   
			 			 
			 getListPagineQuest().put(paginaDocument.getIdPagina().toString(), paginaOld);
			 
		 }
		

	}
	

	






	
	
	
}
