package com.example.test.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/7/12 10:35
 **/
@Slf4j
public class LectcodeTestList {

    int[] testNums = new int[]{-1, 0, 3, 5, 9, 12};
    int testTarget = 9;

    @Test
    public void test01() {
        log.info("左闭右开顺序是：{}", search01(testNums, testTarget));
    }

    //[)
    private int search01(int[] nums, int target) {
        int left = 0, right = nums.length;
        if (nums.length < 1 || target < nums[0] || target > nums[nums.length -1]) {
            return -1;
        }
        int num = 0;
        while (left < right) {
            num++;
            int mid = left + ((right - left) >> 1);
            log.info("运行次数：{}, {}, {}", num, mid, nums[mid]);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid ;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int search02(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        return -1;
    }


    class MyLinkedList {

        int size = 0;
        ListNode node;

        public MyLinkedList() {
            size = 0;
            node = new ListNode(0);
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode now = node;
            for (;index >= 0; index--) {
                now = now.next;
            }
            return now.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }
            ListNode pre = node;
            for(; index > 0; index--) {
                pre = pre.next;
            }
            ListNode toAdd = new ListNode(val);
            toAdd.next = pre.next;
            pre.next = toAdd;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            ListNode pre = node;
            for(; index > 0; index--) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
        }
    }

    @Test
    public void test02() {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtTail(4);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(2);  //现在链表是1-> 3
        linkedList.get(2);            //返回3
    }


}
