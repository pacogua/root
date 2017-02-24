/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.almaviva.rules.dipendenza.dto;

import it.almaviva.rules.core.expressions.Field;

public class DataFieldPageAbsDecorator extends DataFieldPageBaseDecorator {
    
	private static final long serialVersionUID = 1L;

	
	public DataFieldPageAbsDecorator(DataFieldPageDTO dataFieldPage ){
		
		super(dataFieldPage);
		super.getFieldValue().setAbsolute(true);	
   
	}
	
	
	
	@Override
	public Field getFieldValue() {
	
		
		Field field =  super.getFieldValue();
		String label = field.getLabel();
			
		    if(label != null && label.lastIndexOf("=")>1){

			    label = label.substring(0,label.lastIndexOf("="))+ " (valore assoluto) = " +  this.getValue() ; 
		    	
				field.setLabel(label);
			
		    }	
	
	    return field;
 
	}




	@Override
	public String getValue() {

		String value =  super.getValue();
						
		    if(value != null &&  value.startsWith("-")){
				value = value.substring(1);						
			 }	
				
        return value;
	}
	

	
	public static void main(String a[]){
		
		
		/**DataFieldPageDTO ar = new DataFieldPageDTO();
		ar.setValue("-3");
		
		FieldValue fieldValue = new FieldValue();
		fieldValue.setLabel("Pag. '2' - Campo Spese = -3");
		
		ar.setFieldValue(fieldValue);
		
		DataFieldPageBaseDecorator d = new DataFieldPageAbsDecorator(ar);
		
		
		System.out.println("d.getValue() =" + d.getValue());
		System.out.println("d.label =" + d.getFieldValue().getLabel());

		
		
	/**	String x = "Pag. '2' - Campo Spese = -3";
		if(x.lastIndexOf("=")>1){
	    	x = x.substring(0,x.lastIndexOf("="))+ " (valore assoluto) =" +  x.substring(x.lastIndexOf("=")+1) ; 
		}
		//x = x.replaceAll("=", "(valore assoluto) =")  ; 
		System.out.println(x);
		
		*/
		
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
	}





	
}