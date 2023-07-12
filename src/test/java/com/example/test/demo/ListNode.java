package com.example.test.demo;

import lombok.Data;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/7/20 15:27
 **/
@Data
public class ListNode {
    public ListNode next;
    public int val;

    public ListNode(int val) {
        this.val = val;
    }
}
