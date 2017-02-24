package it.almaviva.rules.dipendenza.dto;

import it.almaviva.rules.exceptions.RulesException;
import it.almaviva.rules.core.facts.DocumentBase;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimulatorDocument extends DocumentBase {
   


    private Iterator<String>  listValues ;


    
    public String next()throws RulesException    {
    	//
        try
        {
        	//
            return listValues.next();
        	//
        }
    	//
        catch (NoSuchElementException ex)
    	//
        {
            throw new RulesException("Non sono presenti altri valori nella lista dei Parametri del Simulatore !");
        }
    }
    
   

	public void setListValues(Iterator<String> listValues) {
    	//
		this.listValues = listValues;
	}
  
    
	

	
}
