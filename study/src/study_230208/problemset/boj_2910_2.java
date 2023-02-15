package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class boj_2910_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer stk;
		Map<String, int[]> map = new HashMap<>(); // 수, 위치, 횟수
		int idx = 0; // 위치

		br.readLine();

		stk = new StringTokenizer(br.readLine());
		while (stk.hasMoreTokens()) {
			String num = stk.nextToken();

			// map에 없는 수
			if (!map.containsKey(num))
				map.put(num, new int[] { idx++, 1 });
			else // map에 있는 수
				map.put(num, new int[] { map.get(num)[0], map.get(num)[1] + 1 }); // 위치 그대로, 횟수 증가
		}

		// 정렬을 위해 List<Entry<>>로 변경
		List<Entry<String, int[]>> list = new ArrayList<>(map.entrySet());

		// 정렬
		Collections.sort(list, new Comparator<Entry<String, int[]>>() {
			@Override
			public int compare(Entry<String, int[]> o1, Entry<String, int[]> o2) {
				// 횟수가 같으면 위치가 앞인 값을 먼저 출력
				if (o1.getValue()[1] == o2.getValue()[1])
					return o1.getValue()[0] < o2.getValue()[0] ? -1 : 1;

				// 횟수가 다르면 횟수가 많은 값을 먼저 출력
				return o1.getValue()[1] > o2.getValue()[1] ? -1 : 1;
			}
		});

		// 횟수만큼 출력
		for (Entry<String, int[]> entry : list)
			for (int j = 0; j < entry.getValue()[1]; j++)
				sb.append(entry.getKey()).append(" ");

		System.out.println(sb);
	}

}
