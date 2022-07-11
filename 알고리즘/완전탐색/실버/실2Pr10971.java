package 실버;

import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**백준Pr10971: 외판원순회2
 * */
public class 실2Pr10971 {
	static int[][] board;
	static boolean[] visit;
	static int[] path;
	static int N;
	static int sum;
	static long min = Long.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		visit = new boolean[N+1];
		path = new int[N+1];
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				int edge = Integer.parseInt(st.nextToken());
				board[i][j] = edge;
			}
		}
		
		for(int start=1; start<=N; start++) {
			Arrays.fill(visit, false);
			Arrays.fill(path, 0);
			sum = 0;
			path[0] = start;
//			path[N] = start;
			visit[start] = true;
			dfs(1, start, start);

		}
		System.out.println(min);
		
	}
	private static void dfs(int depth, int now, int start) {
		
		//base case
		if(depth==N) {
			
			if(board[path[path.length-2]][start] != 0) {
				path[path.length-1] = start;
				result();
				return;
			}
			else {
				return;
			}
//			for(int i=0; i<path.length; i++) {
//				System.out.print(path[i] + " ");
//			}
//			System.out.println();
//			result();
//			return;
		}
		
		
		
		//recursive case
		for (int i=1; i<=N; i++) {
			if(!visit[i] && board[now][i]!=0) {
				path[depth] = i;
				visit[i] = true;
				dfs(depth+1, i, start);
				visit[i] = false;
			}
		}
	}
	private static void result() {
		int sum = 0;
		for(int i=0; i<path.length-1; i++) {
			int from = path[i];
			int to = path[i+1];
			sum += board[from][to];
		}
//		System.out.println("해당경로의 값은: " + sum);
		min = Math.min(min ,sum);
//		System.out.println("");
	}
	
}
