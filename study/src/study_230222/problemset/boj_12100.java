package study_230222.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_12100 {
	static int n, answer = 2; // 최솟값은 무조건 2이상
	static int[][] map; // 2048 맵
	static int[] move = new int[5]; // 움직일 방향 설정 배열 (순열)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder sb = new StringBuilder();

		// 값 입력 및 초기화
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(stk.nextToken());
		}

		permutation(0); // 움직임 순열 구함 (위, 아래, 오른쪽, 왼쪽) (0~3)

		sb.append(answer);
		System.out.println(sb);
	}

	static void permutation(int cnt) {
		if (cnt == 5) {
			// 맵 복사
			int[][] copyMap = new int[n][n];
			for (int i = 0; i < n; i++)
				copyMap[i] = Arrays.copyOf(map[i], n);

			answer = Math.max(answer, moveMap(copyMap)); // 최댓값 저장
			return;
		}

		// 순열 구하기
		for (int i = 0; i < 4; i++) {
			move[cnt] = i;
			permutation(cnt + 1);
		}
	}

	// 2048 맵 움직이기
	static int moveMap(int[][] copyMap) {
		// 순열로 구한 움직임에 따라 맵 값 이동 및 갱신
		for (int i = 0; i < move.length; i++) {
			if (move[i] == 0) { // up
				copyMap = moveUp(copyMap);
			} else if (move[i] == 1) { // down
				copyMap = moveDown(copyMap);
			} else if (move[i] == 2) { // right
				copyMap = moveRight(copyMap);
			} else if (move[i] == 3) { // left
				copyMap = moveLeft(copyMap);
			}
		}

		return findMax(copyMap); // 최댓값 구해서 return
	}

	// 위로 움직이기 (나머지 로직 동일)
	static int[][] moveUp(int[][] copyMap) {
		Queue<Integer> queue = new ArrayDeque<>(); // 큐에 순서대로 넣고 뺌

		for (int i = 0; i < n; i++) {
			int idx = 0; // map에 넣을 위치
			for (int j = 0; j < n; j++) {
				int value = copyMap[j][i];
				if (value == 0) // 0이면 넣을 필요 없음 -> skip
					continue;
				if (queue.isEmpty()) { // queue가 비어있으면 value 저장
					queue.offer(value);
				} else {
					if (queue.peek() == value) { // queue의 peek과 값이 같으면 합침
						copyMap[idx++][i] = queue.poll() * 2; // peek*2를 넣음
					} else {
						copyMap[idx++][i] = queue.poll(); // 값이 다르면 peek 저장
						queue.offer(value); // 현재 넣을 값을 queue에 넣음
					}
				}
			}

			// queue가 빌 때까지 map에 저장
			while (!queue.isEmpty()) {
				copyMap[idx++][i] = queue.poll();
			}

			// 나머지는 0으로 갱신
			for (int j = idx; j < n; j++)
				copyMap[j][i] = 0;
		}

		return copyMap;
	}

	// 아래로 움직이기
	static int[][] moveDown(int[][] copyMap) {
		Queue<Integer> queue = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			int idx = n - 1;
			for (int j = n - 1; j >= 0; j--) {
				int value = copyMap[j][i];
				if (value == 0)
					continue;
				if (queue.isEmpty()) {
					queue.offer(value);
				} else {
					if (queue.peek() == value) {
						copyMap[idx--][i] = queue.poll() * 2;
					} else {
						copyMap[idx--][i] = queue.poll();
						queue.offer(value);
					}
				}
			}

			while (!queue.isEmpty()) {
				copyMap[idx--][i] = queue.poll();
			}

			for (int j = idx; j >= 0; j--)
				copyMap[j][i] = 0;
		}

		return copyMap;
	}

	// 오른쪽으로 움직이기
	static int[][] moveRight(int[][] copyMap) {
		Queue<Integer> queue = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			int idx = n - 1;
			for (int j = n - 1; j >= 0; j--) {
				int value = copyMap[i][j];
				if (value == 0)
					continue;
				if (queue.isEmpty()) {
					queue.offer(value);
				} else {
					if (queue.peek() == value) {
						copyMap[i][idx--] = queue.poll() * 2;
					} else {
						copyMap[i][idx--] = queue.poll();
						queue.offer(value);
					}
				}
			}

			while (!queue.isEmpty()) {
				copyMap[i][idx--] = queue.poll();
			}

			for (int j = idx; j >= 0; j--)
				copyMap[i][j] = 0;
		}

		return copyMap;
	}

	// 왼쪽으로 움직이기
	static int[][] moveLeft(int[][] copyMap) {
		Queue<Integer> queue = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			int idx = 0;
			for (int j = 0; j < n; j++) {
				int value = copyMap[i][j];
				if (value == 0)
					continue;
				if (queue.isEmpty()) {
					queue.offer(value);
				} else {
					if (queue.peek() == value) {
						copyMap[i][idx++] = queue.poll() * 2;
					} else {
						copyMap[i][idx++] = queue.poll();
						queue.offer(value);
					}
				}
			}

			while (!queue.isEmpty()) {
				copyMap[i][idx++] = queue.poll();
			}

			for (int j = idx; j < n; j++)
				copyMap[i][j] = 0;
		}

		return copyMap;
	}

	// 최댓값 찾기
	static int findMax(int[][] copyMap) {
		int ans = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				ans = Math.max(ans, copyMap[i][j]);

		return ans;
	}
}
