/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.almaviva.rules.dipendenza.dto;

import java.util.List;

import it.almaviva.rules.core.engine.Rule;
import it.almaviva.rules.core.expressions.Field;



public abstract class DataFieldPageBaseDecorator extends DataFieldPageDTO {
    
	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unused")
	private DataFieldPageDTO dataFieldPageAbs;
	
	
	public DataFieldPageBaseDecorator(DataFieldPageDTO dataFieldPage ){
		
		dataFieldPageAbs = dataFieldPage;		
		
	}


	@Override
	public List<Rule> getAnagCtrList() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getAnagCtrList();
	}


	@Override
	public String getDataAggiornamento() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getDataAggiornamento();
	}


	@Override
	public String getDataCreazione() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getDataCreazione();
	}


	@Override
	public String getDipendenza() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getDipendenza();
	}


	@Override
	public Field getFieldValue() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getFieldValue();
	}


	@Override
	public String getFormula() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getFormula();
	}


	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getId();
	}


	@Override
	public String getIdCampo() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getIdCampo();
	}


	@Override
	public String getIdLista() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getIdLista();
	}


	@Override
	public String getIdPagina() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getIdPagina();
	}


	@Override
	public String getIdQuestionario() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getIdQuestionario();
	}


	@Override
	public String getIdStrQuestEnte() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getIdStrQuestEnte();
	}


	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getLabel();
	}


	@Override
	public String getNomeCampo() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getNomeCampo();
	}


	@Override
	public String getParagrafoOP() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getParagrafoOP();
	}


	@Override
	public String getProgCampo() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getProgCampo();
	}


	@Override
	public String getProgPagina() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getProgPagina();
	}


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getType();
	}


	@Override
	public String getTypeChecking() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getTypeChecking();
	}


	@Override
	public String getUtenteAggiornamento() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getUtenteAggiornamento();
	}


	@Override
	public String getUtenteCreazione() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getUtenteCreazione();
	}


	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return dataFieldPageAbs.getValue();
	}


	@Override
	public void setAnagCtrList(List<Rule> anagCtrList) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setAnagCtrList(anagCtrList);
	}


	@Override
	public void setDataAggiornamento(String dataAggiornamento) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setDataAggiornamento(dataAggiornamento);
	}


	@Override
	public void setDataCreazione(String dataCreazione) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setDataCreazione(dataCreazione);
	}


	@Override
	public void setDipendenza(String dipendenza) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setDipendenza(dipendenza);
	}


	@Override
	public void setFieldValue(Field field) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setFieldValue(field);
	}


	@Override
	public void setFormula(String formula) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setFormula(formula);
	}


	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setId(id);
	}


	@Override
	public void setIdCampo(String idCampo) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setIdCampo(idCampo);
	}


	@Override
	public void setIdLista(String idLista) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setIdLista(idLista);
	}


	@Override
	public void setIdPagina(String idPagina) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setIdPagina(idPagina);
	}


	@Override
	public void setIdQuestionario(String idQuestionario) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setIdQuestionario(idQuestionario);
	}


	@Override
	public void setIdStrQuestEnte(String idStrQuestEnte) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setIdStrQuestEnte(idStrQuestEnte);
	}


	@Override
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setLabel(label);
	}


	@Override
	public void setNomeCampo(String nomeCampo) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setNomeCampo(nomeCampo);
	}


	@Override
	public void setParagrafoOP(String paragrafoOP) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setParagrafoOP(paragrafoOP);
	}


	@Override
	public void setProgCampo(String progCampo) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setProgCampo(progCampo);
	}


	@Override
	public void setProgPagina(String progPagina) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setProgPagina(progPagina);
	}


	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setType(type);
	}


	@Override
	public void setTypeChecking(String typeChecking) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setTypeChecking(typeChecking);
	}


	@Override
	public void setUtenteAggiornamento(String utenteAggiornamento) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setUtenteAggiornamento(utenteAggiornamento);
	}


	@Override
	public void setUtenteCreazione(String utenteCreazione) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setUtenteCreazione(utenteCreazione);
	}


	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		dataFieldPageAbs.setValue(value);
	}
	
	

	
}

