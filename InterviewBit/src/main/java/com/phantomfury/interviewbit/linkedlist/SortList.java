package com.phantomfury.interviewbit.linkedlist;

class ListNode
{
    int val;
    ListNode next;
    public ListNode(int val)
    {
        this.val = val;
        this.next = null;
    }
}

public class SortList
{
    public void printList(ListNode node)
    {
        while(node != null)
        {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println();
    }

    // Merge Sort Linked List
    public ListNode sortList(ListNode A)
    {
        if(A == null || A.next == null)
            return A;

        ListNode slow = A;
        ListNode fast = A.next;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode list1Head = A;
        ListNode list2Head = slow.next;
        slow.next = null;

        return mergeList(sortList(list1Head), sortList(list2Head));
    }

    // Function to merge two sorted linked lists
    public ListNode mergeList(ListNode l1, ListNode l2)
    {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(l1 != null && l2 != null)
        {
            if(l1.val < l2.val)
            {
                tail.next = l1;
                l1 = l1.next;
                tail = tail.next;
                tail.next = null;
            }
            else
            {
                tail.next = l2;
                l2 = l2.next;
                tail = tail.next;
                tail.next = null;
            }
        }

        if(l1 != null)
            tail.next = l1;
        else if(l2 != null)
            tail.next = l2;

        return head.next;
    }

    public static void main(String[] args) {
        SortList obj = new SortList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(8);
        head.next.next.next = new ListNode(6);
        head.next.next.next.next = new ListNode(4);
        System.out.println("List Before Sorting");
        obj.printList(head);

        ListNode sortedHead = obj.sortList(head);

        System.out.println("List After Sorting");
        obj.printList(sortedHead);
    }
}
