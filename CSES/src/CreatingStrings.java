import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CreatingStrings
{
    static Map<Character , Integer> map;
    static int[] words;
    static int [] hist;
    static int N;
    static char[] idxToKey;
    static int ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        map = new TreeMap<>();

        N = input.length();
        for(char c : input.toCharArray()){
            map.merge(c,1,(o , n)->o+1);
        }
        words= new int[map.size()];
        idxToKey = new char[map.size()];
        int idx =0;
        for(char k : map.keySet()){
            words[idx] = map.get(k);
            idxToKey[idx++] = k;
        }
        hist = new int[N];
        dfs(0);
        StringBuilder a = new StringBuilder();
        a.append(ans).append("\n").append(sb);
        System.out.print(a);

    }

    public static void dfs(int idx){
        if(idx == N){
            for(int i = 0 ; i < N ; i++){
                sb.append(idxToKey[hist[i]]);
            }
            sb.append("\n");
            ans++;
            return;
        }
        for(int i=0 ; i< words.length ; i++){
            if(words[i] == 0) continue;
            hist[idx] = i;
            words[i]--;
            dfs(idx+1);
            words[i]++;
        }
    }

}
