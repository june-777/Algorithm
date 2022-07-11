package 실버;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class 실1Pr10974 {
	static int[] answer;
	static boolean[] visit;
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		answer = new int[N];
		visit = new boolean[N+1];
		dfs(0);
		System.out.println(sb);
	}
	private static void dfs(int depth) {
		
		//base case:
		if(depth==N) {
			print();
			return;
		}
		//recursive case:
		for (int i=1; i<=N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				answer[depth] = i;
				dfs(depth+1);
				visit[i] = false;
			}
		}
	}
	private static void print() {

		for (int i=0; i<answer.length; i++) {
			sb.append(answer[i] + " ");
		}
		sb.append("\n");
	}
}
