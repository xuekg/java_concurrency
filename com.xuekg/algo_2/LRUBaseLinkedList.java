package algo_2;

import java.util.Scanner;

/**
 * @author xuekg
 * @description 基于单链表的LRU算法
 * @date 2021/5/6 10:04
 **/
public class LRUBaseLinkedList<T> {

  // 默认链表容量
  private final static Integer DEFAULT_CAPACITY = 10;

  // 头结点
  private SNode<T> headNode;

  // 链表长度
  private Integer length;

  // 链表容量
  private Integer capacity;

  public LRUBaseLinkedList() {
    this.headNode = new SNode<>();
    this.capacity = DEFAULT_CAPACITY;
    this.length = 0;
  }

  public LRUBaseLinkedList(Integer capacity) {
    this.headNode = new SNode<>();
    this.capacity = capacity;
    this.length = 0;
  }

  public void add(T data) {
    SNode preNode = findPreNode(data);
    // 链表中存在，删除原数据，再插入到链表头部
    if (preNode != null) {
      deleteEleOpt(preNode);
      insertEleBegin(data);
    } else {
      if (length > this.capacity) {
        // 删除尾节点
        deleteEleEnd();
      }
      insertEleBegin(data);
    }
  }

  private void printAll() {
    SNode node = headNode.getNext();
    while (node != null) {
      System.out.print(node.getElement() + ",");
      node = node.getNext();
    }
    System.out.println();
  }

  /**
   * 删除preNode的下一个元素
   *
   * @param preNode
   */
  private void deleteEleOpt(SNode preNode) {
    SNode temp = preNode.getNext();
    preNode.setNext(temp.getNext());
    temp = null;
    length--;
  }

  /**
   * 链表头部插入元素
   *
   * @param data
   */
  private void insertEleBegin(T data) {
    SNode next = headNode.getNext();
    headNode.setNext(new SNode(data, next));
    length++;
  }

  /**
   * 删除尾节点
   */
  private void deleteEleEnd() {
    SNode ptr = headNode;
    // 空链表直接返回
    if (ptr.getNext() == null) {
      return;
    }
    // 倒数第二个节点
    while (ptr.getNext().getNext() != null) {
      ptr = ptr.getNext();
    }
    SNode temp = ptr.getNext();
    ptr.setNext(null);
    temp = null;
    length--;
  }

  /**
   * 获取前一个节点
   *
   * @param data
   * @return
   */
  private SNode findPreNode(T data) {
    SNode node = headNode;
    while (node.getNext() != null) {
      if (data.equals(node.getNext().getElement())) {
        return node;
      }
      node = node.getNext();
    }
    return null;

  }

  public class SNode<T> {
    private T element;
    private SNode next;

    public SNode(T element) {
      this.element = element;
    }

    public SNode(T element, SNode next) {
      this.element = element;
      this.next = next;
    }

    public SNode() {
      this.next = null;
    }

    public T getElement() {
      return element;
    }

    public void setElement(T element) {
      this.element = element;
    }

    public SNode getNext() {
      return next;
    }

    public void setNext(SNode next) {
      this.next = next;
    }
  }

  public static void main(String[] args) {
    LRUBaseLinkedList list = new LRUBaseLinkedList();
    Scanner sc = new Scanner(System.in);
    while (true) {
      list.add(sc.nextInt());
      list.printAll();
    }
  }

}
