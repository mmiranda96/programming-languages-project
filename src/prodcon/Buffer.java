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

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Buffer is a queue structure to comunicate a producer and a consumer
 *
 * @author mmiranda96
 * @author rosamariaramirez 
 */
public class Buffer {
    private final LinkedList<Operation> buff;
    private final int buffSize; 
    private final ProdCon ui;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    
    public Buffer(int buffSize, ProdCon ui) {
        this.buff = new LinkedList();
        this.buffSize = buffSize;
        this.ui = ui;
    }
    
    /**
     * Stores an operation in the buffer. If the buffer is full,
     * waits until a space is available.
     * 
     * @param   id  ID of the producer
     * @param   op  Operation to store
     */
    public void produce(int id, Operation op) {
        lock.lock();
        try {
            while (this.buff.size() == this.buffSize) {
                try {
                    notFull.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buffer.class.getName())
                          .log(Level.SEVERE, null, ex);
                }
            }
            this.buff.add(op);
            this.ui.writeProducedOperation(id, op);
            this.ui.setBufferProgressBar(this.buff.size());
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Returns and deletes from the buffer an operation.
     * If the buffer is empty, waits until an operation exists.
     * 
     * @return  The first operation in the queue
     */
    public Operation consume() {
        lock.lock();
        try {
            while (this.buff.isEmpty()) {
                try {
                    notEmpty.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buffer.class.getName())
                          .log(Level.SEVERE, null, ex);
                }
            }
            Operation op = this.buff.remove();
            this.ui.setBufferProgressBar(this.buff.size());
            notFull.signal();
            return op;
        } finally {
            lock.unlock();
        }
    }
}
