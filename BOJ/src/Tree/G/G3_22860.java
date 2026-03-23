package Tree.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Node{
	List<String> files;
	List<String> folders;
	
	Node(){
		files = new ArrayList<String>();
		folders = new ArrayList<String>();
	}

}

public class G3_22860 {
	
	static Map<String , Node> G;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		G = new HashMap<String, Node>();
		
		for(int i = 0 ; i<N+M ; i++) {
			st = new StringTokenizer(br.readLine());
			String p = st.nextToken();
			String c = st.nextToken();
			boolean isFolder = Integer.parseInt(st.nextToken())==1 ?true : false; 
			if(!G.containsKey(p)) {
				G.put(p, new Node());
			}
			if(isFolder) G.get(p).folders.add(c);
			else  G.get(p).files.add(c);
		}
		
		// 쿼리 개수 
		int q = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < q ; i++) {
			String[] line = br.readLine().split("/");		
			Set<String> set = new HashSet<>();
			int fileCnt = dfs(line[line.length-1] , set);
			sb.append(set.size()).append(" ").append(fileCnt).append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	public static int dfs(String cur , Set<String> set) {
		if(!G.containsKey(cur)) return 0;
		int cnt = 0;
		for(String folder : G.get(cur).folders) {
			cnt += dfs(folder, set);
		}
		for(String file : G.get(cur).files ) set.add(file);
		cnt+= G.get(cur).files.size();
		
		
		return cnt;
	}
}
