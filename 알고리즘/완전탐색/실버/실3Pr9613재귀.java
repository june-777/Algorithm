package 실버;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 실3Pr9613재귀 {
	static boolean[] visit;
	static int[] answer = new int[2];
	static long sum = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			sum = 0;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] numArray = new int[N];
			visit = new boolean[N];
//			answer = new int[N];
			for (int i=0; i<N; i++) {
				numArray[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, numArray, N);
			sb.append(sum + "\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int depth, int idx, int[] numArray, int N) {
		
		//base case
		if(depth==2) {
//			print();
			Gcd();
			return;
		}
		
		//recursive case
		for (int i=idx; i<N; i++) {
			if(!visit[i]) {
				answer[depth] = numArray[i];
				visit[i] = true;
				dfs(depth+1, i+1, numArray, N);
				visit[i] = false;
			}
		}
	}
//	private static void print() {
//		for (int i=0; i<answer.length; i++) {
//			System.out.print(answer[i] + " ");
//		}
//		System.out.println();
//	}
	private static void Gcd() {
		int a = answer[0];
		int b = answer[1];
		
		while(b!=0) {
			int r = a % b;
			a = b;
			b = r;
		}
		sum += a;
	}
}
