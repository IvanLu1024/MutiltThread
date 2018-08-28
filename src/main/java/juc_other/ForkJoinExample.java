package juc_other;

import java.util.concurrent.*;

/**
 * Created by Ivan Lu on 2018/8/28.
 */
public class ForkJoinExample extends RecursiveTask<Integer> {
    private final int threshold=10;
    private int first;
    private int last;

    public ForkJoinExample(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result=0;
        if(last-first<=threshold){
            //当任务足够小则自行计算
            for (int i=first;i<last;i++){
                result+=i;
            }
        }else {
            //拆分为小任务进行计算
            int middle=(last-first)/2+first;
            ForkJoinExample leftTask=new ForkJoinExample(first,middle);
            ForkJoinExample rightTask=new ForkJoinExample(middle+1,last);
            leftTask.fork();
            rightTask.fork();
            result=leftTask.join() + rightTask.join();



        }

        return result;
    }

    public static void main(String[] args) throws InterruptedException,ExecutionException{
        ForkJoinExample example = new ForkJoinExample(1, 10000);
        ForkJoinPool joinPool = new ForkJoinPool();
        Future result = joinPool.submit(example);
        System.out.println(result.get());



    }
}
