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

public class boj_2910_1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer stk;
		Map<String, Pair> map = new HashMap<>(); // ?àò, ?úÑÏπ?, ?öü?àò
		int idx = 0; // ?úÑÏπ?

		br.readLine();

		stk = new StringTokenizer(br.readLine());
		while (stk.hasMoreTokens()) {
			String num = stk.nextToken();
			if (!map.containsKey(num))
				map.put(num, new Pair(idx++, 1));
			else
				map.put(num, new Pair(map.get(num).first, map.get(num).second + 1)); // ?úÑÏπ? Í∑∏Î??Î°?, ?öü?àò Ï¶ùÍ??
		}

		List<Entry<String, Pair>> list = new ArrayList<>(map.entrySet());

		// ?†ï?†¨
		Collections.sort(list, new Comparator<Entry<String, Pair>>() {
			@Override
			public int compare(Entry<String, Pair> o1, Entry<String, Pair> o2) {
				if (o1.getValue().second == o2.getValue().second)
					return o1.getValue().first < o2.getValue().first ? -1 : 1;
				return o1.getValue().second > o2.getValue().second ? -1 : 1;
			}
		});

		for (Entry<String, Pair> entry : list)
			for (int j = 0; j < entry.getValue().second; j++)
				sb.append(entry.getKey()).append(" ");

		System.out.println(sb);
	}

	public static class Pair {
		int first;
		int second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

}
