/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dendron.tree;

import dendron.machine.Machine;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author userr
 */
public class Constant implements ExpressionNode{
 
   public int Value;
    
    Constant​(int value){
        this.Value=value;
    }
    
    @Override
    public int evaluate(Map<String, Integer> symTab) {
        
    return Value ;
    }

    @Override
    public void infixDisplay() {
    System.out.print(Value);
    }

    @Override
    public List<Machine.Instruction> emit() {
     
        List<Machine.Instruction> Load= new LinkedList<Machine.Instruction>();
        Load.add(new Machine.PushConst(Value));
    return Load;  
    
    }
    
}
