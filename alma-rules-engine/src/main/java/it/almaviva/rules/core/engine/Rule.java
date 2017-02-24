/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.almaviva.rules.core.engine;

import java.math.BigDecimal;

/**
 * The Class Rule.
 */
public class Rule  {

	

    /** The formula. */
    private final String formula; 

    /** The default msg error. */
    private String defaultMsgError;
    
    /** The scostamento. */
    private boolean scostamento;
    
    /** The tolleranza. */
    private BigDecimal tolleranza = new BigDecimal(0);	
    
 
    



	/**
	 * Gets the tolleranza.
	 *
	 * @return the tolleranza
	 */
	public BigDecimal getTolleranza() {
		return tolleranza;
	}


	/**
	 * Sets the tolleranza.
	 *
	 * @param tolleranza the new tolleranza
	 */
	public void setTolleranza(BigDecimal tolleranza) {
		this.tolleranza = tolleranza;
	}



	/**
	 * Instantiates a new rule.
	 *
	 * @param formula the formula
	 */
	public Rule(String formula) {
		super();
		this.formula = formula;
	}
         

    
    /**
     * Checks if is scostamento.
     *
     * @return true, if is scostamento
     */
    public boolean isScostamento() {
		return scostamento;
	}



	/**
	 * Apply scostamento.
	 */
	public void applyScostamento() {
		this.scostamento = true;
	}
	
	
    
	/**
	 * Gets the formula.
	 *
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}



	/**
	 * Gets the default msg error.
	 *
	 * @return the default msg error
	 */
	public String getDefaultMsgError() {

		return defaultMsgError;
	}


	/**
	 * Sets the default msg error.
	 *
	 * @param defaultMsgError the new default msg error
	 */
	public void setDefaultMsgError(String defaultMsgError) {

		this.defaultMsgError = defaultMsgError;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formula == null) ? 0 : formula.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (formula == null) {
			if (other.formula != null)
				return false;
		} else if (!formula.equals(other.formula))
			return false;
		return true;
	}

    



	
	
}
