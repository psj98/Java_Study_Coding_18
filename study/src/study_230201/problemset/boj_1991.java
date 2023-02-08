package study_230201.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class boj_1991 {
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

            tree.insert(head, left, right); // 트리에 노드 삽입
        }

        tree.preorder(tree.root); // 전위 순회
        System.out.println();
        tree.inorder(tree.root); // 중위 순회
        System.out.println();
        tree.postorder(tree.root); // 후위 순회
    }
}

// 노드 (head data, left node, right node)
class Node {
    String data;
    Node left;
    Node right;

    Node(String data) {
        this.data = data;
    }
}

// 트리
class Tree {
    Node root; // 최상위 노드

    // 초기화
    Tree() {
        root = null;
    }

    // 노드 삽입
    void insert(String data, String left, String right) {
        // 최상위 노드인 경우
        if (root == null) {
            root = new Node(data);

            // "." 이 아니면 노드 생성 (해당 노드에 child node 추가)
            if (!left.equals("."))
                root.left = new Node(left);
            if (!right.equals("."))
                root.right = new Node(right);
        } else {
            search(root, data, left, right); // 노드 탐색 (data와 같은 값을 가지는 노드)
        }
    }

    // 노드 탐색
    void search(Node root, String data, String left, String right) {
        if (root == null) // 노드가 없으면 돌아가기
            return;
        else if (root.data.equals(data)) { // 노드의 data와 같으면
            // "." 이 아니면 노드 생성 (해당 노드에 child node 추가)
            if (!left.equals("."))
                root.left = new Node(left);
            if (!right.equals("."))
                root.right = new Node(right);
        } else {
            // data와 같은 값을 못찾은 경우, 재귀 탐색
            search(root.left, data, left, right);
            search(root.right, data, left, right);
        }
    }

    // 전위 순회 (root -> left -> right)
    void preorder(Node root) {
        System.out.print(root.data);
        if (root.left != null)
            preorder(root.left);
        if (root.right != null)
            preorder(root.right);
    }

    // 중위 순회 (left -> root -> right)
    void inorder(Node root) {
        if (root.left != null)
            inorder(root.left);
        System.out.print(root.data);
        if (root.right != null)
            inorder(root.right);
    }

    // 후위 순회 (left -> right -> root)
    void postorder(Node root) {
        if (root.left != null)
            postorder(root.left);
        if (root.right != null)
            postorder(root.right);
        System.out.print(root.data);
    }
}
