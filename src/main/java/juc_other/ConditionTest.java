package juc_other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这个例子中thread1执行到condition.await()时，
 * 当前线程会被挂起，直到thread2调用了condition.signalAll()方法之后，
 * thread1才会重新被激活执行。
 *
 * 传统线程的通信方式，Condition都可以实现。
 * Condition的强大之处在于它可以为多个线程间建立不同的Condition。
 *
 * Created by Ivan Lu on 2018/8/31.
 */
public class ConditionTest {

    private static Lock lock=new ReentrantLock();

    private static Condition condition=lock.newCondition();



    public static void main(String[] args) {
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{

                    System.out.println(Thread.currentThread().getName()+" run ");
                    System.out.println(Thread.currentThread().getName()+" waiting for condition");
                    try{
                        condition.await();

                        System.out.println("Thread continue --- ");
                    }catch (InterruptedException e){
                        System.err.println("Thread is interrupted");

                        Thread.currentThread().interrupt();
                    }

                }finally {
                    lock.unlock();
                }


            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " run");
                    System.out.println(Thread.currentThread().getName() + " sleep 3 secs");
                    try {
                        Thread.sleep(3000l);
                    } catch (InterruptedException e) {
                        System.err.println(Thread.currentThread().getName() + " interrupted");
                        Thread.currentThread().interrupt();
                    }
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }


            }
        });
        thread1.start();
        thread2.start();



    }
}
