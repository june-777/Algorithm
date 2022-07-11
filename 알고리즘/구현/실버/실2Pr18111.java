package 실버;

import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 실2Pr18111 {
	static int[][] board;
	static int time;
	static int resultHigh;
	static int minTime = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int inven = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		int minHeight = 0;
		int maxHeight = 0;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				int height = Integer.parseInt(st.nextToken());
				board[i][j] = height;
				minHeight = Math.min(minHeight, height);
				maxHeight = Math.max(maxHeight, height);
			}
		}
		
		for (int high=maxHeight; high>=minHeight; high--) {
			time = 0;
			//땅 최초상태 초기화
			int[][] board2 = board.clone();
			
			//땅고르기높이보다 높은 땅은 수거
			int curInven = initialInven(board2, high, inven);
			
			//땅고르기높이로 땅고르기할 수 있는지 확인
			if (!canFillLand(board2, high, curInven)) { //해당높이로 땅고르기할 수 있는가?
				continue;
			}
			
			if(time < minTime) {
				minTime = time;
				resultHigh = high;
			}
			else if (time == minTime) {
				if (high > resultHigh) {
					resultHigh = high;
				}
			}
//			minTime = Math.min(minTime, time);
			

		}
		System.out.println(minTime + " " + resultHigh);
		
		
	}

	private static int initialInven(int[][] board, int high, int inven) {
		int curInven = inven;
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				if(board[i][j] > high) {
					curInven += board[i][j] - high;
					time += 2*(board[i][j] - high);
//					board[i][j] = high;
				}
			}
		}
		return curInven;
	}

	private static boolean canFillLand(int[][] board, int high, int inven) {
		int curInven = inven;
		for(int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				
//				//기준높이보다 땅이 높은경우 땅수거 --> 이건 첫번째 로직에서 이미 수행한부분임.
//				if(board[i][j] > high) {
//					
//				}
				//기준높이보다 땅이 낮은경우 땅채우기
				if(board[i][j] < high) {
					
					if (high - board[i][j] > curInven) {
						return false;
					}
					else {
						curInven -= high - board[i][j];
						time += high - board[i][j];
					}
				}
			}
		}
		return true;
	}
}
