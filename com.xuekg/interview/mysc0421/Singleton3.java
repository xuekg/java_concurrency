package interview.mysc0421;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Singleton3 implements Serializable {
  private static final long serialVersionUID = -483207184632804328L;

  private static Singleton3 instance = new Singleton3();

  private Singleton3() {
  }

  public static Singleton3 getInstance() {
    return instance;
  }

  public static void main(String[] args) throws Exception {
    Singleton3 singleton3 = Singleton3.getInstance();

    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton"));
    oos.writeObject(singleton3);

    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton"));
    Singleton3 serializeSingleton = (Singleton3) ois.readObject();

    System.out.println(singleton3 == serializeSingleton);
  }
}