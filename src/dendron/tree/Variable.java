/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author userr
 */
public class Variable implements ExpressionNode{
  public  String name;
    Variable​(String name){
        
        this.name=name;
    }

    @Override
    public int evaluate(Map<String, Integer> symTab) {
       
         if (symTab.containsKey(name)== false){
             
             Errors.report(Errors.Type.UNINITIALIZED, "The Variable value is not in Sym table");
         } 
      return symTab.get(name);
              }

    @Override
    public void infixDisplay() {
        
        System.out.print(this.name);
          }

    @Override
    public List<Machine.Instruction> emit() {
        
        List<Machine.Instruction> Load= new LinkedList<Machine.Instruction>();
        Load.add(new Machine.Load(name));
    return Load;  
    }
    
}
