package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class boj_11478 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Set<String> set = new HashSet<>();

		String str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str.length() - i; j++) {
				set.add(str.substring(j, j + i + 1));
			}
		}
		
		sb.append(set.size());
		System.out.println(sb);
	}

}
