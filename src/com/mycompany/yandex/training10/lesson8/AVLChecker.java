package com.mycompany.yandex.training10.lesson8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AVLChecker {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    boolean isAVL = true;

    /*
     Более простое решение
     Сначала просто для каждого узла дерева считаем глубину левого и правого поддеревьев
     А потом проходимся еще раз по всем узлам и делаем проверку

     def is_avl_tree(keys):
        bst = BSTNode()
        for key in keys:
            bst.insert_recursive(key)

        is_avl = True

        for node in bst:
            is_avl = is_avl and (abs(node.left_subtree_depth - node.right_subtree_depth) <= 1)

        return 'YES' if is_avl else 'NO'

    Ссылка на такое решение
    https://github.com/Yankovsky/yandex-algos-training/blob/master/hw8/h.py

     */

    public static void main(String[] args) {
        new AVLChecker().run();
        out.close();
    }

    void run() {
        int curr = readInt();
        Node root = null;
        while(curr != 0) {
            Node addedNode = addNodeToBst(root, curr);
            if (root == null) {
                root = addedNode;
            }
            curr = readInt();
        }
        // traverse in order
        HeightPair hp = getHeightPair(root);
        isAVL = isAVL && Math.abs(hp.left - hp.right) <= 1;
        out.println(isAVL ? "YES" : "NO");
    }

    int readInt() {
        return Integer.parseInt(readString());
    }

    String readString() {
        while (!tok.hasMoreTokens()) {
            String nextLine = readLine();
            if (null == nextLine) return null;
            tok = new StringTokenizer(nextLine);
        }
        return tok.nextToken();
    }

    String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Node addNodeToBst(Node root, int val) {
        if (root == null) {
            // adding root
            Node node = new Node(val, 1);
            return node;
        }
        if (root.val == val) {
            // Node already exists, nothing to do
            return root;
        }
        if (root.val > val) {
            // go to left
            if (root.left == null) {
                // add node as a left child
                Node node = new Node(val, root.height+1);
                root.left = node;
                return node;
            }
            return addNodeToBst(root.left, val);
        } else {
            // go to right
            if (root.right == null) {
                // add node as a right child
                Node node = new Node(val, root.height+1);
                root.right = node;
                return node;
            }
            return addNodeToBst(root.right, val);
        }
    }

    HeightPair getHeightPair(Node root) {
        if (root == null) {
            return new HeightPair(-1, -1);
        }
        HeightPair hpLeft = getHeightPair(root.left);
        HeightPair hpRight = getHeightPair(root.right);
        int leftH = Math.max(hpLeft.left, hpLeft.right) + 1;
        int rightH = Math.max(hpRight.left, hpRight.right) + 1;
        isAVL = isAVL && Math.abs(leftH - rightH) <= 1;
        return new HeightPair(leftH, rightH);
    }

    public static class Node {
        int val;
        int height;
        Node left;
        Node right;

        public Node(int val, int height) {
            this.val = val;
            this.height = height;
        }
    }

    public static class HeightPair {
        int left;
        int right;

        public HeightPair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}