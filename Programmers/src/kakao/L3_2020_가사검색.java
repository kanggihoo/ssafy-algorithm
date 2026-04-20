//package kakao;
//
//import java.util.*;
//public class L3_2020_가사검색 {
//    class Trie {
//        Trie[] child;
//        int cnt;
//
//        Trie(){
//            child = new Trie[26];
//            cnt =0;
//        }
//    }
//
//    class TrieManager{
//        Trie root = new Trie();
//
//        public void insert(String str){
//            Trie cur = root;
//            for(int i = 0 ; i < str.length() ; i++){
//                int idx = str.charAt(i) -'a';
//                if(cur.child[idx] == null){
//                    cur.child[idx] = new Trie();
//                }
//                cur = cur.child[idx];
//            }
//            // 문자열 삽입 완료
//            cur.cnt++;
//        }
//
//        public int search(String str){
//            // ? 가 없는 문자열
//
//            int res = 0;
//            Trie cur = root;
//            for(int i = 0 ; i < str.length() ; i++){
//                int idx = str.charAt(i) -'a';
//                if(cur.child[idx] == null){
//                    return 0;
//                }
//                cur = cur.child[idx];
//            }
//            if(cur != null) res = cur.cnt;
//            return res;
//
//        }
//    }
//
//    public int[] solution(String[] words, String[] queries) {
//        // 모든 단어에 대해 트라이 구조 만들기
//        int[] ans = new int[queries.length];
//        int[] size = new int[10000+1];
//        TrieManager tm = new TrieManager();
//        for(String word : words){
//            tm.insert(word);
//            size[word.length()]++;
//        }
//
//        // 쿼리 에 대해 결과 반환
//        for(String q : queries){
//            if(q.charAt(0) == '?'){
//                int sIdx = findQ(q,0); // 정방향
//            }else{
//                int sIdx = findQ(q , -1);
//            }
//        }
//        return answer;
//    }
//
//    public int findQ(String str , int d){
//        int res = 0;
//        if(d==0){
//            for(int i = 0 ; i < str.length() ; i++){
//                if(str.charAt(i) !='?'){
//                    res = i;
//                    break;
//                }
//            }
//        }else{
//            for(int i = str.length()-1 ; i>=0 ; i--){
//                if(str.charAt(i) !='?'){
//                    res = i;
//                    break;
//                }
//            }
//        }
//        return res;
//    }
//}
