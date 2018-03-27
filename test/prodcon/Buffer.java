/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcon;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmiranda96 
 * @author rosamariaramirez 
 */
public class Buffer {
    private LinkedList<Operation> buff;
    private int buffSize;
    
    public Buffer(int buffSize) {
        this.buff = new LinkedList();
        this.buffSize = buffSize;
    }
    
    synchronized public void produce(Operation op) {
        if(this.buff.size() == this.buffSize) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buff.add(op);
        notify();
    }
    
    synchronized public Operation consume() {
        if(this.buff.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Operation op = this.buff.remove();
        notify();
        return op;
    }
    
    synchronized public void print(String msg) {
        System.out.println(msg);
    }
}