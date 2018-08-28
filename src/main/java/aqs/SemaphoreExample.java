package aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Ivan Lu on 2018/8/28.
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        final  int clientCount=3;
        final int requestCount=10;
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0;i<requestCount;i++){
            service.execute(()->{
                try {
                    semaphore.acquire();
                    // TODO: 2018/8/28 返回此信号量中可用的当前许可数
                    System.out.println(semaphore.availablePermits()+" ");

                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }


            });

        }
        service.shutdown();


    }
}
