package Day0406;

public class APT_강기호 {
	
	public static void main(String[] args) {
		int[] apt = new int[9];

		apt[1] = 2;
		apt[2] = 3;

		for (int i = 3; i <= 8; i++) {
			apt[i] = apt[i - 1] + apt[i - 2];
		}

		System.out.println(apt[8]);

	}

}
