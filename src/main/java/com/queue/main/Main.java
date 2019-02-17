package com.queue.main;

import com.example.Message;
import com.example.QueueService;
import com.example.QueueServiceInstance;
import com.queue.user.Consumer;
import com.queue.user.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tina on 2019/2/16.
 */
public class Main {
    /** Multiple producers and consumers use one queueService and one queue  */
    public static void main(String[] args) {
        QueueService queueService = QueueServiceInstance.getQueueServiceInstance("local.properties" ,null);
        final Producer producer1 = new Producer(queueService, "/myQueue1");
        final Producer producer2 = new Producer(queueService, "/myQueue1");
        final Consumer consumer1 = new Consumer(queueService, "/myQueue1");
        final Consumer consumer2 = new Consumer(queueService, "/myQueue1");

        final AtomicInteger value = new AtomicInteger(1);

        Thread producerThread1 = new Thread(){
            public void run() {
                int number = value.incrementAndGet();
                producer1.push("producer1 push " + number);
                System.out.println("producer1 push " + number);
            }
        };

        Thread producerThread2 = new Thread(){
            public void run() {
                int number = value.incrementAndGet();
                producer2.push("producer2 push " + number);
                System.out.println("producer2 push " + number);
            }
        };

        Thread consumerThread1 = new Thread(){
            public void run() {
                Message message = consumer1.pull(3);
                if (message != null) {
                    System.out.println("consumer1 pull one message: " + message.getData());
                    boolean result = consumer1.delete(message.getReceiptHandle());
                    System.out.println("consumer1 delete message: " + result);
                } else {
                    System.out.println("consumer1 pull null message ");
                }
            }
        };

        Thread consumerThread2 = new Thread(){
            public void run() {
                Message message = consumer2.pull(3);
                if (message != null) {
                    System.out.println("consumer2 pull one message: " + message.getData());
                    boolean result = consumer2.delete(message.getReceiptHandle());
                    System.out.println("consumer2 delete message: " + result);
                } else {
                    System.out.println("consumer2 pull null message ");
                }
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 6; i++) {
            executorService.execute(producerThread1);
            executorService.execute(producerThread2);
            if (i % 2 != 0) {
                executorService.execute(consumerThread1);
                executorService.execute(consumerThread2);
            }
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            // do nothing
        }

        System.out.println("Finish multiple threads test!");
    }
}
