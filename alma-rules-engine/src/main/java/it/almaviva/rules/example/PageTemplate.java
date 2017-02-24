package it.almaviva.rules.example;


import it.almaviva.rules.core.expressions.Field;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.core.facts.DocumentBase;
import it.almaviva.rules.core.translator.LiteralMsgFormulaProducer;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.dipendenza.dto.PaginaDocument;
import it.almaviva.rules.dipendenza.dto.QuestionarioDocument;
import it.almaviva.rules.dipendenza.utils.CurrencyUtils;
import it.almaviva.rules.exceptions.RulesException;

public class PageTemplate extends OperandoTemplate {

	///  da settare ogni volta che si chiama il validatore della dipendenza 
	private String currentPageIndex;

    public static final String CODICE_SISTEMA = "S";
    public static final String CODICE_MODELLO = "Q";
    public static final String CODICE_PAGINA = "P";
    public static final String CODICE_CAMPO = "X";
    public static final String CODICE_QUADRO = "S";
    public static final String CODICE_SEGMENTO = "X";
    public static final String CODICE_ANNO = "Y";
	


	public PageTemplate(DocumentBase document){
		super(document);
	}
	
	public PageTemplate(DocumentBase document, String currentPageIndex){
		super(document);
		this.currentPageIndex = currentPageIndex;
	}



	@Override
	public DataFieldPageDTO getField(String indexF)throws RulesException { 
		
		QuestionarioDocument questionario = (QuestionarioDocument) getDocument();
		
		PaginaDocument currentPage = questionario.getListPagineQuest().get(currentPageIndex);
		
		DataFieldPageDTO field;
		String labelCampo="";
		
		if(currentPage==null){
		   throw new RulesException("CtrPage Error: pagina null o indice non valido");				
		}

		
       //TODO --> ilmetodo leggera' la parte della formula relativa al campo es: p1x2 -- (p1x3,9)
		/**
		  
		  - se indexF e' un intero allora vuol dire che mi riferisco al campo della pagina corrente
		  
		  - altrimenti indexF deve essere scritta nel formato "p?x?"  
               -->  mi recupero  pagina e  campo e  e faccio quel che fa il metodo getField(...) di CtrField.
                    con la differenza che quando setto la label, questa sara' composta da progressivo pagina + titolo pagina
                    Es:
                       1 della pagina 1.5 Pagina di sintesi
		
		****************************************************************/
		//campo isnerito da Irene Marino per diversificare il nome del campo a seconda se e'
		//un campo della pagina di riferimento
		//oppure un campo ottenuto da una pagina diversa da quella di riferimento
		String nomeCampo ="";
		if(CurrencyUtils.isInt(indexF.substring(0, 1))){
			field = currentPage.getHashData().get(indexF);
			nomeCampo  = indexF;
			labelCampo = LiteralMsgFormulaProducer.buildFieldName(indexF,field.getNomeCampo());
			
			field.setLabel(labelCampo+" della pagina '"+currentPage.getTitolo()+"'");
		}
		else if (indexF.substring(0, 1).equals(RulesConstants.CODICE_PAGINA)){
		
			try{
				String idPagina = indexF.substring(1, indexF.indexOf(CODICE_CAMPO));
				PaginaDocument pagina =questionario.getListPagineQuest().get((indexF.substring(1, indexF.indexOf(CODICE_CAMPO))));
				field= pagina.getHashData().get(indexF.substring(indexF.indexOf(CODICE_CAMPO)+1));
				nomeCampo = field.getProgCampo();
				labelCampo = LiteralMsgFormulaProducer.buildFieldName(indexF.substring(indexF.indexOf(CODICE_CAMPO)+1),field.getNomeCampo());
				
				field.setLabel(labelCampo+" della pagina '"+pagina.getTitolo()+"'");
			}
			catch(Exception e){
				throw new RulesException("ctrPage error: pagina o campo non valido !"); 
			}
		}
		else{
			throw new RulesException(" Formula Dipendenze non valida. Progressivo campo "+ indexF+" non valido per la pagina del questionario selezionata");
		}
		if(field==null){                          
			throw new RulesException(" Formula Dipendenze non valida. Progressivo campo "+ indexF+" non valido per la pagina del questionario selezionata");                            
		}	
		
		Field f = new  Field(); 
		f.setPrgPage(questionario.getListPagineQuest().get(field.getIdPagina()).getPrgPage().intValue());
		f.setPrgField(Integer.parseInt(nomeCampo));  
		f.setLabel(LiteralMsgFormulaProducer.buildPageFieldLabel(questionario.getListPagineQuest().get(field.getIdPagina()).getTitolo(),labelCampo,field.getValue()));		
		
		field.setFieldValue(f);
		
		
		
		return field;
		
	}

	public String getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(String currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

}
