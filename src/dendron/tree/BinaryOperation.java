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
public class BinaryOperation implements ExpressionNode{
static String ADD="+";
static String DIV="/";
static String MUL="*";
static Collection<String> OPERATORS= new LinkedList<String>();
static String SUB="-";
 ExpressionNode leftChild;
 ExpressionNode rightChild;
 String Operator;

BinaryOperation​(String operator, ExpressionNode leftChild, ExpressionNode rightChild){
    
    OPERATORS.add(ADD);
    OPERATORS.add(DIV);
    OPERATORS.add(MUL);
    OPERATORS.add(SUB);
    
    if(OPERATORS.contains(operator)& leftChild!=null & rightChild!=null){
    this.leftChild=leftChild;
    this.rightChild=rightChild;
    this.Operator=operator;
  
    }else {
          
           Errors.report(Errors.Type.ILLEGAL_VALUE, "The invalid binary operator ");      
            
          
    }
}
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        
        int left=leftChild.evaluate(symTab);
        int right=rightChild.evaluate(symTab);
        int result=0;
         if(Operator.equals(ADD)){
            
           result= left+right;
           
        }else if(Operator.equals(SUB)){
         
            result= left-right; 
            
    }else if(Operator.equals(MUL)){
         
            result=left*right;
            
    }else if(Operator.equals(DIV)){
         
            result=left/right;         
    }
         return result;
    }
    @Override
    public void infixDisplay() {
        System.out.print("( ");
        leftChild.infixDisplay();
        System.out.print(" "+Operator+" ");
        rightChild.infixDisplay();
        System.out.print(" )");
      }

    @Override
    public List<Machine.Instruction> emit() {
        List<Machine.Instruction> Load= new LinkedList<Machine.Instruction>();
         
         if(Operator.equals(ADD)){
            
           Load.addAll( leftChild.emit());
           Load.addAll( rightChild.emit());
            Load.add(new Machine.Add());
           
        }else if(Operator.equals(SUB)){
         
            Load.addAll( leftChild.emit());
           Load.addAll( rightChild.emit());
            Load.add(new Machine.Subtract());
            
    }else if(Operator.equals(MUL)){
         
          Load.addAll( leftChild.emit());
           Load.addAll( rightChild.emit());
            Load.add(new Machine.Multiply());
            
    }else if(Operator.equals(DIV)){
         
           Load.addAll( leftChild.emit());
           Load.addAll( rightChild.emit());
            Load.add(new Machine.Divide());
            
    }
      return Load;
    }

}
