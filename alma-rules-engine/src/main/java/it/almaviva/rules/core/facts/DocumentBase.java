package it.almaviva.rules.core.facts;

import it.almaviva.rules.core.translator.BaseMsgFormulaProducer;



public abstract  class DocumentBase {


	
	private BaseMsgFormulaProducer msgProducer;
	
	public DocumentBase(){}

	public BaseMsgFormulaProducer getMsgProducer() {
		return msgProducer;
	}

	public void setMsgProducer(BaseMsgFormulaProducer msgProducer) {
		this.msgProducer = msgProducer;
	}
	

	
}
