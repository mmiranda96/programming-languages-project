/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcon;

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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
    
    private int id;
    private int m, n;
    private int sleepTime;
    private SecureRandom r;
    private Buffer buff;
    
    public Producer(int id, int m, int n, int sleepTime, Buffer buff) {
        this.id = id;
        this.m = m;
        this.n = n;
        this.sleepTime = sleepTime;
        this.buff = buff;
        this.r = new SecureRandom();
    }

    Producer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        while(true) {
            Operation op = new Operation(
                    Producer.OPERATIONS[r.nextInt(4)],
                    new Float(r.nextInt(this.n - this.m) + m),
                    new Float(r.nextInt(this.n - this.m) + m)
            );
            this.buff.produce(op);
            this.buff.print("Producer " + this.id + " produced " + op);
            try {
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException ex) {
                 Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
