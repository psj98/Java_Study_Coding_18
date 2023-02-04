package study_230201.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class alg_19583 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<String> in = new HashSet<>();
        Set<String> out = new HashSet<>();

        String line = null;
        int ans = 0;

        String[] time = br.readLine().split(" "); // S, E, Q

        while ((line = br.readLine()) != null) {
            String[] str = line.split(" "); // time, name

            if (str[0].compareTo(time[0]) <= 0)
                in.add(str[1]);
            else if (str[0].compareTo(time[1]) >= 0 && str[0].compareTo(time[2]) <= 0)
                out.add(str[1]);
        }

        for (String s : in) {
            if (out.contains(s))
                ans++;
        }

        System.out.println(ans);
    }
}
