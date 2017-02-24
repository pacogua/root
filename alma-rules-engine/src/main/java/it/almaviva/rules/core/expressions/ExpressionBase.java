package it.almaviva.rules.core.expressions;


import it.almaviva.rules.core.expressions.operando.OperandoTemplate;
import it.almaviva.rules.core.expressions.types.numerical.NumericalExpression;
import it.almaviva.rules.dipendenza.dto.OperandoDTO;
import it.almaviva.rules.exceptions.RulesException;

public abstract class ExpressionBase {

	   public abstract String getOperatore(); 	  

	   
	   protected String stringOperando1;
	   protected String stringOperando2;	   
       protected OperandoDTO operando1;
       protected OperandoDTO operando2; 	   	  
	          
       protected OperandoTemplate typeCtr;
       
       public ExpressionBase(){
    	   
       }
       

	   public ExpressionBase(String stringOperando1, String stringOperando2, OperandoTemplate typeCtr)throws RulesException{
		   this.stringOperando1 = stringOperando1;
		   this.stringOperando2 = stringOperando2;
		   this.typeCtr  = typeCtr;
		   operando1 =   typeCtr.getOperando(stringOperando1, (this instanceof NumericalExpression));		   
		   
	   }
	   
	   public void init(String stringOperando1, String stringOperando2, OperandoTemplate typeCtr)throws RulesException{
		   
		   this.stringOperando1 = stringOperando1;
		   this.stringOperando2 = stringOperando2;
		   this.typeCtr  = typeCtr;
		   operando1 =   typeCtr.getOperando(stringOperando1, (this instanceof NumericalExpression));		  
		   
	   }	   
	   
	 ////TODO spostare in espression
	 public ExpressionResult execute()throws RulesException{

		   // aggiungere controlli di classe
		 
		    ExpressionResult f = evaluate();
		 
		 
		 return f;
	 }   
	   

	 public abstract ExpressionResult evaluate()throws RulesException;
	
	 public abstract ExpressionBase getNewObject();
	    
	 public static String operatoriToString(){
            
	        String p ="[ ";
	        
	          for(int i=0; i<RulesConstants.DIPENDENZE.length; i++){
	          
	               p= p+ " '"+RulesConstants.DIPENDENZE[i].getOperatore()+"',";
	          }
	        p = p.substring(0,p.lastIndexOf(","))+" ]";
	        
	        return p;
	     }

		public OperandoTemplate getTypeCtr() {
			return typeCtr;
		}

		public void setTypeCtr(OperandoTemplate typeCtr) {
			this.typeCtr = typeCtr;
		}
	
	
}
