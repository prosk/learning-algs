package com.mycompany.yandex.training10.lesson8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TreeHeight {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new TreeHeight().run();
        out.close();
    }

    void run() {
        int treeHeight = 0;
        int curr = readInt();
        Node root = null;
        while(curr != 0) {
            Node addedNode = addNodeToBst(root, curr);
            if (root == null) {
                root = addedNode;
            }
            treeHeight = Math.max(treeHeight, addedNode.height);
            curr = readInt();
        }
        out.println(treeHeight);
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