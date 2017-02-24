/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.almaviva.rules.dipendenza.dto;

import java.math.BigDecimal;
import java.util.ArrayList;



public class OperandoDTO extends BaseDTO {


  private static final long serialVersionUID = 1L;
	
	
   private ArrayList<DataFieldPageDTO> fieldList = new ArrayList();

   private String fieldLabel =" ";
   
   private BigDecimal sommaImporti;
   
   private boolean moreFields = false;
   
   private boolean constantField=false;
   
   private String valueConstant;
   
   private String tokenF;
   
   private boolean arithmetic;
   
   private boolean  absoluteValue;
   


	

	public OperandoDTO() {
    }

	
	public ArrayList<DataFieldPageDTO> getFieldList() {
		return fieldList;
	}

	public void setFieldList(ArrayList<DataFieldPageDTO> fieldList) {
		this.fieldList = fieldList;
	}


	public String getFieldLabel() {
		return fieldLabel;
	}


	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
    


	public boolean isMoreFields() {
		return moreFields;
	}


	public void setMoreFields(boolean moreFields) {
		this.moreFields = moreFields;
	}


	public BigDecimal getSommaImporti() {
		return sommaImporti;
	}


	public void setSommaImporti(BigDecimal sommaImporti) {
		this.sommaImporti = sommaImporti;
	}

	public boolean isConstantField() {
		return constantField;
	}

	public void setConstantField(boolean constantField) {
		this.constantField = constantField;
	}

	public String getValueConstant() {
		return valueConstant;
	}

	public void setValueConstant(String valueConstant) {
		this.valueConstant = valueConstant;
	}
	
	public boolean isArithmetic() {
		return arithmetic;
	}

	public void setArithmetic(boolean arithmetic) {
		this.arithmetic = arithmetic;
	}

	public String getTokenF() {
		return tokenF;
	}

	public void setTokenF(String tokenF) {
		this.tokenF = tokenF;
	}


	public boolean isAbsoluteValue() {
		return absoluteValue;
	}


	public void setAbsoluteValue(boolean absoluteValue) {
		
		this.absoluteValue = absoluteValue;
	}
	
	/**
	 * 
	 * @param operandoToCo
	 */

	public void formatScaleCompareWith(BigDecimal operandoToCo){
		
		if (sommaImporti.scale()!=operandoToCo.scale()) {
			sommaImporti = sommaImporti.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	
	}
	
}
