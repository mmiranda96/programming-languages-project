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
 * ProdCon is a main class for testing the pipeline
 *
 * @author mmiranda96
 * @author rosamariaramirez 
 */
public class ProdCon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int buffSize = 5;
        int m = 1;
        int n = 10;
        int numProducers = 3;
        int numConsumers = 5;
        int producersSleep = 1000;
        int consumersSleep = 1000;
        
        Buffer buff = new Buffer(buffSize);
        
        Producer[] producers = new Producer[numProducers];
        Consumer[] consumers = new Consumer[numConsumers];
        
        for(int i = 0; i < numProducers; i++) {
            producers[i] = new Producer(i, m, n, producersSleep, buff);
            producers[i].start();
        }
        for(int i = 0; i < numConsumers; i++) {
            consumers[i] = new Consumer(i, consumersSleep, buff);
            consumers[i].start();
        }
    }
    
}
