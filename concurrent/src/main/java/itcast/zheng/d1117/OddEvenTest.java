package itcast.zheng.d1117;

import itcast.zheng.d1115.ListNode;

/**
 * @author xuekg
 * @description
 * @date 2022/11/17 18:06
 **/
public class OddEvenTest {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode listNode = oddEvenList(head);
        System.out.println(listNode);

    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        //奇链表的尾节点
        ListNode oddTail = head;
        //偶链表的头节点
        ListNode even = head.next;
        //偶链表的尾节点
        ListNode evenTail = even;

        while (oddTail.next != null && evenTail != null && evenTail.next != null) {
            oddTail.next = evenTail.next;
            oddTail = oddTail.next;
            evenTail.next = oddTail.next;
            evenTail = evenTail.next;
        }
        oddTail.next = even;
        return head;
    }
}
