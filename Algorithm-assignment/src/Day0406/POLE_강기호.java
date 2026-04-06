package Day0406;

public class POLE_강기호 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] D = new int[7];
		D[1] = 2;
		D[2] = 5;
		
		for(int i = 3 ; i < 7 ;i++) D[i] = D[i-1]*2 + D[i-2];
		System.out.print(D[6]);
	}

}
