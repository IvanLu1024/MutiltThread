package t2;

/**
 * Created by Ivan Lu on 2018/8/27.
 */
public class InterruptExample {

    private static class MyThread extends Thread{

        @Override
        public void run() {
            try {
                sleep(200);

                System.out.println("Thread run ---");

            }catch (InterruptedException e){
                System.out.println(e);
                e.printStackTrace();
            }

        }
    }

    private static class MyThread2 extends Thread{

        public void run() {
            int i=0;
            while (!interrupted()){
                System.out.println("running --- "+i);
                i++;

            }
            System.out.println("Thread end ! ");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread2 t2 = new MyThread2();
        t2.start();
        Thread.sleep(200);
        t2.interrupt();



    }
}
