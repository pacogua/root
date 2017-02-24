package it.almaviva.rules.core.expressions;

import it.almaviva.rules.core.expressions.types.CheckRequired;
import it.almaviva.rules.core.expressions.types.EqualLiteral;
import it.almaviva.rules.core.expressions.types.False;
import it.almaviva.rules.core.expressions.types.NotEqualLiteral;
import it.almaviva.rules.core.expressions.types.NotNull;
import it.almaviva.rules.core.expressions.types.Null;
import it.almaviva.rules.core.expressions.types.NumberNoSignifDec;
import it.almaviva.rules.core.expressions.types.True;
import it.almaviva.rules.core.expressions.types.date.DateGreatherThan;
import it.almaviva.rules.core.expressions.types.date.DateGreatherThanOrEqual;
import it.almaviva.rules.core.expressions.types.date.DateLessThan;
import it.almaviva.rules.core.expressions.types.date.DateLessThanOrEqual;
import it.almaviva.rules.core.expressions.types.numerical.Equal;
import it.almaviva.rules.core.expressions.types.numerical.GreaterThan;
import it.almaviva.rules.core.expressions.types.numerical.GreaterThanOrEqual;
import it.almaviva.rules.core.expressions.types.numerical.LessThan;
import it.almaviva.rules.core.expressions.types.numerical.LessThanOrEqual;
import it.almaviva.rules.core.expressions.types.numerical.Negative;
import it.almaviva.rules.core.expressions.types.numerical.NotEqual;
import it.almaviva.rules.core.expressions.types.numerical.Positive;

public interface RulesConstants {

	public static final String AND ="AND";
	public static final String OR ="OR";
	
	public static final String  CONTROLLO_OBBLIGATORIO="1"; 			   	   
	public static final ExpressionBase  [] DIPENDENZE  ={    new DateGreatherThanOrEqual(),
		 													 new DateGreatherThan(),	
		 													 new DateLessThanOrEqual(),
															 new DateLessThan(),
		  													 new NotNull(), 
		   													 new Null(),
		   													 new LessThanOrEqual(),
	   														 new LessThan(),
	   														 new GreaterThanOrEqual(),
	   														 new GreaterThan(),	   														 	   													
	   														 new NotEqual(),
	   														 new Equal(),
	   														 new True(),
	   														 new False(),
	   														 new Positive(),
	   														 new Negative(),
	   														 new EqualLiteral(),
	   														 new NotEqualLiteral(),
	   														 new CheckRequired(),
	   														 new NumberNoSignifDec()
	    													};
	   

	  // public static final String  LABEL_FIELD_VALUE =  " il valore del dato";
	   public static final String  TODAY =  "today";
	     
	    
	    public static final String  OPERATORE_NULL= "NULL";
	    public static final String  OPERATORE_NOT_NULL= "NOT_NULL";
	    public static final String  OPERATORE_EQUAL= "==";
	    public static final String  OPERATORE_NOT_EQUAL= "!=";
	    public static final String  OPERATORE_AND= "AND";
	    public static final String  OPERATORE_OR= "OR";
	    public static final String  OPERATORE_TRUE= "TRUE";
	    public static final String  OPERATORE_FALSE= "FALSE";
	    public static final String  OPERATORE_GREATER_THAN= ">";
	    public static final String  OPERATORE_LESS_THAN= "<";
	    public static final String  OPERATORE_GREATER_THAN_OR_EQUAL= ">=";
	    public static final String  OPERATORE_LESS_THAN_OR_EQUAL= "<=";
	    public static final String  OPERATORE_CHECK_REQUIRED ="CHK_REQ";
	    public static final String  OPERATORE_DATE_GREATHER_THAN ="D>";
	    public static final String  OPERATORE_DATE_GREATHER_THAN_OR_EQUAL ="D>=";
	    public static final String  OPERATORE_DATE_LESS_THAN ="D<";
	    public static final String  OPERATORE_DATE_LESS_THAN_OR_EQUAL ="D<=";
	    public static final String  OPERATORE_EQUAL_LITERAL= "$EQ";
	    public static final String  OPERATORE_NEGATIVE= "NEGATIVE";
	    public static final String  OPERATORE_NOT_EQUAL_LITERAL= "$!EQ";
	    public static final String  OPERATORE_NO_SIGNIF_DEC= "LONG";
	    public static final String  OPERATORE_POSITIVE= "POSITIVE";

	    public static final String  FIELD_VOID = "** n. p. **";
   
	    
	    public static final String CODICE_PAGINA = "P";
	    public static final String ABSOLUTE_VALUE = "ABS";
	    public static final String ABSOLUTE_VALUE_LABEL = "assoluto";
//	    
	    public static final String ARITHMETICH_DIVISION_ZERO      = "Division by zero";
	    public static final String ARITHMETICH_DIVISION_UNDEFINED = "Division undefined";
	    public static final String PARZIALE_SCOSTAMENTO="1"; 
	    
	    

}
