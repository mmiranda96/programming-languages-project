/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcon;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmiranda96 
 * @author rosamariaramirez 
 */
public class Consumer extends Thread {
    private int id;
    private int sleepTime;
    private Random r;
    private Buffer buff;
    
    public Consumer(int id, int sleepTime, Buffer buff) {
        this.id = id;
        this.sleepTime = sleepTime;
        this.buff = buff;
        this.r = new Random(System.currentTimeMillis());
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        while(true) {
            Operation op = this.buff.consume();
            try {
                this.buff.print("Consumer " + this.id + " consumed " + op + 
                        ". Result is " + op.eval()); 
            } catch (ArithmeticException ex) {
                this.buff.print("Consumer " + this.id + " consumed " + op + 
                        ". Result is NaN"); 
            }
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                 Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
