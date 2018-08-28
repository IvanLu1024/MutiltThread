package aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用来控制一个线程等待多个线程。

 维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，
 那些因为调用 await() 方法而在等待的线程就会被唤醒
 * Created by Ivan Lu on 2018/8/28.
 */
public class CountdownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        final int totoalThread=3;
        CountDownLatch countDownLatch = new CountDownLatch(totoalThread);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0;i<totoalThread;i++){
            service.execute(()->{
                System.out.print(" run --");
                countDownLatch.countDown();
            });


        }
        countDownLatch.await();
        System.out.println("end !");
        service.shutdown();


    }


}
