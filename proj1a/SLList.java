public class SLList {

    // SLList 的头节点
    private IntNode head;
    private int size = 0;

    public SLList() {
        head = null;
        size = 0;
    }

    // 创建一个list，把传入的i作为节点&头节点
    public SLList(int i) {
        head = new IntNode(i, null);
        size = 1;
    }

    // Int链表的节点
    static class IntNode {
        int data;
        IntNode next;

        public IntNode(int d, IntNode n) {
            this.data = d;
            this.next = n;
        }
    }

    public IntNode getHead() {
        return head;
    }

    public int getFirst() {
        return head.data;
    }

    public void addFirst(int i) {
        head = new IntNode(i, head);
        size += 1;
    }

    public void addLast(int i) {
        IntNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new IntNode(i, null);
        size += 1;
    }

    // 迭代写法
//    int size() {
//        int size = 0;
//        IntNode curr = head;
//        while (curr.next != null) {
//            size += 1;
//            curr = curr.next;
//        }
//        return size;
//    }

    // 递归写法（+overload）
//    private int size(IntNode n) {
//        if (n.next == null) {
//            return 1;
//        }
//        return 1 + size(n.next);
//    }
//
//    public int size() {
//        return size(head);
//    }
    public int size() {
        return size;
    }
}
