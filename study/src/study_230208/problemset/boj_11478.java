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

		// 부분 문자열 잘라서 set에 저장 (중복 제거)
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str.length() - i; j++) {
				set.add(str.substring(j, j + i + 1));
			}
		}
		
		// set의 크기가 부분 문자열 개수
		sb.append(set.size());
		System.out.println(sb);
	}

}
