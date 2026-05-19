package sortAndSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Ticket implements Comparable<Ticket>{
    int idx;
    int p;
    Ticket(int idx , int p){
        this.idx = idx;
        this.p = p;
    }
    @Override
    public int compareTo(Ticket o) {
        if(p == o.p) return Integer.compare(o.idx , idx);
        return Integer.compare(p , o.p);
    }
}

public class ConcertTickets {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N개 콘서트 티켓
        int M = Integer.parseInt(st.nextToken()); // M명의 사람
        TreeSet<Ticket> T = new TreeSet<>();

        int[] P = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int p = Integer.parseInt(st.nextToken());
            T.add(new Ticket(i,p));
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++){
            P[i] = Integer.parseInt(st.nextToken());
        }

        // 처음 고객 부터 계산
        StringBuilder sb = new StringBuilder();
        Ticket comp = new Ticket(-1,0);
        for(int i = 0 ; i < M ; i++){
            int curP = P[i];
            if(T.size() == 0 || (T.size() > 0 && T.first().p > curP )){
                sb.append(-1).append("\n");
            }else{
                comp.p = curP;
                Ticket res = T.floor(comp);
                sb.append(res.p).append("\n");
                T.remove(res);
            }
        }

        System.out.print(sb);
    }
}
