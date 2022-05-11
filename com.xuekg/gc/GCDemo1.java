package gc;

/**
 * @author xuekg
 * @description
 * @date 2021/9/23 9:38
 **/
public class GCDemo1 {
    public static void main(String[] args) {
        long counter = 0;
        while (true) {
//            Enhancer enhancer = new Enhancer();
        }
    }

    static class Car {
        public void run() {
            System.out.println("汽车启动，开始行使......");
        }
    }

    static class SafeCar extends Car {
        @Override
        public void run() {
            System.out.println("汽车启动，开始行使.....");
            super.run();
        }
    }
}

