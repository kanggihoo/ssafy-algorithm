package BinarySearch.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Product implements Comparable<Product>{
	String name;
	int price;
	int quality;
	
	Product(String n , int p , int q){
		this.name = n ;
		this.price = p;
		this.quality = q;
	}

	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.price, o.price);
	}
	
	
}

public class G3_3691 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			// 
			Map<String , List<Product>> products = new HashMap<>();
			// 부품 정보
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				String type = st.nextToken();
				String name = st.nextToken();
				int price = Integer.parseInt(st.nextToken());
				int quality = Integer.parseInt(st.nextToken());
				
				products.computeIfAbsent(type, k-> new ArrayList<>()).add(new Product(name , price , quality));
				
			}
			// 각 제품별 가격 정렬 
			for(List<Product>product : products.values()) {
				product.sort(null);
			}
			
			// 기준이 되는 q 값 정하고  
			int L = 0;
			int R = 1000000000;
			
			while(L <= R) {
				int mid = R - ((R-L)/2);
				if(check(mid , products , B)) { // 가능하면  증가시키고 가능하지 않으면 줄이기 
					L = mid+1;
				}else {
					R = mid-1;
				}
			}
			sb.append(R).append("\n");
		}
		System.out.print(sb);

	}
	
	public static boolean check(int criterion , Map<String , List<Product>> products , int target) {
		int total = 0;
		for(List<Product> product : products.values()) {
			Product a=null;
			for(Product p : product) {
				if(p.quality >= criterion) {
					a=p;
					break;
				}
			}
			if(a == null) return false;
			total+= a.price;
		}
		return total > target ? false : true;
	}

}
