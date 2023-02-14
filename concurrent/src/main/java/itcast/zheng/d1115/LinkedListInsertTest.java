package itcast.zheng.d1115;

/**
 * @author xuekg
 * @description 对链表进行插入排序
 * @date 2022/12/14 17:10
 **/
public class LinkedListInsertTest {

    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        //虚拟头节点
        ListNode dummy = new ListNode(Integer.MIN_VALUE, null);
        //遍历节点
        ListNode p = head;
        while (p != null) {
            ListNode tmp = p.next;

            //寻找p节点插入的位置，插入到哪个节点后面
            ListNode q = dummy;
            while (q.next != null && q.next.val <= p.val) {
                q = q.next;
            }
            //将p节点插入
            p.next = q.next;
            q.next = p;

            p = tmp;
        }
        return dummy.next;
    }

}
