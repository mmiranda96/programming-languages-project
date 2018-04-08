/* 
 * The MIT License
 *
 * Copyright 2018 com.github.mmiranda96.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package prodcon;

/**
 * Operation is a basic arithmetic operation with two operands
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
        if (operation != Operation.ADDITION &&
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
        switch (this.operation){
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
