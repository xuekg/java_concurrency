package algo_10;


/**
 * @author xuekg
 * @description
 * @date 2021/9/21 21:06
 **/
public class ReverseList {

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        ListNode listNode = reverseList.reverseList(new ListNode());

    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
