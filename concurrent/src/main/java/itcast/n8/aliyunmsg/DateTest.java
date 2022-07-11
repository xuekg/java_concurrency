package itcast.n8.aliyunmsg;

import java.util.Date;

/**
 * @author xuekg
 * @description 本质上，对象的引用是按值传递的
 * 因此，我们可以修改参数对象的内部状态，但是对参数对象重新赋值时没有意义的
 * @date 2022/7/11 9:07
 **/
public class DateTest {

    public static void main(String[] args) {
        Date d1 = new Date("1 Apr 98");
        nextDayUpdate(d1);
        System.out.println("d1 after nextDayUpdate:" + d1);

        System.out.println("---------------------");

        Date d2 = new Date("1 Apr 98");
        nextDayReplace(d2);
        System.out.println("d2 after nextDayReplace:" + d2);
    }

    private static void nextDayReplace(Date arg) {
        arg = new Date(arg.getYear(), arg.getMonth(), arg.getDate() + 1);
        System.out.println("arg in nextDayReplace:" + arg);
    }

    private static void nextDayUpdate(Date arg) {
        arg.setDate(arg.getDate() + 1);
        System.out.println("arg in nextDayUpdate:" + arg);
    }
}
