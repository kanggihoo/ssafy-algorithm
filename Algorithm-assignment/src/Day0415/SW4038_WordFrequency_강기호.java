package Day0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW4038_WordFrequency_강기호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String text = br.readLine();
            String pattern = br.readLine();

            int ans = kmp(text, pattern);

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

    // KMP 알고리즘 메인 로직
    static int kmp(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] pi = getPi(pattern);
        int count = 0;
        int j = 0;

        for (int i = 0; i < n; i++) {
            // 일치하지 않으면 pi 배열을 이용해 j 이동
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            // 문자가 일치하는 경우
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == m - 1) {
                    count++; // 단어 발견
                    j = pi[j]; // 중첩 확인을 위해 pi 값을 이용해 포인터 이동
                } else {
                    j++;
                }
            }
        }
        return count;
    }

    // 부분 일치 테이블(Failure Function) 생성
    static int[] getPi(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        int j = 0;

        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}