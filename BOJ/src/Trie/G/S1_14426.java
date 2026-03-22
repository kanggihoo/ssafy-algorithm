package Trie.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class TrieNode{
    TrieNode[] children;
    TrieNode(){
        children = new TrieNode[26];
    }
}

class Trie{
    TrieNode root;

    Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode cur = root;
        for(char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];
        }
    }

    public boolean check(String word){
        TrieNode cur = root;
        for(char c : word.toCharArray()){
            int idx = c-'a';
            if(cur.children[idx] == null) return false;
            cur = cur.children[idx];
        }
        return true;
    }
}

public class S1_14426 {



    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int ans = 0;
        Trie t = new Trie();
        for(int i = 0 ; i < N ; i++){
            String s = br.readLine();
            t.insert(s);
        }
        for(int i = 0 ; i < M ; i++){
            String q = br.readLine();
            if(t.check(q)) ans++;
        }

        System.out.print(ans);


    }
}
