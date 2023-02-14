package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_23309_2 {

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
        int size = 0;

        CircularLinkedList() {
            head = null;
        }

        public void add(Node cur, int num, int stationNum) {
            Node node = new Node(stationNum);

            node.next = cur.next;
            node.back = cur;
            cur.next = node;
            node.next.back = node;
            size++;

            return;
        }

        public void addLast(int num) {
            Node node = new Node(num);

            if (size == 0) {
                head = node;
                node.next = node;
                node.back = node;
                size++;
                return;
            }

            node.next = head;
            node.back = head.back;
            head.back.next = node;
            head.back = node;
            size++;

            return;
        }

        public Node search(int num) {
            Node node = head;

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

        public void remove(Node cur) {
            cur.next.back = cur.back;
            cur.back.next = cur.next;
            cur.next = null;
            cur.back = null;

            size--;

            return;
        }

        public int BN(int num, int stationNum) {
            Node cur = search(num);
            int value = cur.next.data;
            add(cur, num, stationNum);
            return value;
        }

        public int BP(int num, int stationNum) {
            Node cur = search(num);
            int value = cur.back.data;
            add(cur.back, num, stationNum);
            return value;
        }

        public int CN(int num) {
            Node cur = search(num);
            int value = cur.next.data;
            remove(cur.next);
            return value;
        }

        public int CP(int num) {
            Node cur = search(num);
            int value = cur.back.data;
            remove(cur.back);
            return value;
        }
    }
}
