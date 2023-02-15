package practice.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_23309 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Node node = new Node();

		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());

		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			node.addLast(Integer.parseInt(stk.nextToken()));
		}

		for (int i = 0; i < m; i++) {
			stk = new StringTokenizer(br.readLine());
			String op = stk.nextToken();
			int num = Integer.parseInt(stk.nextToken());
			int stationNum = -1;
			if (stk.hasMoreTokens())
				stationNum = Integer.parseInt(stk.nextToken());

			if (op.equals("BN")) {
				sb.append(node.next[num]).append("\n");
				node.add(num, stationNum);
			} else if (op.equals("BP")) {
				sb.append(node.back[num]).append("\n");
				node.add(node.back[num], stationNum);
			} else if (op.equals("CN")) {
				sb.append(node.next[num]).append("\n");
				node.delete(node.next[num]);
			} else if (op.equals("CP")) {
				sb.append(node.back[num]).append("\n");
				node.delete(node.back[num]);
			}
		}

		System.out.println(sb);
	}

	// 노드
	public static class Node {
		int head = 0;
		int[] next = new int[1000001];
		int[] back = new int[1000001];

		Node() {
		}

		public void addLast(int num) {
			if (head == 0) {
				head = num;
				next[num] = num;
				back[num] = num;
				return;
			}

			next[num] = head;
			back[num] = back[head];
			back[head] = num;
			next[back[num]] = num;
		}

		public void add(int cur, int stationNum) {
			back[stationNum] = cur;
			next[stationNum] = next[cur];
			back[next[cur]] = stationNum;
			next[cur] = stationNum;
		}

		public void delete(int cur) {
			next[back[cur]] = next[cur];
			back[next[cur]] = back[cur];
			next[cur] = 0;
			back[cur] = 0;
		}
	}
}