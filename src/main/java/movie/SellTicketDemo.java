package movie;

/**
 * Created by Ivan Lu on 2018/8/30.
 */
public class SellTicketDemo {

    public static void main(String[] args) {
        //创建Runable接口的实现类
        SellTicket sellTicket = new SellTicket();

        //创建Thread对象
        Thread t1 = new Thread(sellTicket, "窗口1");
        Thread t2 = new Thread(sellTicket, "窗口2");
        Thread t3 = new Thread(sellTicket, "窗口3");

        //启动线程对象
        t1.start();
        t2.start();
        t3.start();


    }
}
