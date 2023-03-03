package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class boj_4195 {
	static int[] parents; // 부모 노드
	static int[] size; // 각 정점 별 크기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine()); // testcase
		for (int tc = 1; tc <= t; tc++) {
			Map<String, Integer> name = new HashMap<>(); // (이름, idx)
			int idx = 0; // 각 이름 별 idx
			int n = Integer.parseInt(br.readLine());

			makeSet(); // 초기화

			for (int i = 0; i < n; i++) {
				String[] person = br.readLine().split(" ");
				int personIdx1 = -1, personIdx2 = -1; // 이름 idx

				// 이미 이름이 있으면 해당 value를 가져옴
				// 이름이 없으면 idx를 저장하고 map에 추가
				if (!name.containsKey(person[0])) {
					personIdx1 = idx;
					name.put(person[0], idx++);
				} else {
					personIdx1 = name.get(person[0]);
				}

				if (!name.containsKey(person[1])) {
					personIdx2 = idx;
					name.put(person[1], idx++);
				} else {
					personIdx2 = name.get(person[1]);
				}

				union(personIdx1, personIdx2); // union

				sb.append(size[findSet(personIdx1)]).append("\n"); // 크기 가져오기
			}
		}

		System.out.println(sb);
	}

	// 초기화 -> 이름 최대 200000개
	static void makeSet() {
		parents = new int[200001];
		size = new int[200001];

		for (int i = 0; i < 200001; i++) {
			parents[i] = i;
			size[i] = 1;
		}
	}

	// findset
	static int findSet(int num) {
		if (num == parents[num])
			return num;
		return parents[num] = findSet(parents[num]); // rank 갱신
	}

	// union
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		// 같으면 바꿀 필요 없음
		if (aRoot == bRoot)
			return;

		// 부모 정점 번호가 작은 쪽으로 union
		if (aRoot < bRoot) {
			parents[bRoot] = aRoot; // 부모 정점 변경
			size[aRoot] += size[bRoot]; // 크기 변경 -> 작은 쪽에 붙였으므로 작은 쪽으로 사이즈 추가
			size[bRoot] = 0; // 큰 쪽 사이즈 0
		} else {
			parents[aRoot] = bRoot;
			size[bRoot] += size[aRoot];
			size[aRoot] = 0;
		}
	}
}
