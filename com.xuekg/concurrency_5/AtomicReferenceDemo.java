package concurrency_5;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用 解决ABA问题
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {

        User z3 = new User("z3", 22);
        User l4 = new User("l4", 23);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get());
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [age=" + age + ", name=" + name + "]";
    }

}