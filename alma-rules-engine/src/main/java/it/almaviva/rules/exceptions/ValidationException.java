/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.almaviva.rules.exceptions;


import java.util.ArrayList;

import it.almaviva.rules.core.expressions.Field;
import it.almaviva.rules.core.statistcs.scostamento.dto.ScostamentoOutDTO;


public class ValidationException extends Exception {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Field> fields;
    private String message;
    private ArrayList<ScostamentoOutDTO> scostamenti; 





	public ValidationException(String message) {
        super(message);
        this.message = message;
    }

	
    
    public ValidationException(String message, ArrayList<Field> fields) {
        super(message);
        setFields(fields);
        this.message = message;
    }


	public ArrayList<Field> getFields() {
		return fields;
	}

	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}

    public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ArrayList<ScostamentoOutDTO> getScostamenti() {
		return scostamenti;
	}


	public void setScostamenti(ArrayList<ScostamentoOutDTO> scostamenti) {
		this.scostamenti = scostamenti;
	}


    
    
}
