package t1;

/**
 * 可以发现，线程0首先执行，然后线程1执行一次，又了执行一次。发现并不是按照sleep的顺序执行的。
 *
 * 因为使用sleep方法之后，线程是进入阻塞状态的，只有当睡眠的时间结束，才会重新进入到就绪状态，
 * 而就绪状态进入到运行状态，是由系统控制的，我们不可能精准的去干涉它
 * Created by Ivan Lu on 2018/8/26.
 */
public class Test1 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        MyThread myThread1 = new MyThread();
        myThread.start();
        myThread1.start();


    }


}

class MyThread extends Thread{


    @Override
    public void run() {
       for (int i=0;i<3;i++){
           System.out.println(this.getName()+"线程"+i+"执行！"+System.currentTimeMillis());
           try {
               Thread.sleep(50);

           }catch (InterruptedException e)
           {

               e.printStackTrace();
           }

       }
    }
}
