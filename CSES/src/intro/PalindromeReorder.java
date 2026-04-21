package intro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromeReorder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        int[] counter= new int[26];
        char[] ans = new char[input.length()];
        for(char c : input.toCharArray()){
            counter[c-'A']++;
        }

        int idx = 0;
        boolean flag = false;
        if(input.length() %2==0){
            for(int i = 0 ; i < 26 ; i++){
                if(counter[i] %2 != 0){
                    flag =true;
                    break;
                }
                for(int j = 0 ; j<counter[i] /2 ; j++){
                    ans[idx] = (char)(i+'A');
                    ans[ans.length-1-idx] = (char)(i+'A');
                    idx++;
                }
            }
        }else{
            int oddIdx=-1;
            int oddCnt = 0;
            for(int i = 0 ; i < 26 ; i++){
                if(counter[i] %2 != 0 && oddCnt >= 1){
                    flag=true;
                    break;
                }
                if(counter[i] % 2 == 0){
                    for(int j = 0 ; j < counter[i]/2 ; j++){
                        ans[idx] = (char)(i+'A');
                        ans[ans.length-1-idx] = (char)(i+'A');
                        idx++;
                    }
                }else{
                    oddCnt++;
                    oddIdx = i;
                }
            }

            for(int j = 0 ; j< counter[oddIdx] ; j++){
                ans[idx+j] = (char)(oddIdx+'A');
            }

        }

        if(flag){
            sb.append("NO SOLUTION");
        }else{
            sb.append(ans);
        }
        System.out.print(sb);


    }
}
