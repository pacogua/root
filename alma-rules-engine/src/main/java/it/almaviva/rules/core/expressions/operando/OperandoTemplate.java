package it.almaviva.rules.core.expressions.operando;



import it.almaviva.rules.core.expressions.Field;
import it.almaviva.rules.core.expressions.RulesConstants;
import it.almaviva.rules.core.facts.DocumentBase;
import it.almaviva.rules.core.statistcs.scostamento.ScostamentoBase;
import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoOutDTO;
import it.almaviva.rules.core.translator.BaseMsgFormulaProducer;
import it.almaviva.rules.core.translator.RulesConstantEncoder;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.dipendenza.dto.OperandoDTO;
import it.almaviva.rules.dipendenza.utils.Trace;
import it.almaviva.rules.exceptions.RulesException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.java.dev.eval.Expression;


public abstract class OperandoTemplate {

    private ArrayList<Field> fields = new ArrayList<Field>();
    
    private ArrayList<ScostamentoOutDTO> scostamenti = new ArrayList<ScostamentoOutDTO>();
    
	private final DocumentBase document;
	
	private boolean condition;
	
	//private String tipoParziale;
	private boolean scostamento;
	


	private int prgParziale;
	
	private boolean arithmeticRule;
	
    private BigDecimal tolleranza = new BigDecimal(0);	
    
	protected String labelField=" campo ";

    private String motivoNonApplicabilita;    
    
    private boolean erroreGestito;



	
	public OperandoTemplate(DocumentBase document){
		this.document = document;
	}
	
	
	public abstract DataFieldPageDTO getField(String indexF)throws RulesException ;
	
	
	/**
	 * 
	 */
	public void reset(){
		
		setMotivoNonApplicabilita(null);
		setErroreGestito(false);
		
	}
	
	
	
	/**
	 * 
	 * @param baseMsgFormulaProducer
	 * @throws RulesException
	 */
	public void addMsgFormulaProducer(BaseMsgFormulaProducer baseMsgFormulaProducer)throws RulesException{
		
		  if(document!=null){			
			  document.setMsgProducer(baseMsgFormulaProducer);			  
		  }
		  else{
			 throw new RulesException("Operazione non consentita. Assegnare prima un Document");
		  }
		
	}
	
	/**
	 * 
	 * @return
	 */
	public BaseMsgFormulaProducer getMsgFormulaProducer(){
		
		return document.getMsgProducer();
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public BaseMsgFormulaProducer getMsgFormulaProducerCleaned(){
		
		BaseMsgFormulaProducer baseMsgFormulaProducer= document.getMsgProducer();
		baseMsgFormulaProducer.reset();
		
		return baseMsgFormulaProducer;
	}
	

	/** 
	 * 
	 * @param fields
	 * @param dipendenzaNumeric
	 * @return
	 * @throws RulesException
	 */
	public OperandoDTO getOperando(String fields, boolean dipendenzaNumeric)throws RulesException {

		OperandoDTO opDTO = null;
		RulesConstantEncoder dip=new RulesConstantEncoder(fields);
		dip.encode();
		
		if(dip.getFormulaEncoded().contains("+") || dip.getFormulaEncoded().contains("-") || dip.getFormulaEncoded().contains("/") || dip.getFormulaEncoded().contains("*")){
			opDTO = createOperandoArihtmetic(fields, dipendenzaNumeric);
		}else{
			opDTO = createOperandoDTO(fields, dipendenzaNumeric);
		}
		
//		opDTO.formatScale(3);	
		return opDTO;
	}
	
	/**
	 * 
	 * @param fields
	 * @param dipendenzaNumeric
	 * @return
	 * @throws RulesException
	 */
	private OperandoDTO createOperandoDTO(String fields, boolean dipendenzaNumeric)throws RulesException {

		OperandoDTO  operandoDTO = null;
		ArrayList<DataFieldPageDTO> fieldList =  new ArrayList<DataFieldPageDTO>();

		
		if(fields.startsWith("(") && fields.endsWith(")")){

			String fieldLabel =" ";
			fields = fields.substring(1,fields.length()-1);    	
			StringTokenizer fieldsTokenizer = new StringTokenizer(fields, ",");  
			String indexF; 
			BigDecimal tot = new BigDecimal(0);
			operandoDTO = new OperandoDTO();
	
			operandoDTO.setMoreFields(fieldsTokenizer.countTokens()>1);
			
			OperandoNumeric opNumTemp;
			OperandoDTO  operandoDtoTemp; 
			
			while(fieldsTokenizer.hasMoreElements()){
	//  
						
				indexF = fieldsTokenizer.nextToken(); 
				
				opNumTemp = new OperandoNumeric(indexF,dipendenzaNumeric,this); 
				operandoDtoTemp = opNumTemp.init();	
				
				fieldList.addAll(operandoDtoTemp.getFieldList()); 
	    		fieldLabel += operandoDtoTemp.getFieldLabel()+","; 
	    		tot = tot.add(operandoDtoTemp.getSommaImporti()) ;

			}	
			
			fieldLabel = fieldLabel.substring(0, fieldLabel.length()-1);			
			operandoDTO.setFieldLabel(fieldLabel);
			operandoDTO.setFieldList(fieldList);
			operandoDTO.setSommaImporti(tot.setScale(3,BigDecimal.ROUND_HALF_UP));
			
		}	  
		else if(fields.toUpperCase().startsWith(OperandoBase.OPERANDO_COSTANT)){
	//
			OperandoCostant op = new OperandoCostant();
			op.setController(this);
            op.setStringToCheck(fields);
            op.setDipendenzaNumeric(dipendenzaNumeric);
            operandoDTO = op.init();
		}
		else if(fields.toUpperCase().indexOf(OperandoBase.OPERANDO_PERCENTUALE)!=-1){
	//
			OperandoPercentuale op = new OperandoPercentuale();
			op.setController(this);
            op.setStringToCheck(fields);
            op.setDipendenzaNumeric(dipendenzaNumeric);
            operandoDTO = op.init();
		}
		 if(operandoDTO==null){
	// 
			 OperandoNumeric opNum = new OperandoNumeric(fields,dipendenzaNumeric,this); 
			 operandoDTO = opNum.init();	
			 //throw new SiquelException(" Formula Dipendenze non valida - Operando non riconosciuto in formula: "+fields);
		 }	 
		 if(operandoDTO==null){
	//
			 throw new RulesException(" Formula Dipendenze non valida - Operando non riconosciuto in formula: "+fields);
		 }
		return operandoDTO;

	}
	

	private OperandoDTO createOperandoArihtmetic(String formula1, boolean dipendenzaNumeric)throws RulesException {

		RulesConstantEncoder rulesConstantEncoder=new RulesConstantEncoder(formula1);
		rulesConstantEncoder.encode();
		OperandoDTO  operandoDTO = null;
		Expression exp;
		BigDecimal result;
		String msg;
		
		msg = buildMsg(formula1);
		
		//StringBuffer msgSB = new StringBuffer();
		
	    	   
		Pattern p = Pattern.compile("[-+/*()]");
		String formula=rulesConstantEncoder.getFormulaEncoded();
		Matcher matcher = p.matcher(formula);
		
		StringBuffer formulaSB = new StringBuffer();
		String token;
		int match=0;
		
		int previousMatch = 0;
		
		try{
		
				while(matcher.find()){
			//
					
					match = matcher.start();
		 		   	token = formula.substring(previousMatch,match);
		 		   	
		 		   if(token.equals("")){
			//
		 			  formulaSB.append(formula.substring(match,match+1));
		 		   }else{
			//
		 			  operandoDTO = getOperando(rulesConstantEncoder.decodeToken(token), operandoDTO, dipendenzaNumeric);
		 			  operandoDTO.setTokenF(rulesConstantEncoder.decodeToken(token));
		 			  formulaSB.append(operandoDTO.getSommaImporti()).append(formula.substring(match,match+1));
		 			  //msgSB.append(operandoDTO.getFieldLabel());
		 			  operandoDTO = null;
					}
		 		   previousMatch = match+1;
				}
				
				if(matcher.hitEnd()){
			//
					if(match==0 && !formula.substring(match+1).startsWith(RulesConstants.CODICE_PAGINA)){
			//
					   token = formula;
					}   
					else if(!formula.substring(match+1).equalsIgnoreCase("")){
			//
						token=formula.substring(match+1);
					   operandoDTO = getOperando(rulesConstantEncoder.decodeToken(token), operandoDTO, dipendenzaNumeric);
					   formulaSB.append(operandoDTO.getSommaImporti());
					}
					   //msgSB.append(operandoDTO.getFieldLabel());
				}
				
				//return formulaSB.toString();
				
			
				
				formula = formulaSB.toString();//getFormula(formula);						
				exp = new Expression(formula);
				result = exp.eval();

				
				operandoDTO = new OperandoDTO();
				operandoDTO.setSommaImporti(result.setScale(3,BigDecimal.ROUND_HALF_UP));
				operandoDTO.setFieldLabel(msg);
		
	     }catch(ArithmeticException art){
	    	 
	    		if(art.getMessage().equals(RulesConstants.ARITHMETICH_DIVISION_ZERO)||art.getMessage().equals(RulesConstants.ARITHMETICH_DIVISION_UNDEFINED)){
	    			
	    			setMotivoNonApplicabilita("Calcolo non applicabile. Formula: "+formula1+" - Valori: "+formula);	

	    		}
		     throw new RulesException("Eccezione aritmetica nel calcolo della formula numerica: "+ formula1+" - in CtrBase: "+art.getMessage());
	     }
		 catch(RulesException e){  
	 		Trace.error(this,"Errore calcolo formula numerica;  ", e);
	    	 throw new RulesException("Errore nell'esecuzione del calcolo della formula numerica: "+ formula1+" - in CtrBase: "+e.getMessage());
	     }
		
		operandoDTO.setArithmetic(true);	     
		return operandoDTO;

		
	}
	
	/**
	 * 
	 * @param formula1
	 * @return
	 * @throws RulesException
	 */
	private String buildMsg(String formula1) throws RulesException{

		
		BaseMsgFormulaProducer msgFormulaProducer = getMsgFormulaProducerCleaned();
		
		RulesConstantEncoder rulesConstantEncoder =new RulesConstantEncoder(formula1);
		rulesConstantEncoder.encode();
		
		String formula=rulesConstantEncoder.getFormulaEncoded();
		
		//StringBuffer msgSB = new StringBuffer();
		Pattern p = Pattern.compile("[-+/*()]");
		Matcher matcher = p.matcher(formula);
		String token;
		String operatore;
		int match=0;
		int previousMatch = 0;
		
		try{
			
			
			   while(matcher.find()){

				match = matcher.start();
	 		   	token = formula.substring(previousMatch,match);
	 			operatore = formula.substring(match, match+1);
	 			
	 		   	if(operatore.equals("(")){

	 		   	    msgFormulaProducer.openParenthesis();
	 		   	
	 		   	} else {

			   		if(token.startsWith(RulesConstantEncoder.CONSTANT_LABEL_IDENTIFY)){
			   			msgFormulaProducer.addConstantValue(rulesConstantEncoder.decodeToken(token));
			   		}
			   		else if(token.toUpperCase().indexOf(OperandoBase.OPERANDO_PERCENTUALE)!=-1){
			   			
			   			StringBuffer msgTemp =new StringBuffer(buildMsg(rulesConstantEncoder.decodeToken(token).substring(0, rulesConstantEncoder.decodeToken(token).indexOf("%"))));
			   			msgTemp.append(" ");
			   			msgTemp.append(buildMsg(rulesConstantEncoder.decodeToken(token).substring(rulesConstantEncoder.decodeToken(token).indexOf("%")+1,rulesConstantEncoder.decodeToken(token).length())));
			   			msgTemp.append("%");
			   			
			   			msgFormulaProducer.addStringBuffer(msgTemp);
			   			
			   		}
			   			
			   		else if(!token.equals("")){

			   			
			   			boolean isAbsolutevalue=false;
			   			
			   			if( token.startsWith(RulesConstants.ABSOLUTE_VALUE)){ 
			   				isAbsolutevalue =true;
			   				token = token.substring(RulesConstants.ABSOLUTE_VALUE.length());
			   			}
			   			
			   			msgFormulaProducer.addField(isAbsolutevalue, labelField, getField(rulesConstantEncoder.decodeToken(token)).getLabel());
	
			   			
			   		}
			   		if(operatore.equals(")")){

			   			msgFormulaProducer.closeParenthesis();
			   			
			   			
			   			if(formula.length() > match+1){
				   			operatore = "";
			   			}	
			   		}

			   		
			   		if(operatore.equals("+")||
			   		   operatore.equals("-")||
			   		   operatore.equals("*")||
			   		   operatore.equals("/")
			   		){
			   			msgFormulaProducer.addOperatore(operatore);			   			
			   		}
			   		
			   	}
	 		   
	 		   	previousMatch = match+1;
			
			}
			
			if(matcher.hitEnd()){
		//
				if(match==0 && !formula.substring(match+1).startsWith(RulesConstants.CODICE_PAGINA)){
		//
					   token = formula;
				}	   
				else{
		//
					token=formula.substring(match+1);
				}
								
				if(token.startsWith(RulesConstantEncoder.CONSTANT_LABEL_IDENTIFY)){

		   			msgFormulaProducer.addConstantValue(rulesConstantEncoder.decodeToken(token));
		   			
				}	
				else if(token.toUpperCase().indexOf(OperandoBase.OPERANDO_PERCENTUALE)!=-1){
	   			
		   			StringBuffer msgTemp =new StringBuffer(buildMsg(rulesConstantEncoder.decodeToken(token).substring(0, rulesConstantEncoder.decodeToken(token).indexOf("%"))));
		   			msgTemp.append(" ");
		   			msgTemp.append(buildMsg(rulesConstantEncoder.decodeToken(token).substring(rulesConstantEncoder.decodeToken(token).indexOf("%")+1,rulesConstantEncoder.decodeToken(token).length())));
		   			msgTemp.append("%");
		   			msgFormulaProducer.addStringBuffer(msgTemp);		   			
		   			
		   			
		   		}
				else if(!token.equals("")){
					
		   			boolean isAbsolutevalue=false;
		   			
		   			if( token.startsWith(RulesConstants.ABSOLUTE_VALUE)){ 
		   				isAbsolutevalue =true;
		   				token = token.substring(RulesConstants.ABSOLUTE_VALUE.length());
		   			}
		   			
		   			msgFormulaProducer.addField(isAbsolutevalue, labelField, getField(rulesConstantEncoder.decodeToken(token)).getLabel());

		   			
				}	
				
			}
		
	      
	     }catch(Exception e){
	    	 Trace.error(this,"Errore nella costruzione del messaggio in CtrBase:  ", e);
	    	 throw new RulesException("Errore nella costruzione del messaggio in CtrBase: "+e.getMessage());
	     }
		

	     return msgFormulaProducer.getMsg().toString();
		
	}
	

	/**
	 * 
	 * @param token
	 * @param operandoDTO
	 * @param dipendenzaNumeric
	 * @return
	 * @throws RulesException
	 */
	private OperandoDTO getOperando(String token, OperandoDTO operandoDTO, boolean dipendenzaNumeric) throws RulesException{
	//
		//
		// getOperando
		//
		OperandoBase op;
		
		if(token.toUpperCase().startsWith(OperandoBase.OPERANDO_COSTANT)){
	//
			op = new OperandoCostant();
			op.setController(this);
            op.setStringToCheck(token);
            op.setDipendenzaNumeric(dipendenzaNumeric);
            operandoDTO = op.init();
		}
		else if(token.toUpperCase().indexOf(OperandoBase.OPERANDO_PERCENTUALE)!=-1){
	//
			op = new OperandoPercentuale();
			op.setController(this);
            op.setStringToCheck(token);
            op.setDipendenzaNumeric(dipendenzaNumeric);
            operandoDTO = op.init();
		}
		if(operandoDTO==null){
	//
			 OperandoNumeric opNum = new OperandoNumeric(token,dipendenzaNumeric,this); 
			 operandoDTO = opNum.init();
		}	 
		if(operandoDTO==null){
	//
			 throw new RulesException(" Formula Dipendenze non valida - Operando non riconosciuto in formula: "+token);
		}
		
		return operandoDTO;
	}
	
	
	
	public DocumentBase getDocument() {
	//
		//
		// getDocument
		//
		return document;
	}



 
	public ArrayList<Field> getFields() {
		Collections.sort(fields);
		return fields;
	}

	public void setFields(ArrayList<Field> fields) {

		this.fields = fields;
	}

	
	
	public void addField(Field o){

	     if(!fields.contains(o)){
	    	 fields.add(o);    	 
	     }
	} 
	
	public void addAllFields(List<DataFieldPageDTO> listFv){

		for(DataFieldPageDTO o:listFv){	
			
		     if(!fields.contains(o.getFieldValue())){
		    	 fields.add(o.getFieldValue());    	 
		     }
		}
	} 	
	

   

	
	public String getLabelField() {

		return labelField;
	}
	
	public void setLabelField(String labelField) {

		this.labelField = labelField;
	}
	
	
	public boolean addScostamento(ScostamentoBase o){
	
		     if( o!=null){
			    			    	
		    	 o.setCtr(this);
		    	 ScostamentoOutDTO pDto = o.build(); 
		    	 
		    	   //if(pDto!=null &&  !parzialiErroriList.contains(pDto)){
		    	 // aggiunge il parziale se si tratta di uno scostamente non presente o di un parziale di tipo operando relativo ad uno scostamento inserito
		    	   if(pDto!=null &&  (!scostamenti.contains(pDto) || o.getInputDTO().isOperandoToAdd()  )){

				    	 scostamenti.add(pDto);		
				    	 return true;
		    	   }		    	 		    
		    	 
		     }
			  return false ;
		     
   } 
	
	
	public ArrayList<ScostamentoOutDTO> getScostamenti() {
		
		Collections.sort(scostamenti);
		
		return scostamenti;
	}
	
	private void setScostamenti(ArrayList<ScostamentoOutDTO> scostamenti) {
		this.scostamenti = scostamenti;
	}
	
	public boolean isCondition() {
		return condition;
	}
	
	public void setCondition(boolean isCondition) {
		this.condition = isCondition;
	}
	
	
	private void resetPrgParziale(){
		prgParziale =0;
	}
	
	public int nextPrgParziale(){
		return prgParziale ++;
	}

    public void resetFields(){    	
    	setFields(new ArrayList<Field>());
    }

    public void resetScostamento(){
    	
    	setScostamenti(new ArrayList<ScostamentoOutDTO>());
    	resetPrgParziale();
    }

	public boolean isArithmeticRule() {
		return arithmeticRule;
	}

	public void setArithmeticRule(boolean arithmeticRule) {
		this.arithmeticRule = arithmeticRule;
	}

	public BigDecimal getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(BigDecimal tolleranza) {
		this.tolleranza = tolleranza;
	}

	public String getMotivoNonApplicabilita() {
		return motivoNonApplicabilita;
	}

	public void setMotivoNonApplicabilita(String motivoNonApplicabilita) {
		this.motivoNonApplicabilita = motivoNonApplicabilita;
	}

	public boolean isErroreGestito() {
		return erroreGestito;
	}

	public void setErroreGestito(boolean erroreGestito) {
		this.erroreGestito = erroreGestito;
	}
	
	public boolean isScostamento() {
		return scostamento;
	}


	public void setScostamento(boolean scostamento) {
		this.scostamento = scostamento;
	}
	
	
}
