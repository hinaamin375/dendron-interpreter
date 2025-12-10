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
public class Program implements ActionNode{
      List<ActionNode> ChildNodes;
    
    public Program(){
        ChildNodes=new LinkedList<ActionNode>();
    }
    
    public void addAction​(ActionNode newNode){
    ChildNodes.add(newNode);
}
    @Override
    public void execute(Map<String, Integer> symTab) {
     int i=0;
        
       while(i<ChildNodes.size()){
         
           ChildNodes.get(i).execute(symTab);
    //       System.out.print(symTab);
           i++;
       }   
    }

    @Override
    public void infixDisplay() {
        int i=0;
        
       while(i<ChildNodes.size()){
         
           ChildNodes.get(i).infixDisplay();
           System.out.println("");
           i++;
       }
    }

    @Override
    public List<Machine.Instruction> emit() {
         int i=0;
          List<Machine.Instruction> Load= new LinkedList<Machine.Instruction>();
        
       while(i<ChildNodes.size()){
           
          Load.addAll( ChildNodes.get(i).emit());

           i++;
       }
       return Load;
    }
   
}
