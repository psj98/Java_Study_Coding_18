package study_230201.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class alg_1991 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        Tree tree = new Tree();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            String head = stk.nextToken();
            String left = stk.nextToken();
            String right = stk.nextToken();

            tree.insert(head, left, right);
        }

        tree.preorder(tree.root);
        System.out.println();
        tree.inorder(tree.root);
        System.out.println();
        tree.postorder(tree.root);
    }
}

class Node {
    String data;
    Node left;
    Node right;

    Node(String data) {
        this.data = data;
    }
}

class Tree {
    Node root;

    Tree() {
        root = null;
    }

    void insert(String data, String left, String right) {
        if (root == null) {
            root = new Node(data);

            if (!left.equals("."))
                root.left = new Node(left);
            if (!right.equals("."))
                root.right = new Node(right);
        } else {
            search(root, data, left, right);
        }
    }

    void search(Node root, String data, String left, String right) {
        if (root == null)
            return;
        else if (root.data.equals(data)) {
            if (!left.equals("."))
                root.left = new Node(left);
            if (!right.equals("."))
                root.right = new Node(right);
        } else {
            search(root.left, data, left, right);
            search(root.right, data, left, right);
        }
    }

    void preorder(Node root) {
        System.out.print(root.data);
        if (root.left != null)
            preorder(root.left);
        if (root.right != null)
            preorder(root.right);
    }

    void inorder(Node root) {
        if (root.left != null)
            inorder(root.left);
        System.out.print(root.data);
        if (root.right != null)
            inorder(root.right);
    }

    void postorder(Node root) {
        if (root.left != null)
            postorder(root.left);
        if (root.right != null)
            postorder(root.right);
        System.out.print(root.data);
    }
}