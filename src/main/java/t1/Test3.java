package t1;

/**
 * Created by Ivan Lu on 2018/8/26.
 */
public class Test3 {
    public static void main(String[] args) {
        MyCommonThread t1 = new MyCommonThread();
        Thread t2 = new Thread(new DaemonThread());
        t2.setDaemon(true);
        t1.start();
        t2.start();



    }

}

class MyCommonThread extends Thread{

    @Override
    public void run() {
        for (int i=0;i<5;i++){
        System.out.println("前台线程"+i+"执行");
        try {
            Thread.sleep(7);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        }
    }
}

class DaemonThread implements Runnable{

    public void run() {
        for (int i=0;i<100000l;i++){
            System.out.println("后台线程"+i+"执行");
            try {
                Thread.sleep(7);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
