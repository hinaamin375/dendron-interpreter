/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dendron.tree;

import dendron.machine.Machine;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author userr
 */
public class Assignment implements ActionNode {
 String name;
 ExpressionNode rhs;
 
    public Assignment​(String ident, ExpressionNode rhs){
    this.name=ident;
    this.rhs=rhs;
}
    @Override
    public void execute(Map<String, Integer> symTab) {
     
      int result=  rhs.evaluate(symTab);
      
      symTab.put(name,result);
    
          }

    @Override
    public void infixDisplay() {
     System.out.print(name+" := ");
        rhs.infixDisplay();
    }

    @Override
    public List<Machine.Instruction> emit() {
          List<Machine.Instruction> Load= new LinkedList<Machine.Instruction>();
         Load.addAll( rhs.emit());
         Load.add(new Machine.Store(name));
            return Load;
    }
      
    
}
