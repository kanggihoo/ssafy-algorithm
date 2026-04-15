package Trie.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G3_14725 {
	static StringBuilder sb;
	
	static class Node{
		Map<String, Node> child;
		boolean isLast;
		
		Node(){
			child = new HashMap<>();
		}
		
	}
	
	static class Trie{
		private final Node root;
		Trie(){
			root= new Node();
		}
		
		
		public void insert(String[] input) {
			Node cur = root;
			for(String str : input) {
				cur.child.putIfAbsent(str, new Node()); // 해당 키가 없는 경우 새롭게 생성
				cur = cur.child.get(str);
			}
			cur.isLast = true;
		}
		
		public void print() {
			search(root , 0);
		}
		private void search( Node cur , int depth) {
			
			Map<String , Node> child = cur.child;
			List<String> keys = new ArrayList<>(child.keySet());
			keys.sort(null);
			for(String key : keys) {
				for(int d =0 ; d<depth ; d++) {
					sb.append("--");
				}
				sb.append(key).append("\n");
				search(child.get(key) , depth+1);
			}
		}
	}
	

	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		sb = new StringBuilder();
		Trie t = new Trie();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			String[] input = new String[k];
			int idx = 0;
			for(int j = 0 ; j < k ; j++) {
				input[idx++] = st.nextToken();
			}
			t.insert(input);
		}
		
		t.print();
		System.out.print(sb);
		
		
		
		
	}

}
