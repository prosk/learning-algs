package com.mycompany.leetcode.bytopic.trees.binary;

public class BinaryTreeMaxPathSum {

    public static void main(String[] args) {

    }

    // editorial solution with only one DFS
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        gainFromSubtree(root);
        return maxSum;
    }

    private int maxSum;

    // post order traversal of subtree rooted at `root`
    private int gainFromSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // add the path sum from left subtree. Note that if the path
        // sum is negative, we can ignore it, or count it as 0.
        // This is the reason we use `Math.max` here.
        int gainFromLeft = Math.max(gainFromSubtree(root.left), 0);

        // add the path sum from right subtree. 0 if negative
        int gainFromRight = Math.max(gainFromSubtree(root.right), 0);

        // if left or right path sum are negative, they are counted
        // as 0, so this statement takes care of all four scenarios
        maxSum = Math.max(maxSum, gainFromLeft + gainFromRight + root.val);

        // return the max sum for a path starting at the root of subtree
        return Math.max(gainFromLeft + root.val, gainFromRight + root.val);
    }

    // my right but complex solution with second class and two dfs
    // in out dp
    static int MINUS_INF = -1_000_000_000;
    public int ans = MINUS_INF;
    public int getMaxPathSum(TreeNode root) {
        TreeNodeStat statRoot = copyTree(root);
        dfsFromLeaves(statRoot);
        dfsFromRoot(statRoot, null, false);
        return ans;
    }

    private void dfsFromRoot(TreeNodeStat node, TreeNodeStat parent, boolean leftDir) {
        int upperSum = MINUS_INF;
        if (parent == null) {
            node.maxWithoutLeft = node.maxRightSum;
            node.maxWithoutRight = node.maxLeftSum;
        } else {
            upperSum = leftDir ? parent.maxWithoutLeft + node.val
                : parent.maxWithoutRight + node.val;
            node.maxWithoutLeft = Math.max(Math.max(node.maxRightSum, upperSum), node.val);
            node.maxWithoutRight = Math.max(Math.max(node.maxLeftSum, upperSum), node.val);
        }
        int nodeOptSum = Math.max(Math.max(node.maxSum, upperSum), node.val);
        ans = Math.max(ans, nodeOptSum);
        if (node.left != null) {
            dfsFromRoot(node.left, node, true);
        }
        if (node.right != null) {
            dfsFromRoot(node.right, node, false);
        }
    }

    private void dfsFromLeaves(TreeNodeStat node) {
        int leftSum = node.val, rightSum = node.val;
        if (node.left != null) {
            dfsFromLeaves(node.left);
            leftSum = Math.max(node.left.maxSum + node.val, node.val);
        }
        if (node.right != null) {
            dfsFromLeaves(node.right);
            rightSum = Math.max(node.right.maxSum + node.val, node.val);
        }
        node.maxSum = Math.max(leftSum, rightSum);
        node.maxLeftSum = leftSum;
        node.maxRightSum = rightSum;
    }

    private TreeNodeStat copyTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new TreeNodeStat(root.val, null, null);
        }
        TreeNodeStat leftSubTree = null, rightSubTree = null;
        if (root.left != null) {
            leftSubTree = copyTree(root.left);
        }
        if (root.right != null) {
            rightSubTree = copyTree(root.right);
        }
        return new TreeNodeStat(root.val, leftSubTree, rightSubTree);
    }

    public static class TreeNodeStat {
        int val;
        TreeNodeStat left;
        TreeNodeStat right;
        int maxLeftSum;
        int maxRightSum;
        int maxSum;
        int maxWithoutLeft;
        int maxWithoutRight;

        TreeNodeStat() {}
        TreeNodeStat(int val) { this.val = val; }
        TreeNodeStat(int val, TreeNodeStat left, TreeNodeStat right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class TreeNode {
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

}
