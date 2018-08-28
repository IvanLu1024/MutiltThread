package SynchronizedTest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ivan Lu on 2018/8/27.
 */
public class SynchronizedExample {

    public void fun1(){
        synchronized (SynchronizedExample.class){
            for (int i=0;i<10;i++){
                System.out.print(i+"\t");
            }
            System.out.print("---");

        }

    }

    public static void main(String[] args) {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(()->e1.fun1());
        service.execute(()->e2.fun1());
        //service.shutdown();


    }
}
