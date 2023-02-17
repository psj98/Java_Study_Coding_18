package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1202 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		ArrayList<int[]> gem = new ArrayList<>(); // 보석 정보 (무게, 가격)
		PriorityQueue<Integer> bag = new PriorityQueue<>(); // 가방 무게
		PriorityQueue<Integer> selectGem = new PriorityQueue<>(Collections.reverseOrder()); // 보석 가격

		int gemNum = Integer.parseInt(stk.nextToken()); // 보석 개수
		int bagNum = Integer.parseInt(stk.nextToken()); // 가방 개수

		// gem에 보석 정보 저장
		for (int i = 0; i < gemNum; i++) {
			stk = new StringTokenizer(br.readLine());
			int gemWeight = Integer.parseInt(stk.nextToken());
			int gemValue = Integer.parseInt(stk.nextToken());
			gem.add(new int[] { gemWeight, gemValue });
		}

		// 무게가 적은 순으로 정렬 (compare -1, 0, 1 주의)
		Collections.sort(gem, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		// 가방 무게 오름차순 정렬
		for (int i = 0; i < bagNum; i++)
			bag.add(Integer.parseInt(br.readLine()));

		long sum = 0; // 합
		int idx = 0; // 보석 index
		while (!bag.isEmpty()) {
			int bagWeight = bag.peek(); // 가방 무게
			for (int i = idx; i < gemNum; i++) {
				if (gem.get(i)[0] > bagWeight) // 가방 무게보다 크면 break
					break;

				// 가방 무게보다 작으면 selectGem에 보석 가격만 추가
				selectGem.add(gem.get(i)[1]); // PriorityQueue (내림차순)
				idx++;
			}

			// size가 0이 아니면 sum에 추가
			if (selectGem.size() != 0) {
				sum += (long) selectGem.poll();
			}

			bag.poll();
		}

		sb.append(sum);
		System.out.println(sb);
	}

}

