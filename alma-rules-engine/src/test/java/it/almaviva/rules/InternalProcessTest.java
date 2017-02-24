package it.almaviva.rules;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import org.junit.Test;

import it.almaviva.rules.core.engine.Result;
import it.almaviva.rules.core.engine.Rule;
import it.almaviva.rules.core.engine.RulesEngine;
import it.almaviva.rules.core.translator.RulesConstantEncoder;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.dipendenza.dto.PaginaDocument;
import it.almaviva.rules.example.FieldPageTemplate;
import it.almaviva.rules.exceptions.RulesException;

public class InternalProcessTest {

	
		


	
	//@Test
	public void testTolleranza(){
			
			BigDecimal importi1 = new BigDecimal(2);
			BigDecimal importi2 = new BigDecimal(1);
			BigDecimal tolleranza = new BigDecimal(0);
			
		   boolean valid;
		   
					BigDecimal 	valore = importi1.subtract(importi2).abs();
				
					valore = valore.setScale(2,RoundingMode.HALF_UP);
					
					valid = !(valore.compareTo(tolleranza.multiply(new BigDecimal(-1)))==-1 || valore.compareTo(tolleranza)==1);//?false:true; 
				
			System.out.println("valid:"+ valid);
				
			
			
		}
    
  
//	@Test
	public void testRulesConstantEncode(){
		
		
		String formula ="[1>K{NON^RICORRE^LA^FATTISPECIE}]OR[1>K{177777}]";

		String tokenDecode="";
		RulesConstantEncoder constantEncoder = new RulesConstantEncoder(formula);
		try {
			
			constantEncoder.encode();
			
			constantEncoder.getFormulaEncoded();
			
			System.out.println(constantEncoder.getFormulaEncoded());
			constantEncoder.decodeToken("[1>K{4}");
			
			assertTrue(formula.contains(tokenDecode));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
}
