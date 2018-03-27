/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcon;

/**
 *
 * @author mmiranda96 
 * @author rosamariaramirez 
 */
public class Operation {
    public static final char ADDITION = '+';
    public static final char SUBSTRACTION = '-';
    public static final char MULTIPLICATION = '*';
    public static final char DIVISION = '/';
    
    private char operation;
    private Float a, b;
    
    public Operation(char operation, Float a, Float b) {
        if( operation != Operation.ADDITION &&
            operation != Operation.SUBSTRACTION &&
            operation != Operation.MULTIPLICATION &&
            operation != Operation.DIVISION) {
            throw new IllegalArgumentException("Illegal operation: "
                    + operation);        
        }
        this.operation = operation;
        this.a = a;
        this.b = b;
    }
    
    public Float eval() {
        switch(this.operation){
            case Operation.ADDITION:
                return a + b;
            case Operation.SUBSTRACTION:
                return a - b;
            case Operation.MULTIPLICATION:
                return a * b;
            case Operation.DIVISION:
                return a / b;
            default:
                return Float.NaN;
        }
    }
    
    @Override
    public String toString() {
        return "(" + this.operation + " " + this.a + " " + this.b + ")";
    }
}