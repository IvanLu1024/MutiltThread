package SynchronizedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ivan Lu on 2018/8/27.
 */
public class AwaitSignalExample {

    private Lock lock=new ReentrantLock();

    private Condition condition=lock.newCondition();


    public void before(){
        lock.lock();
        try{
            System.out.println("before");
            condition.signalAll();

        }finally {
            lock.unlock();
        }



    }



    public void after(){
        lock.lock();
        try{
            condition.await();
            System.out.println("after");


        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        AwaitSignalExample example = new AwaitSignalExample();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->example.after());
        service.execute(()->example.before());




    }
}
