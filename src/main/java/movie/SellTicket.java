package movie;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ivan Lu on 2018/8/30.
 */
public class SellTicket implements Runnable {

    private int ticket=100;

    private Lock lock=new ReentrantLock();


    @Override

    public  void run() {
       while (true){
           try {
               Thread.sleep(10);
           }catch (InterruptedException e){
               e.printStackTrace();
           }
           try{
               lock.lock();

            {
               if (ticket > 0) {
                   System.out.println(Thread.currentThread().getName() + " 正在销售第 " + (ticket--) + "张票");
               }
           }
           }finally {
               lock.unlock();
           }
        }

    }
}
