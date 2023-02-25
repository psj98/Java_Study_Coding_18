package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16236 {
	static int n, sharkX, sharkY, babySize = 2, fishCnt = 0, time = 0; // 맵 크기, 상어 위치, 상어 크기, 먹은 물고기 개수, 시간
	static int[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean meet = false; // 아기 상어가 물고기가 있는데 물고기를 먹지 못할 경우 체크
	static ArrayList<Fish>[] fish; // 물고기 크기 별 위치

	// 물고기 위치
	static class Fish {
		int x;
		int y;

		public Fish(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		fish = new ArrayList[7]; // 초기화 (물고기 크기 1 ~ 6)
		for (int i = 0; i < fish.length; i++)
			fish[i] = new ArrayList<>();

		// 맵 정보 저장
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 6) // 물고기 크기에 따른 위치 저장
					fish[map[i][j]].add(new Fish(i, j));
				if (map[i][j] == 9) { // 상어 초기 위치 저장
					sharkX = i;
					sharkY = j;
				}
			}
		}

		// 물고기의 개수가 0이면 탐색 X -> 0 출력
		if (cntFish(7) == 0) {
			System.out.println(0);
			System.exit(0);
		}

		// 아기 상어가 먹을 수 있는 물고기가 있으면 && 물고기를 먹을 수 있으면
		while (cntFish(babySize) > 0 && !meet) {
			eatFish(); // 물고기 먹기
		}

		sb.append(time);
		System.out.println(sb);
	}

	// 물고기 먹기
	static void eatFish() {
		int x = 0, y = 0; // 상어를 옮길 좌표
		int size = 0, fishIdx = 0; // 물고기 크기, 물고기 크기 List의 idx
		int minDist = Integer.MAX_VALUE; // 최소 거리
		int minMove = 0; // 상어와 물고기까지의 거리

		// 아기 상어 크기보다 작은 경우
		for (int i = 1; i < babySize; i++) {
			for (int j = 0; j < fish[i].size(); j++) {
				// 물고기의 위치
				int fishX = fish[i].get(j).x;
				int fishY = fish[i].get(j).y;

				minMove = move(fishX, fishY, sharkX, sharkY); // 상어와 물고기까지의 거리 (BFS)
				if (minMove < minDist) { // 거리가 작은 경우, 갱신
					minDist = minMove;
					x = fishX;
					y = fishY;
					size = i;
					fishIdx = j;
				} else if (minMove == minDist) { // 거리가 같은 경우
					if (x == fishX) { // x좌표(위)가 같을 경우
						if (y > fishY) { // y좌표가 작은 것으로 갱신 (가장 왼쪽)
							x = fishX;
							y = fishY;
							size = i;
							fishIdx = j;
						}
					} else if (x > fishX) { // x좌표(위)가 다른 경우, x좌표가 작은 것으로 갱신 (가장 위)
						x = fishX;
						y = fishY;
						size = i;
						fishIdx = j;
					}
				}
			}
		}

		// 물고기를 먹지 못한 경우, 갱신하지 않고 탈출
		if (minDist == Integer.MAX_VALUE) {
			meet = true;
			return;
		}

		time += minDist; // 시간
		fishCnt++; // 먹은 물고기 개수 증가

		map[sharkX][sharkY] = 0; // 상어 위치 옮기기
		map[x][y] = 9;
		sharkX = x;
		sharkY = y;
		fish[size].remove(fishIdx); // 물고기 없애기

		// 상어 크기와 먹은 물고기 개수가 같은 경우, 상어 크기 +1
		if (babySize == fishCnt) {
			babySize++;
			if (babySize > 6) // 상어가 6보다 커지는 경우, 7로 조정
				babySize = 7;
			fishCnt = 0; // 먹은 물고기 개수 0으로 갱신
		}
	}

	// 물고기 개수 세기
	static int cntFish(int size) {
		int sum = 0;
		for (int i = 1; i < size; i++)
			sum += fish[i].size();

		return sum;
	}

	// 거리 계산
	static int move(int ax, int ay, int bx, int by) {
		boolean[][] visited = new boolean[n][n];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { bx, by });
		visited[bx][by] = true;
		int moveCnt = 0; // 거리
		boolean check = false; // 상어가 물고기 위치에 갔는지 체크

		while (!queue.isEmpty()) {
			int qSize = queue.size();
			for (int i = 0; i < qSize; i++) {
				int curX = queue.peek()[0];
				int curY = queue.poll()[1];

				// 상어가 물고기 위치에 갔을 경우
				if (curX == ax && curY == ay) {
					check = true;
					break;
				}

				for (int j = 0; j < 4; j++) {
					int nx = curX + dx[j];
					int ny = curY + dy[j];

					// 상어 크기보다 작거나 같은 곳만 이동 가능
					if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] <= babySize && !visited[nx][ny]) {
						queue.add(new int[] { nx, ny });
						visited[nx][ny] = true;
					}
				}
			}

			// 물고기 위치에 갔을 경우, while문 탈출
			if (check)
				break;

			moveCnt++; // 거리 증가
		}

		// 물고기 위치에 못 갔을 경우
		if (!check)
			moveCnt = Integer.MAX_VALUE;

		return moveCnt;
	}
}
