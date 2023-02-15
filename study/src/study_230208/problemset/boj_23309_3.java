package practice.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_23309_3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Node node = new Node();

		int n = Integer.parseInt(stk.nextToken()); // 역 개수 (1<= n <= 500000)
		int m = Integer.parseInt(stk.nextToken()); // 공사 횟수 (1<= m <= 1500000)

		// 공사하기 전에 있는 역
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			node.addLast(Integer.parseInt(stk.nextToken()));
		}

		for (int i = 0; i < m; i++) {
			stk = new StringTokenizer(br.readLine());
			
			String op = stk.nextToken(); // 명령어 (BN, BP, CN, CP)
			int num = Integer.parseInt(stk.nextToken()); // 역 고유 번호
			int stationNum = -1; // 설립할 역 고유 번호
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

	// 노드 -> 고유 번호이기 때문에 배열의 인덱스가 겹칠 일이 없음
	// 링크드리스트 구현하면 시간 초과
	public static class Node {
		// 모든 역의 고유 번호 (1~1000000) -> 배열의 인덱스가 현재 역 번호
		int[] next = new int[1000001]; // 해당 역의 다음 역 번호
		int[] back = new int[1000001]; // 해당 역의 이전 역 번호
		int head = 0; // 시작점

		// 처음에 역의 고유 번호를 넣을 때 사용
		public void addLast(int num) {
			// 시작점이 없는 경우, 시작점에 값 추가
			if (head == 0) {
				head = num;
				next[num] = num;
				back[num] = num;
				return;
			}

			// 시작점이 있는 경우, n1(head) - n2 - n3 - n1(head) || n3과 n1(head) 사이에 넣기
			next[num] = head;
			back[num] = back[head];
			back[head] = num;
			next[back[num]] = num;
		}

		// 역 설립 -> 현재 역(cur)과 다음 역(next[cur]) 사이에 stationNum 설립
		public void add(int cur, int stationNum) {
			back[stationNum] = cur;
			next[stationNum] = next[cur];
			back[next[cur]] = stationNum;
			next[cur] = stationNum;
		}

		// 역 폐쇄 -> 현재 역(cur) 폐쇄 -> 현재 역 기준 양쪽의 역을 연결 시키고 0으로 저장하여 폐쇄
		public void delete(int cur) {
			next[back[cur]] = next[cur];
			back[next[cur]] = back[cur];
			next[cur] = 0;
			back[cur] = 0;
		}
	}
}
