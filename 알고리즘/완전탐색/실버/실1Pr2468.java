package 실버;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
public class 실1Pr2468 {
	static int[][] board;
	static boolean[][] isFlooded;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		isFlooded = new boolean[N][N];
		int maxHigh = 0;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				maxHigh = Math.max(maxHigh, board[i][j]);
			}
		}
//		System.out.println(maxHigh);
		int maxFlood = 0;
		for(int rain=1; rain<=maxHigh; rain++) {
			int countFlood = 0;
			arrayFill();
			
			for(int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					
					//강수량보다 높이가 낮고, 방문안한경우만 방문
					if(board[i][j]>rain && !isFlooded[i][j]) {
						isFlooded[i][j] = true;
						countFlood++;
						dfs(i, j, rain);
					}
				}
			}
			maxFlood = Math.max(maxFlood, countFlood);
//			System.out.println("강수량: " + rain + "일 때의 침수지도: ");
//			printVisit();
//			System.out.println("안전지역개수: " + maxFlood);
//			countFlood();
		}
		
		
		System.out.println(maxFlood);
	}
	private static void dfs(int x, int y, int rain) {
		
		
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<N && ny>=0 && ny<N) {
				if(board[nx][ny]>rain && !isFlooded[nx][ny]) {
					isFlooded[nx][ny] = true;
					dfs(nx, ny, rain);
				}	
			}
			
		}
	}
	private static void printVisit() {
		for (int i=0; i<isFlooded.length; i++) {
			for (int j=0; j<isFlooded[i].length; j++) {
				System.out.print(isFlooded[i][j]+ " ");
			}
			System.out.println();
		}
	}
	private static void arrayFill() {
		for (int i=0; i<isFlooded.length; i++) {
			Arrays.fill(isFlooded[i], false);
		}
	}
//	private static void countFlood() {
//		for (int i=0; i<visit.length; i++) {
//			for (int j=0; j<visit[i].length; j++) {
//				if(visit[i][j]==false) {
//					countSearch(i, j);
//				}
//			}
//		}
//	}
}
