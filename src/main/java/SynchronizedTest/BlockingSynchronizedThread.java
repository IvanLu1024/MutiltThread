package SynchronizedTest;


import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用阻塞队列实现线程同步
 *
 * LinkedBlockingQueue<E>是一个基于链表的队列，先进先出的顺序（FIFO），范围任意的blocking queue。
 *
 * Created by Ivan Lu on 2018/8/31.
 */
public class BlockingSynchronizedThread {
    /**
     * 定义一个存储的队列
     */
    private LinkedBlockingQueue<Integer> queue=new LinkedBlockingQueue<>();

    /**
        定义队列的最大长度
     */
    private static final int MAX_SIZE=10;

    /**
     * 定义启动线程的标志，为0时，启动生产商品的线程；为1时，启动消费商品的线程
     */
    private int flag=0;

    private class LinkedThread implements Runnable{

        @Override
        public void run() {
            int new_flag=flag++;
            System.out.println("启动线程 ："+new_flag);
            if (new_flag==0){
                for (int i=0;i<MAX_SIZE;i++){
                    int productNum = new Random().nextInt(255);
                    System.out.println("生产的产品号为："+productNum);
                    try{
                        queue.put(productNum);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("仓库里面还有商品："+queue.size()+" 个 ");
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }else {
                for (int i=0;i<MAX_SIZE/2;i++){
                    try{
                        int n = queue.take();
                        System.out.println("消费者买了 "+n+" 号商品");

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("仓库中还剩余商品为： "+queue.size());
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingSynchronizedThread bst=new BlockingSynchronizedThread();
        LinkedThread linkedThread = bst.new LinkedThread();
        Thread thread1 = new Thread(linkedThread);
        Thread thread2 = new Thread(linkedThread);
        thread1.start();
        thread2.start();


    }


}
