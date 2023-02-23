package itcast.zheng.d1115;

/**
 * @author xuekg
 * @description
 * @date 2022/11/15 21:30
 **/
public class ListNode {

    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
