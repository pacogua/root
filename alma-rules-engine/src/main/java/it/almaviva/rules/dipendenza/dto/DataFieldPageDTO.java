/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.almaviva.rules.dipendenza.dto;

import java.util.List;

import it.almaviva.rules.core.engine.Rule;
import it.almaviva.rules.core.expressions.Field;

/**
 *
 * @author Bitmedia_Vizzarro
 */
public class DataFieldPageDTO extends BaseDTO {
    
    private String id;   
    private String idCampo;   
    private String idStrQuestEnte;
    private String idQuestionario;
    private String value;
    private String valorePrecompilato;
    private String dataCreazione;
    private String utenteCreazione;
    private String dataAggiornamento;
    private String utenteAggiornamento;   
    private String type;    
    private String typeChecking;
    private String formula;
    private String progCampo;
    private String dipendenza;
    private String idLista;    
    private List<Rule> anagCtrList;
    private String label;
    private Field field;
    private String progPagina;  
    private String idPagina;
    private String nomeCampo;
    private String paragrafoOP;

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(String idPagina) {
		this.idPagina = idPagina;
	}

	public DataFieldPageDTO() {
    }

    public DataFieldPageDTO(String id, String idCampo, String idStrQuestEnte, String value, String type, String formula) {
        this.id = id;
        this.idCampo = idCampo;
        this.idStrQuestEnte = idStrQuestEnte;
        this.value = value;
        this.type = type;
        this.formula = formula;
    }

    public DataFieldPageDTO(String id, String value, String type, String typeChecking, String formula) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.typeChecking = typeChecking;
        this.formula = formula;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDipendenza() {
        return dipendenza;
    }

    public void setDipendenza(String dipendenza) {
        this.dipendenza = dipendenza;
    }
    
    public String getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(String idCampo) {
        this.idCampo = idCampo;
    }

    public String getIdStrQuestEnte() {
        return idStrQuestEnte;
    }

    public void setIdStrQuestEnte(String idStrQuestEnte) {
        this.idStrQuestEnte = idStrQuestEnte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTypeChecking() {
        return typeChecking;
    }

    public void setTypeChecking(String typeChecking) {
        this.typeChecking = typeChecking;
    }

    public String getDataAggiornamento() {
        return dataAggiornamento;
    }

    public void setDataAggiornamento(String dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    public String getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(String dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getUtenteAggiornamento() {
        return utenteAggiornamento;
    }

    public void setUtenteAggiornamento(String utenteAggiornamento) {
        this.utenteAggiornamento = utenteAggiornamento;
    }

    public String getUtenteCreazione() {
        return utenteCreazione;
    }

    public void setUtenteCreazione(String utenteCreazione) {
        this.utenteCreazione = utenteCreazione;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getProgCampo() {
        return progCampo;
    }

    public void setProgCampo(String progCampo) {
        this.progCampo = progCampo;
    }

	public String getIdLista() {
		return idLista;
	}

	public void setIdLista(String idLista) {
		this.idLista = idLista;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIdQuestionario() {
		return idQuestionario;
	}

	public void setIdQuestionario(String idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public void setAnagCtrList(List<Rule> anagCtrList) {
		this.anagCtrList = anagCtrList;
	}

	public List<Rule> getAnagCtrList() {
		return anagCtrList;
	}  
	

	public String getProgPagina() {
		return progPagina;
	}

	public void setProgPagina(String progPagina) {
		this.progPagina = progPagina;
	}

	public Field getFieldValue() {
		return field;
	}

	public void setFieldValue(Field field) {
		this.field = field;
	}   


   public String getParagrafoOP() {
		return paragrafoOP;
	}

	public void setParagrafoOP(String paragrafoOP) {
		this.paragrafoOP = paragrafoOP;
	}

	public String getValorePrecompilato() {
		return valorePrecompilato;
	}

	public void setValorePrecompilato(String valorePrecompilato) {
		this.valorePrecompilato = valorePrecompilato;
	}   



}
