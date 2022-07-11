package 실버;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 실3Pr9613반복문 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			int sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] numArray = new int[N];

			for (int i=0; i<N; i++) {
				numArray[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i=0; i<numArray.length-1; i++) {
				for (int j=i+1; j<numArray.length; j++) {
					sum += gcd(numArray[i], numArray[j]);
				}
			}
			sb.append(sum + "\n");
		}
		System.out.println(sb);
	}
	private static int gcd(int a, int b) {
		while(b!=0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

}
