package it.almaviva.rules;

import static org.junit.Assert.assertNull;
import it.almaviva.rules.core.engine.Result;
import it.almaviva.rules.core.engine.Rule;
import it.almaviva.rules.core.engine.RulesEngine;
import it.almaviva.rules.core.expressions.types.numerical.Equal;
import it.almaviva.rules.core.expressions.types.numerical.GreaterThan;
import it.almaviva.rules.dipendenza.dto.DataFieldPageDTO;
import it.almaviva.rules.dipendenza.dto.PaginaDocument;
import it.almaviva.rules.dipendenza.dto.QuestionarioDocument;
import it.almaviva.rules.dipendenza.utils.i18n.Resource;
import it.almaviva.rules.example.FieldPageTemplate;
import it.almaviva.rules.example.PageTemplate;
import it.almaviva.rules.exceptions.RulesException;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

public class MyFirstRulesTest {

	

   //@Test
	public void testRules() {
		


		
   	 try {
     	
   	  /*
   	   * Creo il documento (specifico del sistema di riferimento)
   	   * */	 
         HashMap<String, DataFieldPageDTO> hashData =new  HashMap<String, DataFieldPageDTO>();
         
         DataFieldPageDTO d1= new DataFieldPageDTO();
         d1.setId("1");
         d1.setIdCampo("1");
         d1.setIdPagina("10");
         d1.setIdQuestionario("33");
         d1.setLabel("label campo 1");
         d1.setNomeCampo("nome campo 1");
         d1.setProgCampo("1");
         d1.setProgPagina("10");
       //  d1.setValue("7");
         d1.setValue("true");
         
         
         DataFieldPageDTO d2= new DataFieldPageDTO();
         d2.setId("2");
         d2.setIdCampo("2");
         d2.setIdPagina("10");
         d2.setIdQuestionario("33");
         d2.setLabel("label campo 2");
         d2.setNomeCampo("nome campo 2");
         d2.setProgCampo("2");
         d2.setProgPagina("10");
         d2.setValue("5");
         
   	     PaginaDocument pagina = new PaginaDocument();
         pagina.setIdPagina(1l); 
         pagina.setPrgPage(1l);  
         pagina.setTitolo("Pagina 1");
         pagina.setHashData(hashData);
   		       
         
         hashData.put("1", d1);
         hashData.put("2", d2);   		 

         
	         /**
	          * Istanza della classe che si occupa della parte dinamica della formula
	          * (specifica dell'applicazione di riferimento)
	          */
		      FieldPageTemplate fieldPageTemplate= new FieldPageTemplate(pagina);	
		      		     
		      
		      /**
		       * Istanzio del RulesEngine associandogli il documento (specifico dell'applicazione)
		       */
			  RulesEngine rulesEngine = new RulesEngine(fieldPageTemplate);	
	
			  
			  /**
			   * Istanzio la regola
			   */
	      	  //Rule rules = new Rule("IF[1+2<K{10000}]or[1<k{999999999}]THEN[1==2]");
			//  Rule rules = new Rule("IF[1$EQ2]THEN[1null]");			  
			//  Rule rules = new Rule("IF[1$EQk{ciccrrr}]THEN[1not_null]");
			  
			  
			  //exceptions
			  //Rule rules = new Rule("IF[1>k{2}]THEN [1>k{2}]andrr[1==2]");  //grammar
			    Rule rules = new Rule("IF[1>k{2}]THEN [1>k{2}]and[1==2]");
			 // Rule rules = new Rule("IF[P10X2>k{2}]THEN [1NOTNULL]");
			  
	       	 //rules.applyScostamento();
	       	 rules.setTolleranza(new BigDecimal(1));
	       	 // rules.setDefaultMsgError("****** default msg *********");
	    	
	       	  /**
	       	   * esecuzione...
	       	   */
	       	  Result result = rulesEngine.execute(rules);
      	
	       	  
	       	result.getScostamenti();
	       	  

	       	System.out.println(result.toString()  );
	       	System.out.println(result.getMessage()  );
	       	
    	assertNull("controllo non superato",result.getMessage());
        
	    	
			
		} catch (RulesException e) {
			// TODO Auto-generated catch block
			
			System.out.println("exception formula : "+e.getRuleFormula());
			System.out.println("exception message: "+e.getMessage());
			e.printStackTrace();
		}
		
		
	}

	
	
	
	 @Test
		public void testPageTemplate() {
			


			
	   	 try {
	     	
	   	  /*
	   	   * Creo il documento (specifico del sistema di riferimento)
	   	   * */	 
	         HashMap<String, DataFieldPageDTO> hashData =new  HashMap<String, DataFieldPageDTO>();
	         
	         DataFieldPageDTO d1= new DataFieldPageDTO();
	         d1.setId("1");
	         d1.setIdCampo("1");
	         d1.setIdPagina("10");
	         d1.setIdQuestionario("33");
	         d1.setLabel("label campo 1");
	         d1.setNomeCampo("nome campo 1");
	         d1.setProgCampo("1");
	         d1.setProgPagina("10");
	       //  d1.setValue("7");
	         d1.setValue("true");
	         
	         
	         DataFieldPageDTO d2= new DataFieldPageDTO();
	         d2.setId("2");
	         d2.setIdCampo("2");
	         d2.setIdPagina("10");
	         d2.setIdQuestionario("33");
	         d2.setLabel("label campo 2");
	         d2.setNomeCampo("nome campo 2");
	         d2.setProgCampo("2");
	         d2.setProgPagina("10");
	         d2.setValue("5");
	         
	   	     PaginaDocument pagina = new PaginaDocument();
	         pagina.setIdPagina(10l); 
	         pagina.setPrgPage(10l);  
	         pagina.setTitolo("Pagina 10");
	   		       	        
	         hashData.put("1", d1);
	         hashData.put("2", d2);   		 

	         pagina.setHashData(hashData);
	         
	         ///				pagina.setIdPagina(new Long(idPagina));
//				pagina.setPrgPage(new Long(progPagina));
//				pagina.setTitolo(titoloPagina);
//				pagina.setHashData(new HashMap<String, DataFieldPageDTO>());
//				pagina.getHashData().put(progCampo, dataFieldPageDTO);
//
//				pagineMap.put(idPagina, pagina);
				///
	         
	         
	         
	         QuestionarioDocument document = new QuestionarioDocument();
	         document.setIdQuestEnte("100");
	         HashMap<String, PaginaDocument> listPagineQuest = new HashMap<String, PaginaDocument>();
	         listPagineQuest.put("10", pagina);
	         
	         document.setListPagineQuest(listPagineQuest);
	         
		         /**
		          * Istanza della classe che si occupa della parte dinamica della formula
		          * (specifica dell'applicazione di riferimento)
		          */
			      //FieldPageTemplate fieldPageTemplate= new FieldPageTemplate(pagina);	
			      	PageTemplate pageTemplate = new PageTemplate(document);	     
			      	pageTemplate.setCurrentPageIndex("10");
			      /**
			       * Istanzio del RulesEngine associandogli il documento (specifico dell'applicazione)
			       * 
			       */
			   
			      	
				  RulesEngine rulesEngine = new RulesEngine(pageTemplate);	
		
				  
				  /**
				   * Istanzio la regola
				   */
		      	  //Rule rules = new Rule("IF[1+2<K{10000}]or[1<k{999999999}]THEN[1==2]");
				//  Rule rules = new Rule("IF[1$EQ2]THEN[1null]");			  
				//  Rule rules = new Rule("IF[1$EQk{ciccrrr}]THEN[1not_null]");
				  
				  
				  //exceptions
				  //Rule rules = new Rule("IF[1>k{2}]THEN [1>k{2}]andrr[1==2]");  //grammar
				   // Rule rules = new Rule("IF[1>k{2}]THEN [1>k{2}]and[1==2]");
				  //Rule rules = new Rule("IF[(P10X2+P10X2)*P10X2/K{1}>k{2}]THEN [1NULL]");		
				//  Rule rules = new Rule("if[K{1}*P10X2>k{2}]then[1not_null]");
				  Rule rules = new Rule("[P10X2not_null]");
		       	 //rules.applyScostamento();
		       	 rules.setTolleranza(new BigDecimal(1));
		       	 // rules.setDefaultMsgError("****** default msg *********");
		    	
		       	  /**
		       	   * esecuzione...
		       	   */
		       	  Result result = rulesEngine.execute(rules);
	      	
		       	  
		       	result.getScostamenti();
		       	  

		       	System.out.println(result.toString()  );
		       	System.out.println(result.getMessage()  );
		       	
	    	assertNull("controllo non superato",result.getMessage());
	        
		    	
				
			} catch (RulesException e) {
				// TODO Auto-generated catch block
				
				System.out.println("exception formula : "+e.getRuleFormula());
				System.out.println("exception message: "+e.getMessage());
				e.printStackTrace();
			}
			
			
		}

	
	
	

   //@Test
   public void testMsgBundle(){
	   
	   
	   try {
//		GreaterThan g = new GreaterThan();
//		   System.out.println(g.t1("first.msg"));
//		   		 
//		   Equal g2 = new Equal();
//		   System.out.println(g2.t1("first.msg"));
		   
		 
		   System.out.println(  Resource.MESSASGES.get("msg.dyna","uno","due"));  
		   
		   
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   
   }
   
	
}
