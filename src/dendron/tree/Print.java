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
public class Print implements ActionNode{
    
  ExpressionNode printee;
    
    Print​(ExpressionNode printee){
        this.printee=printee;
    }
 

    @Override
    public void infixDisplay() {
       System.out.print("Print ");
        printee.infixDisplay();
    }

    @Override
    public List<Machine.Instruction> emit() {
         List<Machine.Instruction> Load= new LinkedList<Machine.Instruction>();
         Load.addAll( printee.emit());
         Load.add(new Machine.Print());
            return Load;
    }

    @Override
    public void execute(Map<String, Integer> symTab) {
       int result=  printee.evaluate(symTab);
      System.out.print("=== "+result);
      
    }
     
}
