package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_23309_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        CircularLinkedList link = new CircularLinkedList();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            link.addLast(Integer.parseInt(stk.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            String op = stk.nextToken();
            int num = Integer.parseInt(stk.nextToken());
            int stationNum = -1;
            if (stk.hasMoreTokens())
                stationNum = Integer.parseInt(stk.nextToken());

            if (op.equals("BN")) {
                sb.append(link.BN(num, stationNum)).append("\n");
            } else if (op.equals("BP")) {
                sb.append(link.BP(num, stationNum)).append("\n");
            } else if (op.equals("CN")) {
                sb.append(link.CN(num)).append("\n");
            } else if (op.equals("CP")) {
                sb.append(link.CP(num)).append("\n");
            }
        }

        System.out.println(sb);
    }

    // 노드
    public static class Node {
        Node next;
        Node back;
        int data;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.back = null;
        }
    }

    // 원형 연결 리스트 (양방향으로)
    public static class CircularLinkedList {
        Node head = new Node(-1);
        Node tail = new Node(-1);
        int size = 0;

        CircularLinkedList() {
            head.next = tail;
            tail.next = head;
            head.back = tail;
            tail.back = head;
        }

        public void addFirst(int num) {
            Node node = new Node(num);

            node.next = head.next;
            node.back = head;
            head.next = node;
            head.next.back = node;
            size++;

            return;
        }

        public void addLast(int num) {
            Node node = new Node(num);

            node.next = tail;
            node.back = tail.back;
            tail.back.next = node;
            tail.back = node;
            size++;

            return;
        }

        public int addMiddle(Node cur, int num, int stationNum) {
            int value = 0;
            Node node = new Node(stationNum);

            if (cur.next.equals(tail)) {
                value = head.next.data;
                addLast(stationNum);
            } else if (cur.equals(head)) {
                value = tail.back.data;
                addFirst(stationNum);
            } else {
                value = cur.data;
                node.next = cur.next;
                node.back = cur;
                cur.next = node;
                node.next.back = node;
                size++;
            }

            return value;
        }

        public Node search(int num) {
            Node node = head.next;

            // 값이 같은 노드 찾기
            for (int i = 0; i < size; i++) {
                if (node.data == num) {
                    return node;
                } else {
                    node = node.next;
                }
            }

            return null;
        }

        public void removeFirst(Node cur) {
            cur.next.back = head;
            head.next = cur.next;
            cur.next = null;
            cur.back = null;

            return;
        }

        public void removeLast(Node cur) {
            cur.back.next = tail;
            tail.back = cur.back;
            cur.next = null;
            cur.back = null;

            return;
        }

        public int remove(Node cur) {
            int value = 0;

            if (cur.equals(tail)) {
                value = head.next.data;
                removeFirst(head.next);
            } else if (cur.equals(head)) {
                value = tail.back.data;
                removeLast(tail.back);
            } else {
                value = cur.data;
                cur.next.back = cur.back;
                cur.back.next = cur.next;
                cur.next = null;
                cur.back = null;
            }

            size--;

            return value;
        }

        public int BN(int num, int stationNum) {
            Node cur = search(num);
            return addMiddle(cur, num, stationNum);
        }

        public int BP(int num, int stationNum) {
            Node cur = search(num);
            return addMiddle(cur.back, num, stationNum);
        }

        public int CN(int num) {
            Node cur = search(num);
            return remove(cur.next);
        }

        public int CP(int num) {
            Node cur = search(num);
            return remove(cur.back);
        }
    }
}
