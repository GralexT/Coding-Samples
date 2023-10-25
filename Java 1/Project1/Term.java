/**
 * Term models terms in a polynomial. 
 * 
 * @author Lyndon While
 * @version 1.0
 */
public class Term
{
    // the term = coefficient * x ^ exponent
    private double coefficient;
    private int    exponent;

    public Term(double c, int e)
    {
        coefficient = c;
        exponent = e;
    }
    
    // returns the coefficient
    public double getCoefficient()
    {
        return coefficient;
    }
    
    // returns the exponent
    public int getExponent()
    {
        return exponent;
    }

    // returns the term as a simple String for display 
    // e.g. with coefficient = 2 and exponent = 1, return "+ 2.0 x^1" 
    public String displaySimple()
    {
        if(coefficient < 0) {return coefficient+"x^"+exponent;}     //Returns with '-' value.
        else                {return "+"+coefficient+"x^"+exponent;} //Returns with '+' value.
    }

    // returns the term as a String for display: 
    // see the sample file and the test program for the layout required 
    public String displayImproved()
    {
        String expon = "^"+String.valueOf(exponent);
        String coef;
        if(coefficient%1 == 0)  {coef = String.valueOf((int)coefficient)+"x";}
        else                    {coef = String.valueOf(coefficient)+"x";}
        if(coefficient > 0)     {coef = "+"+coef;}
        
        if(coefficient == 0)    {return "0";}
        if(exponent == 0)       {return coef.substring(0,coef.length()-1);} //Removes x value from coef
        if(coefficient == 1)    {coef = "+x";}
        if(coefficient == -1)   {coef = "-x";}
        if(exponent == 1)       {expon = "";}
        return coef+expon;
    }
}