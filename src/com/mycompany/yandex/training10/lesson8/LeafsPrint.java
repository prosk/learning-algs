package com.mycompany.yandex.training10.lesson8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LeafsPrint {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new LeafsPrint().run();
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
        List<Integer> ans = new ArrayList<>();
        traverseInOrder(root, ans);
        // output
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ans.size(); i++) {
            if (i > 0) sb.append('\n');
            sb.append(ans.get(i));
        }
        out.println(sb);
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

    void traverseInOrder(Node root, List<Integer> elems) {
        if (root == null) {
            return;
        }
        traverseInOrder(root.left, elems);
        if (root.left == null && root.right == null) {
            elems.add(root.val);
        }
        traverseInOrder(root.right, elems);
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
}