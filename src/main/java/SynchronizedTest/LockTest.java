package SynchronizedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ivan Lu on 2018/8/27.
 */
public class LockTest {
    private Lock lock=new ReentrantLock();


    public void func(){
        lock.lock();
        try{
        for (int i=0;i<10;i++){
            System.out.print(i+"\t");

        }
        }finally {
            System.out.print("\t--\t");
            lock.unlock();
        }


    }

    public static void main(String[] args) {
        LockTest l1 = new LockTest();
        LockTest l2 = new LockTest();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->l1.func());
        service.execute(()->l2.func());
    }

}
