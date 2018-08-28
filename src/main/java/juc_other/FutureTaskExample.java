package juc_other;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 可用于异步获取执行结果或取消执行任务的场景。
 * 当一个计算任务是一个耗时任务，那么就可以用 FutureTask 来封装这个任务，主线程在完成自己的任务之后再去获取结果。
 * Created by Ivan Lu on 2018/8/28.
 */
public class FutureTaskExample {
    public static void main(String[] args) throws InterruptedException ,ExecutionException{
        FutureTask<Integer> futureTask=new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result=0;
                for (int i=0;i<100;i++){
                    Thread.sleep(100);
                    result+=i;

                }
                return result;
            }
        });

        Thread computeThread=new Thread(futureTask);
        computeThread.start();

        Thread otherThread=new Thread(()->{
            System.out.println("other thread is running --- ");
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        });
        otherThread.start();
        System.out.println(futureTask.get());


    }

}
