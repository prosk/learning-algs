package com.mycompany.leetcode.backend.structures;

public class ConstructQuadTree {

    // --------------------------------------------------------------------------
    // my recursive solution
    // --------------------------------------------------------------------------
    private static class QuadNode {
        public int res;
        public Node node;

        public QuadNode(int res, Node node) {
            this.res = res;
            this.node = node;
        }
    }

    public Node construct(int[][] grid) {
        if (grid.length == 1) {
            return new Node(grid[0][0] == 1, true);
        }
        QuadNode quadNode = getQuadType(grid, 0, 0, grid.length);
        if (quadNode.node == null) {
            // all elems are the same
            return new Node(grid[0][0] == 1, true);
        }
        return quadNode.node;
    }

    private QuadNode getQuadType(int[][] grid, int startRow, int startCol, int size) {
        if (size == 1) {
            return new QuadNode(grid[startRow][startCol], null);
        }
        // devide to 4 areas
        int areaSize = size/2;
        QuadNode topLeft = getQuadType(grid, startRow, startCol, areaSize);
        QuadNode topRight = getQuadType(grid, startRow, startCol+areaSize, areaSize);
        QuadNode bottomLeft = getQuadType(grid, startRow+areaSize, startCol, areaSize);
        QuadNode bottomRight = getQuadType(grid, startRow+areaSize, startCol+areaSize, areaSize);
        if (topLeft.res == topRight.res
            && topRight.res == bottomLeft.res
            && bottomLeft.res == bottomRight.res
            && topLeft.res != -1) {
            // all is 1 or all is 0
            return new QuadNode(topLeft.res, null);
        }
        // adding one parent with 4 child nodes
        Node parent = new Node(true, false);
        QuadNode parentQuad = new QuadNode(-1, parent);
        parent.topLeft = (topLeft.node == null) ? new Node(topLeft.res == 1, true) : topLeft.node;
        parent.topRight = (topRight.node == null) ? new Node(topRight.res == 1, true) : topRight.node;
        parent.bottomLeft = (bottomLeft.node == null) ? new Node(bottomLeft.res == 1, true) : bottomLeft.node;
        parent.bottomRight = (bottomRight.node == null) ? new Node(bottomRight.res == 1, true) : bottomRight.node;
        return parentQuad;
    }


    // class Node from leetcode
    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    // --------------------------------------------------------------------------
    // editorial brute force recursive solution with time complexity O(N*N*logN)
    // --------------------------------------------------------------------------

    // Returns true if all the values in the matrix are the same; otherwise, false.
    boolean sameValue(int[][] grid, int x1, int y1, int length) {
        for (int i = x1; i < x1 + length; i++) {
            for (int j = y1; j < y1 + length; j++)
                if (grid[i][j] != grid[x1][y1])
                    return false;
        }
        return true;
    }

    Node solveEdBF(int[][] grid, int x1, int y1, int length) {
        // Return a leaf node if all values are the same.
        if (sameValue(grid, x1, y1, length)) {
            return new Node(grid[x1][y1] == 1, true);
        } else {
            Node root = new Node(false, false);

            // Recursive call for the four sub-matrices.
            root.topLeft = solveEdBF(grid, x1, y1, length / 2);
            root.topRight = solveEdBF(grid, x1, y1 + length / 2, length / 2);
            root.bottomLeft = solveEdBF(grid, x1 + length / 2, y1, length / 2);
            root.bottomRight = solveEdBF(grid, x1 + length / 2, y1 + length / 2, length / 2);

            return root;
        }
    }

    public Node constructEdBF(int[][] grid) {
        return solveEdBF(grid, 0, 0, grid.length);
    }

    // --------------------------------------------------------------------------
    // editorial optimal recursive solution with time complexity O(N*N)
    // --------------------------------------------------------------------------

    private Node solve(int[][] grid, int x1, int y1, int length) {
        // Return a leaf node if the matrix size is one.
        if (length == 1) {
            return new Node(grid[x1][y1] == 1, true);
        }

        // Recursive calls to the four sub-matrices.
        Node topLeft = solve(grid, x1, y1, length / 2);
        Node topRight = solve(grid, x1, y1 + length / 2, length / 2);
        Node bottomLeft = solve(grid, x1 + length / 2, y1, length / 2);
        Node bottomRight = solve(grid, x1 + length / 2, y1 + length / 2, length / 2);

        // If the four returned nodes are leaf and have the same values
        // Return a leaf node with the same value.
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
            && topLeft.val == topRight.val && topRight.val == bottomLeft.val
            && bottomLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true);
        }

        // If the four nodes aren't identical, return a non-leaf node with corresponding child pointers.
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    public Node constructEdOpt(int[][] grid) {
        return solve(grid, 0, 0, grid.length);
    }

}
