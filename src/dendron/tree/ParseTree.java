/*
 * file: ParseTree.java
 */

package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;
import dendron.tree.ActionNode;
import dendron.tree.ExpressionNode;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.List;
import java.util.Map;

/**
 * Operations that are done on a Dendron code parse tree.
 *
 * THIS CLASS IS UNIMPLEMENTED. All methods are stubbed out.
 *
 * @author YOUR NAME HERE
 */
public class ParseTree {
public Program prog=null;
 static Map<String, Integer> symTab=new HashMap<String,Integer>();
    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param program the token list (Strings)
     */
    public ParseTree( List< String > program ) {
       List<String> n=new LinkedList<String>();
       n=program;
        n.add(".");
        List< String > list=new LinkedList<String>();
        
        prog= new Program();
        list.add(program.get(0));
   
       for(int i=1;i<program.size();i++){
          
             
           if(n.get(i).trim().equals(":=") || n.get(i).trim().equals("@") ||n.get(i)=="." &i!=0){
        
            
           prog.addAction(parseAction(list));

            list.clear();
           
            }
           
           list.add(program.get(i));
        }
     
    }

    /**
     * Parse the next action (statement) in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for the action
     */
    private ActionNode parseAction( List< String > program ) {
           
      if(program.get(0).equals(":=")){
          
          program.remove(0);
      
           return new Assignment(program.get(0),parseExpr(program.subList(1, program.size())));
           
       } else if (program.get(0).equals("@")){
           
          program.remove(0);
        
         return new Print(parseExpr(program)); 
       }
       return  null;
    }

    /**
     * Parse the next expression in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for this expression
     */
    private ExpressionNode parseExpr( List< String > program ) {
      
        if( program.get(0).equals("+") ||program.get(0).equals("-") ||program.get(0).equals("*") ||program.get(0).equals("/")  ){
       ExpressionNode n1=null;
        ExpressionNode n2=null;
        String operator=program.remove(0);

         for (int i=0 ;i<program.size();i++){
           if(!(program.get(i).trim().equals("_") || program.get(i).equals("#")) & n1==null & n2==null){
            
               n1=parseExpr(program.subList(i, i+1));
              
           }  else  if(!(program.get(i).trim().equals("_") || program.get(i).equals("#")) & n1!=null & n2==null){
              
               n2=parseExpr(program.subList(i, i+1));
                
           }
           else   if((program.get(i).trim().equals("_") || program.get(i).equals("#")) & n1==null & n2==null){
               
               n1=parseExpr(program.subList(i, i+2));
               i=i+1;
             
           }  else  if((program.get(i).trim().equals("_") || program.get(i).equals("#")) & n1!=null & n2==null){
              
                n2=parseExpr(program.subList(i, i+2));
                  i=i+1;
           }
           
         }
     return new BinaryOperation(operator,n1,n2);
           
       }else if(program.get(0).trim().equals("_") || program.get(0).equals("#")){
             
            return new UnaryOperation(program.get(0),parseExpr(program.subList(1, program.size())));
       }
       else {
           if (program.get(0).matches( "^[a-zA-Z].*" )){
               
               return new Variable(program.get(0));}
           else if(program.get(0).matches( "[-+]?\\d+" )) {
               
           return new Constant(Integer.parseInt(program.get(0).trim()));
           }
          
    }
return null;
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see dendron.tree.ActionNode#infixDisplay()
     */
    public void displayProgram() {
       System.out.println("The Program, with expressions in infix notation: \n");
       
            
         prog.infixDisplay();
    
    }
    /**
     * Run the program represented by the tree directly
     * @see dendron.tree.ActionNode#execute(Map)
     */
    public void interpret() {
         System.out.println("Interpreting the parse tree... \n");
          
         prog.execute(symTab);
         
         System.out.println("\n Interpretation complete.\n");
         System.out.println("Symbol Table Contents");
         System.out.println("======================");
         System.out.println(symTab);
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     * @return the Machine.Instruction list
     * @see Machine.Instruction#execute()
     */
    public List< Machine.Instruction > compile() {
          System.out.println("Compiled code:");
          
        return prog.emit();
        
    }
public static void main( String[] args ) {
  
        }
}
