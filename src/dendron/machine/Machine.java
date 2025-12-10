/*
 * file: Machine.java
 */

package dendron.machine;

import java.util.List;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import dendron.Errors;

/**
 * An abstraction of a computing machine that reads instructions
 * and executes them. It has an instruction set, a symbol table
 * for variables (instead of general-purpose memory), and a
 * value stack on which calculations are performed.
 *
 * (Everything is static to avoid the need to master the subtleties
 * of nested class instantiation or to pass the symbol table and
 * stack into every instruction when it executes.)
 *
 * THIS CLASS IS INCOMPLETE. The student must add code to it.
 *
 * @author James Heliotis
 * @author YOUR NAME HERE
 */
public class Machine {

    /** Do not instantiate this class. */
    private Machine() {}

    public static interface Instruction {
        /**
         * Run this instruction on the Machine, using the Machine's
         * value stack and symbol table.
         */
        void execute();

        /**
         * Show the instruction using text so it can be understood
         * by a person.
         * @return a short string describing what this instruction will do
         */
        @Override
        String toString();

       
    }

    private static Map< String, Integer > table = null;
    private static Stack< Integer > stack = null;

    /**
     * Reset the Machine to a pristine state.
     * @see Machine#execute
     */
    private static void reset() {
        stack = new Stack<>();  
        table = new HashMap<>();
    }

    /**
     * Generate a listing of a program on standard output by
     * calling the toString() method on each instruction
     * contained therein, in order.
     *
     * @param program the list of instructions in the program
     */
    public static void displayInstructions(
            List< Machine.Instruction > program ) {
        System.out.println( "\nCompiled code:" );
        for ( Machine.Instruction instr: program ) {
            System.out.println( instr );
        }
        System.out.println();
    }

    /**
     * Run a "compiled" program by executing in order each instruction
     * contained therein.
     * Report on the final size of the stack (should normally be empty)
     * and the contents of the symbol table.
     * @param program a list of Machine instructions
     */
    public static void execute( List< Instruction > program ) {
        reset();
        System.out.println("Executing compiled code...");
        for ( Instruction instr: program ) {
            instr.execute();
        }
        System.out.println( "Machine: execution ended with " +
                stack.size() + " items left on the stack." );
        System.out.println();
        Errors.dump( table );
    }

    /**
     * The ADD instruction
     */
    public static class Add implements Instruction {
        /**
         * Run the microsteps for the ADD instruction.
         */
        @Override
        public void execute() {
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push( op1 + op2 );
        }

        /**
         * Show the ADD instruction as plain text.
         * @return "ADD"
         */
        @Override
        public String toString() {
            return "ADD";
        }
    }

    /**
     * The STORE instruction
     */
    public static class Store implements Instruction {
        /** stores name of target variable */
        private String name;

        /**
         * Create a STORE instruction
         * @param ident the name of the target variable
         */
        public Store( String ident ) {
            this.name = ident;
        }
        /**
         * Run the microsteps for the STORE instruction.
         */
        @Override
        public void execute() {
            table.put( this.name, stack.pop() );
        }
        /**
         * Show the STORE instruction as plain text.
         * @return "STORE" followed by the target variable name
         */
        @Override
        public String toString() {
            return "STORE " + this.name;
        }
    }
       /**
     * The Print instruction
     */
    public static class Print implements Instruction {
    
        /**
         * Create a Print instruction
         */
        public Print(  ) {
            
        }
        /**
         * Run the microsteps for the Print instruction.
         */
        @Override
        public void execute() {
            System.out.println("*** "+stack.pop());
            
        }
        /**
         * Show the Print instruction as plain text.
         * @return "Print" followed by the target variable name
         */
        @Override
        public String toString() {
            return "PRINT";
        }
    }
    
    /**
     * The Subtract instruction
     */
    public static class Subtract implements Instruction {
        /**
         * Run the microsteps for the Subtract instruction.
         */
        @Override
        public void execute() {
            int op1 = stack.pop();
            int op2 = stack.pop();
            
            stack.push( op2 - op1 );
        }

        /**
         * Show the Subtract instruction as plain text.
         * @return "Subtract"
         */
        @Override
        public String toString() {
            return "SUB";
        }
    }
        /**
     * The Multiply instruction
     */
    public static class Multiply implements Instruction {
        /**
         * Run the microsteps for the Multiply instruction.
         */
        @Override
        public void execute() {
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push( op1 * op2 );
        }

        /**
         * Show the Multiply instruction as plain text.
         * @return "Multiply"
         */
        @Override
        public String toString() {
            return "MUL ";
        }
    }
        /**
     * The Load instruction
     */
    public static class Load implements Instruction {
        /** stores name of target variable */
        private String name;

        /**
         * Create a Load instruction
         * @param ident the name of the target variable
         */
        public Load( String ident ) {
            this.name = ident;
        }
        /**
         * Run the microsteps for the Load instruction.
         */
        @Override
        public void execute() {
            
            if(!table.containsKey( this.name )){
                
            Errors.report(Errors.Type.ILLEGAL_VALUE, "The value is not in the HashMap");
          
            } 
           stack.push( table.get( this.name ));
        }
        /**
         * Show the Load instruction as plain text.
         * @return "Load" followed by the target variable name
         */
        @Override
        public String toString() {
            return "LOAD " + this.name;
        }
    }
          /**
     * The Divide instruction
     */
    public static class Divide implements Instruction {
        /**
         * Run the microsteps for the Divide instruction.
         */
        @Override
        public void execute() {
            int op1 = stack.pop();
          
            int op2 = stack.pop();
            
           
            if(op1==0){
                
            Errors.report(Errors.Type.DIVIDE_BY_ZERO, "The value of divisor is 0");
          
            }
            stack.push( op2 / op1 );
        }

        /**
         * Show the Divide instruction as plain text.
         * @return "Divide"
         */
        @Override
        public String toString() {
            return "DIV";
        }
    }
            /**
     * The Negate instruction
     */
    public static class Negate implements Instruction {
        /**
         * Run the microsteps for the Negate instruction.
         */
      
        @Override
        public void execute() {
            int op = stack.pop();
            stack.push( op *(-1) );
        }

        /**
         * Show the Negate instruction as plain text.
         * @return "Negate"
         */
        @Override
        public String toString() {
            return "NEG";
        }
    }
                /**
     * The SquareRoot instruction
     */
    public static class SquareRoot implements Instruction {
        /**
         * Run the microsteps for the SquareRoot instruction.
         */
        @Override
        public void execute() {
            int op = stack.pop();
            double t;
           if(op<0){
                
            Errors.report(Errors.Type.ILLEGAL_VALUE, "The square root of Negative value is a math error");
        
            }
            
          double squareroot = op / 2;
  
    do {
        
        t = squareroot;
        squareroot = (t + (op / t)) / 2;
        
    } while ((t - squareroot) != 0);
    
            stack.push( (int)squareroot);
        }

        /**
         * Show the SquareRoot instruction as plain text.
         * @return "SquareRoot"
         */
        @Override
        public String toString() {
            return "SQRT";
        }
    }
               /**
     * The PushConst instruction
     */
    public static class PushConst implements Instruction {
          /** stores target variable */
        private int constant;

        /**
         * Create a PushConst instruction
         * @param constant the name of the target variable
         */
        public PushConst( int constant ) {
            this.constant = constant;
        }
        /**
         * Run the microsteps for the PushConst instruction.
         */
        @Override
        public void execute() {
           
            stack.push( this.constant);
        }

        /**
         * Show the PushConst instruction as plain text.
         * @return "PushConst"
         */
        @Override
        public String toString() {
            return "PUSH "+constant;
        }
    }

       
    }

