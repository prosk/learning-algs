package com.mycompany.leetcode.backend.structures;

import java.util.HashMap;
import java.util.Map;

class ListNode {
    int key;
    int val;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "key=" + key +
                ", val=" + val +
                '}';
    }
}

class LRUCache {
    int capacity;
    Map<Integer, ListNode> cacheMap;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }

        ListNode node = cacheMap.get(key);
        remove(node);
        add(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (cacheMap.containsKey(key)) {
            ListNode oldNode = cacheMap.get(key);
            remove(oldNode);
        }

        ListNode node = new ListNode(key, value);
        cacheMap.put(key, node);
        add(node);

        if (cacheMap.size() > capacity) {
            ListNode nodeToDelete = head.next;
            remove(nodeToDelete);
            cacheMap.remove(nodeToDelete.key);
        }
        print();
    }

    public void add(ListNode node) {
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    public void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void print() {
        cacheMap.forEach((k, v) -> System.out.print("k = " + k + " v = " + v + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
