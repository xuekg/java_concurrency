package itcast.zheng.d0223;

/**
 * @author xuekg
 * @description
 * @date 2023/2/23 10:47
 **/
public class Number {
    Node head = null;
    Node tail = null;
    int size = 0;

    public class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Number(String string) throws IllegalArgumentException, NullPointerException {
        // TODO Auto-generated constructor stub
        for (int i = string.length() - 1; i >= 0; i--) {
            char c = string.charAt(i);
            int digit = Integer.parseInt(String.valueOf(c));
            Node n = new Node(digit);
            if (head == null) {
                head = n;
                tail = n;
            } else {
                tail.next = n;
                tail = tail.next;
            }
        }
    }

    public Number add(Number n2) throws NullPointerException {
        // TODO Auto-generated method stub
        Node a = head;
        Node b = n2.head;
        Number result = new Number("");
        Node current = null;
        int carry = 0;

        while (a != null || b != null) {
            int val_1 = 0;
            int val_2 = 0;
            if (a != null) val_1 = a.data;
            if (b != null) val_2 = b.data;
            int sum = val_1 + val_2 + carry;
            carry = sum / 10;
            int digit = sum % 10;

            if (current == null) {
                current = new Node(digit);
                result.head = current;
            } else {
                current.next = new Node(digit);
                current = current.next;
            }

            if (a != null) a = a.next;
            if (b != null) b = b.next;
        }
        if (carry > 0) current.next = new Node(carry);
        return result;
    }

    //写一个length() method

	/*public Number multiplyByDigit(int digit) throws IllegalArgumentException{
		Number result = new Number("");
		Node current = head;
		int carry = 0;
		while(current!=null) {
			int product = carry + current.data * digit;
			int final_digit = product % 10;
			carry = product / 10;
			result.head = new Number.Node(final_digit);
			if(result.head == null) {
				result.tail = result.head;
			}
			else {
				result.head.next = current;
			}
			result.size++;
			current = current.next;
		}
		return null;
	}*/

    public Number multiply(Number n2) throws NullPointerException {
        // TODO Auto-generated method stub
        Node a = head;
        Number result = new Number("");
        int power = 0;

        return null;
    }


    public int compareTo(Number n2) throws NullPointerException {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public boolean equals(Object obj) {
        return true;
    }

    //写两个exceptions
}
