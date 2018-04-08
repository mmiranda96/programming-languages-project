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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Consumes an operation buffer and evaluates them.
 *
 * @author mmiranda96 
 * @author rosamariaramirez 
 */
public class Consumer extends Thread {
    private final int id;
    private final int sleepTime;
    private final Buffer buff;
    
    public Consumer(int id, int sleepTime, Buffer buff) {
        this.id = id;
        this.sleepTime = sleepTime;
        this.buff = buff;
    }
    
    @Override
    public void run() {
        this.buff.print("Running Consumer " + this.id);
        Operation op;
        while (true) {
            op = this.buff.consume();
            this.buff.print("Consumer " + this.id + " consumed " + op + 
                            ". Result is " + op.eval()); 
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                 Logger.getLogger(Buffer.class.getName())
                       .log(Level.SEVERE, null, ex);
            }
        }
    }
}
