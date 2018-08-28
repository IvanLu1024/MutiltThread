package juc_other;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用BlockingQueue来实现生产者消费者问题
 *
 * 提供了阻塞的 take() 和 put() 方法：如果队列为空 take() 将阻塞，直到队列中有内容；
 * 如果队列为满 put() 将阻塞，直到队列有空闲位置。
 *
 * Created by Ivan Lu on 2018/8/28.
 */
public class ProducerConsumer {
    private static BlockingQueue<String> queue=new ArrayBlockingQueue<String>(5);

    private static class Producer extends Thread{
        @Override
        public void run() {
            try{
                queue.put("product");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Produce -- ");
        }
    }

    private static class Consumer extends Thread{

        @Override
        public void run() {
            try{
                String product = queue.take();


            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("consume ---");
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            Consumer consumer = new Consumer();
            consumer.start();
        }

        for (int i=0;i<6;i++){
            Producer producer = new Producer();
            producer.start();

        }

        for (int i=0;i<3;i++){
            Producer producer = new Producer();
            producer.start();

        }



    }

}
