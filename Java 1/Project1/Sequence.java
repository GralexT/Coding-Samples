/**
 * Sequence models sequences.
 * 
 * @author Lyndon While
 * @version 1.0
 */
import java.util.ArrayList;

public class Sequence
{
   // the numbers in the sequence 
   private ArrayList<Double> sequence;

   // sets up sequence by parsing s 
   // e.g. Sequence("3, -4, 8.5") sets sequence to <3, -4, 8.5> 
   public Sequence(String s)
   {
       sequence = new ArrayList<>();
       for (String x : s.split(",")) 
           sequence.add(Double.parseDouble(x));
   }
   
   // returns sequence 
   public ArrayList<Double> getSequence()
   {
       return sequence;
   }
   
   // returns the product of 1..x 
   // e.g. factorial(4) = 1 * 2 * 3 * 4 returns 24 
   public int factorial(int x)
   {
       int fact = 1;
       int i;
       for(i=1;i<=x;i++){fact = fact*i;}
       return fact;
   }
   
   // returns true iff all of the items on seq are equal 
   // e.g. allEqual(<4, 4, 4>) returns true, and allEqual(<3, 3, -2>) returns false 
   public boolean allEqual(ArrayList<Double> seq)
   {
       for(double i : seq){
           if(i != seq.get(0)){
               return false;
           }
       }
       return true;
   }
   
   // returns a new ArrayList holding the differences between adjacent items on seq 
   // e.g. differences(<4, 6, 1, 1>) returns <2, -5, 0>  
   public ArrayList<Double> differences(ArrayList<Double> seq)
   {
       if(seq.size()<2){
           return null;
       }
       ArrayList<Double> diff = new ArrayList<>();
       int i;
       for(i=0;i<seq.size()-1;i++){diff.add(seq.get(i+1)-seq.get(i));}
       return diff;
   }
   
   // returns the next term in the simplest polynomial that generates sequence 
   // implements Steps 1-3 of the algorithm description on the project web page 
   public Term nextTerm()
   {
       ArrayList<Double> seq = sequence;
       int exponent = 0;
       while(!allEqual(seq) && exponent < sequence.size()){
           seq = differences(seq);
           exponent++;
       }
       double coefficient = seq.get(0)/factorial(exponent);
       return new Term(coefficient,exponent);
   }
   
   // returns the value to subtract from the kth index of term t
   // e.g. termUpdate(Term<2, 3>, 4) returns 128 
   public double termUpdate(Term t, int k)
   {
       return t.getCoefficient()*java.lang.Math.pow(k,t.getExponent());
   }
   
   // subtracts from each item in sequence the effect of the term t 
   // implements Step 4 of the algorithm description on the project web page 
   public void updateSequence(Term t)
   {
       ArrayList<Double> nextseq = new ArrayList<>();
       int i;
       for(i=1;i<=sequence.size();i++){nextseq.add(sequence.get(i-1)-termUpdate(t,i));}
       sequence = nextseq;
   }
   
   // returns the simplest polynomial that generates sequence 
   // implements the algorithm description on the project web page 
   // and also displays the polynomial as a String 
   public Polynomial solveSequence()
   {
       Polynomial poly = new Polynomial();
       while(!allEqual(sequence)){
           poly.addTerm(nextTerm());
           updateSequence(nextTerm());
       }
       poly.addTerm(nextTerm()); //Adds last term, when allEqual(sequence).
       System.out.println(poly.displayPolynomial());
       return poly;
   }
   
   // reads the file filename, solves the sequences therein, and displays the results 
   public static void solveFileSequences(String filename)
   {
       for (String s : (new FileIO(filename)).lines)
           if (s.length() > 0 && "0123456789-".indexOf(s.charAt(0)) != -1)
           {
              System.out.print("Sequence: " + s + "\nPolynomial: ");
              (new Sequence(s)).solveSequence();
              System.out.println();
           }
   }

}