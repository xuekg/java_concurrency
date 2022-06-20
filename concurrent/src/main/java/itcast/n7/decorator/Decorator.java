package itcast.n7.decorator;

/**
 * @author xuekg
 * @description 装修基本类
 * @date 2022/6/20 18:43
 **/
public class Decorator implements IDecorator{

    /**
     * 基本实现方法
     */
    @Override
    public void decorate() {
        System.out.println("水电装修、天地板以及粉刷墙。。。");
    }

}