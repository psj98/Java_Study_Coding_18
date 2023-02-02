package study.study_230201;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class alg_15552 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] temp = br.readLine().split(" ");
            bw.write(Integer.parseInt(temp[0]) + Integer.parseInt(temp[1]) + "\n");
        }
        bw.flush();
    }
}
