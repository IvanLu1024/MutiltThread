package t1;

/**
 * Created by Ivan Lu on 2018/8/26.
 */
public class Test2 {
    public static void main(String[] args) {
        new MyThread1("低级",1).start();
        new MyThread1("中级",5).start();
        new MyThread1("高级",10).start();
    }



}
class MyThread1 extends Thread {
    public MyThread1(String name, int pro) {
        super(name);// 设置线程的名称
        this.setPriority(pro);// 设置优先级
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(this.getName() + "线程第" + i + "次执行！");
            if (i % 5 == 0)
                Thread.yield();
        }
    }
}


