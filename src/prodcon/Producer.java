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

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Produces random operations.
 * 
 * @author mmiranda96 
 * @author rosamariaramirez 
 */
public class Producer extends Thread {
    private static final char[] OPERATIONS = {
        Operation.ADDITION,
        Operation.SUBSTRACTION,
        Operation.MULTIPLICATION,
        Operation.DIVISION
    };
    
    private final int id;
    private final int m, n;
    private final int sleepTime;
    private final SecureRandom r;
    private final Buffer buff;
    private boolean isRunning = true;
    
    public Producer(int id, int m, int n, int sleepTime, Buffer buff) {
        this.id = id;
        this.m = m;
        this.n = n;
        this.sleepTime = sleepTime;
        this.buff = buff;
        this.r = new SecureRandom();
    }
    
    @Override
    public void run() {
        while (isRunning) {
            Operation op = new Operation(
                    Producer.OPERATIONS[r.nextInt(4)],
                    new Float(r.nextInt(this.n - this.m) + m),
                    new Float(r.nextInt(this.n - this.m) + m)
            );
            this.buff.produce(this.id, op);
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                 Logger.getLogger(Buffer.class.getName())
                       .log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void terminate() {
        this.isRunning = false;
    }
}
