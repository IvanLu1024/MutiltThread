package SynchronizedTest;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于AbstractQueuedSynchronizer自定义实现一个独占锁
 *
 * Created by Ivan Lu on 2018/8/31.
 */
public class MySynchronizer extends AbstractQueuedSynchronizer{

    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }

        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setState(0);
        setExclusiveOwnerThread(null);

        return true;
    }

    //上锁操作
    public void lock(){
        acquire(1);
    }

    //解锁操作
    public void unlock(){
        release(1);
    }

    public static void main(String[] args) {

        MySynchronizer mySynchronizer=new MySynchronizer();

        Thread thread1=new Thread(new Runnable() {
            @Override

            public void run() {
                mySynchronizer.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+" run ");
                    System.out.println(Thread.currentThread().getName()+"will sleep 5s ---");
                    try{
                        Thread.sleep(5000l);
                        System.out.println(Thread.currentThread().getName()+" continue ");
                    }catch (InterruptedException e){
                        System.err.println(Thread.currentThread().getName() + " interrupted ");
                        Thread.currentThread().interrupt();
                    }


                }finally {
                    mySynchronizer.unlock();
                }




            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                mySynchronizer.lock();
                try{
                    System.out.println(Thread.currentThread().getName()+" run ");

                }finally {
                    mySynchronizer.unlock();
                }

            }
        });

        thread1.start();
        thread2.start();


    }

}
