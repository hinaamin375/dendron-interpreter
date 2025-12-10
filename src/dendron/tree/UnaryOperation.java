/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author userr
 */
public class UnaryOperation implements ExpressionNode{

    static String NEG="_";
    static Collection<String> OPERATORS= new LinkedList<String>();
    static String SQRT="#";
    ExpressionNode expr=null;
    String operator;
    
  public   UnaryOperation​(String operator, ExpressionNode expr){
   
      OPERATORS.add(NEG);
    
      OPERATORS.add(SQRT);
    
           if(OPERATORS.contains(operator)& expr!=null){

             this.expr=expr;  
             this.operator=operator;
  
    }else {
          
           Errors.report(Errors.Type.ILLEGAL_VALUE, "The invalid unary operator ");      
            
          
    }
         
      }
    
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        int result;
       int expression=  expr.evaluate(symTab);
        double t;
       
        if(operator.equals(NEG)){
            
           return expression*(-1);
           
        }else {
            
             double squareroot = expression/ 2;
  
    do {
        
        t = squareroot;
        squareroot = (t + (expression / t)) / 2;
        
    } while ((t - squareroot) != 0);
     return  (int) squareroot;
        }
   
    }

    @Override
    public void infixDisplay() {
     
   System.out.print(operator);
   expr.infixDisplay();
    }

    @Override
    public List<Machine.Instruction> emit() {
          List<Machine.Instruction> Load= new LinkedList<Machine.Instruction>();
      
        if(operator.equals(NEG)){
          
           Load.addAll( expr.emit());
            Load.add(new Machine.Negate());
           
        }else if (operator.equals(SQRT)){
             Load.addAll( expr.emit());
            Load.add(new Machine.SquareRoot());
        }
    return Load;   
    }
       
}
