package Tree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G4_P5639 {

	
	static StringBuilder sb; 
	
	static class  Node {
		Node left;
		Node right;
		int v;
		
		Node(int v){
			this.v = v;
			
		}
	}
	
	static class  Tree{
		Node root;
		
		void insert(int v) {
			Node newNode = new Node(v);
			if(root == null) {
				root = newNode;
				return;
			}
			
			Node cursor = root;
			Node pre = null;
			while(cursor != null) {
				pre = cursor;
				if(cursor.v > v) {
					cursor = cursor.left;  
				}else {
					cursor = cursor.right;
				}
			}
			if(pre.v > v) pre.left = newNode;
			else pre.right = newNode;
			
		}
		
		void postOrder(Node cur) {
			if(cur == null) {
				return;
			}
			
			postOrder(cur.left);
			postOrder(cur.right);
			sb.append(cur.v).append("\n");
		}
		
	}
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String line;
		Tree t = new Tree();
		
		while ((line = br.readLine()) != null && !line.isEmpty()){
			t.insert(Integer.parseInt(line));
		}
		t.postOrder(t.root);
		System.out.print(sb);
		
		
		
		
	}
	
	

}
