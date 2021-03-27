package jvm;

/**
 * String的不可变性
 */
public class StringTest {

  String str = new String("good");
  char[] ch = { 't', 'e', 's', 't' };

  public void change(String str, char ch[]) {
    str = "test ok";
    ch[0] = 'b';
  }

  public static void main(String[] args) {
    StringTest st = new StringTest();
    st.change(st.str, st.ch);
    System.out.println(st.str);
    System.out.println(st.ch);
    ;
  }
}