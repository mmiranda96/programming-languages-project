/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcon;

/**
 *
 * @author Rosa
 */
public class ProdCon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int buffSize = 5;
        int m = 5;
        int n = 10;
        int numProducers = 7;
        int numConsumers = 5;
        int producersSleep = 500;
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
