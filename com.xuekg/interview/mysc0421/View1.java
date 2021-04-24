package interview.mysc0421;

import java.util.ArrayList;
import java.util.List;

public class View1 {
  public static void main(String[] args) {
    List<ParentCar> list = new ArrayList<>();
    ParentCar bicycle = new Bicycle();
    list.add(bicycle);
    ParentCar car = new Car();
    list.add(car);
    ParentCar wheelbarrow = new Wheelbarrow();
    list.add(wheelbarrow);

    for (ParentCar parentCar : list) {
      System.out.println(parentCar.toString());
    }
  }
}

class ParentCar {

}

class Bicycle extends ParentCar {
  @Override
  public String toString() {
    return "我是一辆自行车";
  }
}

class Car extends ParentCar {
  @Override
  public String toString() {
    return "我是一辆汽车";
  }
}

class Wheelbarrow extends ParentCar {
  @Override
  public String toString() {
    return "我是一辆独轮车";
  }
}