package SynchronizedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，
 * 那么其它线程就无法进入对象的同步方法或者同步控制块中，
 * 那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。
 *
 * Created by Ivan Lu on 2018/8/27.
 */
public class WaitNotifyTest {

    public synchronized void before(){
        System.out.println("before");
        notifyAll();

    }

    public synchronized void after(){
        try{
            wait();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("after");

    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        WaitNotifyTest test = new WaitNotifyTest();
        service.execute(()->test.after());
        service.execute(()->test.before());


    }
}
