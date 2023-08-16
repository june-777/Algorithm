## Programmers Problem

#### 멀쩡한사각형 (https://school.programmers.co.kr/learn/courses/30/lessons/62048?language=java)

- **date:**   
  1차: 2023.06.14 (참조 후 풀이)  
  2차: 2023.08.16 (21:30 ~ ) (규칙 못찾아서 다시 참고함)

- **type:** `math`

- **핵심:** 
1. 최소 공배수 O(logN) 알고리즘 :=  `gcd 유클리드 호제법`
   - gcd(32, 24) -> gcd(24, 8) -> gcd(8, 0) -> return num1
   - 외우려하지 말고, 예시 하나 생각해보면 바로 구현할 수 있음

    
2. 입력값이 터무니 없이 큼 + log scale을 고려해야 함 + 수학적인 규칙으로 접근해야 할 것 같음
   - 최소 공배수와 관련한 규칙이 있는지 생각해볼 것


3. BigInteger의 gcd API

---



**설계:**
1. 대각선에 포함된 정사각형의 개수: 가로 + 세로 - gcd(가로, 세로)
2. 정답: 가로 * 세로 - 대각선에 포함된 정사각형의 개수

---

 ```java
public class Solution {
    public long solution(int w, int h) {
        long result = sol1(w, h);
        // long result = sol2(w, h);
        return result;
    }

    // BigInteger gcd API 활용
    // 주의: import java.math.BigInteger 해야 함
    private long sol2(int w, int h) {
        BigInteger W = BigInteger.valueOf(w);   // to BigInteger
        BigInteger H = BigInteger.valueOf(h);
        BigInteger GCD = W.gcd(H);  // ** gcd method **
        int gcd = GCD.intValue();   // to primitive type
        int diagonal = w + h - gcd;
        long result = (long) w * h - diagonal;

        return result;
    }

    // gcd 알고리즘 직접 구현
    private long sol1(int w, int h) {
        int num1 = Math.max(w, h);
        int num2 = Math.min(w, h);
        int diagonal = w + h - gcd(w, h);
        long result = (long) w * h - diagonal;
        return result;
    }

    private int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return gcd(num2, num1 % num2);
    }
}
 ```
