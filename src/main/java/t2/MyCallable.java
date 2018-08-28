package t2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Ivan Lu on 2018/8/27.
 */
public class MyCallable implements Callable<Integer> {
    public Integer call() throws Exception {
        System.out.println("running --- ");
        return 123;
    }

    public static void main(String[] args) throws Exception{
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(myCallable);
        Thread thread = new Thread(integerFutureTask);
        thread.start();
        System.out.println(integerFutureTask.get());


    }
}
