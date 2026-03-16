package Trie.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class G3_3178 {

    static class TrieNode{
        boolean isEnd;
        TrieNode[] children = new TrieNode[26];
    }

    static class Trie{
        TrieNode root;
        int size;
        public Trie(){
            root = new TrieNode();
            size = 0;
        }

        public void insert(String words){
            TrieNode cur = root;
            for(int i = 0 ; i < words.length() ; i++){
                int idx = words.charAt(i) -'A';
                if(cur.children[idx] == null){
                    cur.children[idx] = new TrieNode();
                    size++;
                }
                cur = cur.children[idx];
            }

        }
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[] fs = new String[N];
        String[] bs = new String[N];

        for(int i = 0 ; i<N ; i++) {
            String line = br.readLine();
            fs[i] = line.substring(0,K);
            bs[i] = new StringBuilder(line.substring(K,2*K)).reverse().toString();
        }
        Trie t1 = new Trie();
        for(String words : fs){
            t1.insert(words);
        }
        Trie t2 = new Trie();
        for(String words : bs){
            t2.insert(words);
        }
        System.out.print(t1.size+t2.size);
    }
}
