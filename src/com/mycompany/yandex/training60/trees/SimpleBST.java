package com.mycompany.yandex.training60.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SimpleBST {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new SimpleBST().run();
        out.close();
    }

    void run() {
        Node root = null;
        String query = readLine();

        while(query != null) {
            String code = query.substring(0, 3);
            if (code.equals("ADD")) {
                int val = Integer.parseInt(query.substring(4));
                Node addedNode = addNodeToBst(root, val);
                if (root == null) {
                    root = addedNode;
                }
            } else if (code.equals("SEA")) {
                int val = Integer.parseInt(query.substring(7));
                search(root, val);
            } else {
                // print
                printInOrder(root);
            }
            query = readLine();
        }
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
            Node node = new Node(val, 0);
            out.println("DONE");
            return node;
        }
        if (root.val == val) {
            // Node already exists, nothing to do
            out.println("ALREADY");
            return root;
        }
        if (root.val > val) {
            // go to left
            if (root.left == null) {
                // add node as a left child
                Node node = new Node(val, root.height+1);
                root.left = node;
                out.println("DONE");
                return node;
            }
            return addNodeToBst(root.left, val);
        } else {
            // go to right
            if (root.right == null) {
                // add node as a right child
                Node node = new Node(val, root.height+1);
                root.right = node;
                out.println("DONE");
                return node;
            }
            return addNodeToBst(root.right, val);
        }
    }

    void printInOrder(Node root) {
        if (root == null) return;
        printInOrder(root.left);
        String dots = ".".repeat(root.height);
        out.println(dots + root.val);
        printInOrder(root.right);
    }

    void search(Node root, int val) {
        String ans = "NO";
        Node curr = root;
        while(curr != null) {
            if (curr.val == val) {
                ans = "YES";
                break;
            }
            if (curr.val > val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        out.println(ans);
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