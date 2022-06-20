package itcast.n7.decorator;

/**
 * @author xuekg
 * @description 基本装饰类
 * @date 2022/6/20 18:42
 **/
public class BaseDecorator implements IDecorator{

    private IDecorator decorator;

    public BaseDecorator(IDecorator decorator) {
        this.decorator = decorator;
    }

    /**
     * 调用装饰方法
     */
    @Override
    public void decorate() {
        if(decorator != null) {
            decorator.decorate();
        }
    }
}