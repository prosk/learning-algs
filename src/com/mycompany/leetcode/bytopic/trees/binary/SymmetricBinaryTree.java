package com.mycompany.leetcode.bytopic.trees.binary;

import java.util.Deque;
import java.util.LinkedList;

// 101
// https://leetcode.com/problems/symmetric-tree/description/?source=submission-ac
public class SymmetricBinaryTree {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    // my recursive solution (better than editorial recursive solution)
    public boolean isSymmetric(TreeNode root) {
        return isPairSymmetric(root.left, root.right);
    }

    private boolean isPairSymmetric(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        }
        if (r1 != null && r2 != null) {
            return r1.val == r2.val &&
                isPairSymmetric(r1.left, r2.right) &&
                isPairSymmetric(r1.right, r2.left);
        }
        return false;
    }

    // modified editorial non recursive solution
    // ArrayDeque не подходит для очереди так как он запрещает null элементы
    // поэтому вместо него LinkedList
    public boolean isSymmetricNonRecursive(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root.left);
        q.addLast(root.right);
        while (!q.isEmpty()) {
            TreeNode r1 = q.pollFirst();
            TreeNode r2 = q.pollFirst();
            if (r1 == null && r2 == null) continue;
            if (r1 == null || r2 == null) return false;
            if (r1.val != r2.val) return false;
            q.addLast(r1.left);
            q.addLast(r2.right);
            q.addLast(r1.right);
            q.addLast(r2.left);
        }
        return true;
    }



}
