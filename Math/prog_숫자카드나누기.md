## Programmers Problem

#### 숫자카드 나누기 (https://school.programmers.co.kr/learn/courses/30/lessons/135807)

- **date:** 2023.07.10 (10:51 ~ 11:14 23m ref && solve)

- **type:** `Math`

- **learned:** 
  - 유클리드 호제법 알고리즘: ref: https://programmer-chocho.tistory.com/9

---

**Input:** 
1. arrayA길이 == arrayB길이
2. arrayA,B길이 L: ( 1 ~ 500,000)
3. O(N), O(M), O(N * logM), O(M * logN)

**goal:** 주어진 조건 중 하나를 만족하는 가장 큰 양의 정수 a를 구하시오. (없다면 0)

조건1. a는 arrayA 숫자들의 공약수이고, arrayB 숫자들을 나눌 수 없다.

조건2. a는 arrayB 숫자들의 공약수이고, arrayA 숫자들을 나눌 수 없다.



```
 ex. A = [10, 17], B = [5, 20]
 조건1. A의 공약수 없음.
 조건2. a는 B의 공약수인 5. 5로 A의 숫자를 나눌 수 있음.
 -> 실패

 ex. A = [10, 20], B = [5, 17]
 조건1. a는 A의 공약수인 10. 10으로 B의 숫자를 나눌 수 없음. -> 만족
 조건2. B의 공약수는 없음.
```


**설계:**
1. A 숫자들의 최대공약수를 구하고 O(N * logN)
2. B를 나눌 수 없는 공약수 중 최댓값 기록 O(N)

3. B의 숫자들의 최대공약수 모두 구하고 O(N * logN)
4. A를 나눌 수 없는 공약수 중 최댓값 기록 O(N)

-> `O(N * logN)`


---

 ```java
public class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // int answer = 0;
        int answer = pro(arrayA, arrayB);
        return answer;
    }

    private static int pro(int[] A, int[] B) {
        int max = 0;

        int aGCD = A[0];
        for(int i=0; i<A.length-1; i++) {
            int tempGCD = getGCD(A[i], A[i+1]);
            aGCD = getGCD(aGCD, tempGCD);
        }

        for(int idx = 0; idx < B.length; idx ++) {
            if(B[idx] % aGCD == 0) {
                break;
            }
            if(idx == B.length-1) {
                max = Math.max(max, aGCD);
            }
        }

        int bGCD = B[0];
        for(int i=0; i<B.length-1; i++) {
            int tempGCD = getGCD(B[i], B[i+1]);
            bGCD = getGCD(bGCD, tempGCD);
        }

        for(int idx = 0; idx < A.length; idx ++) {
            if(A[idx] % bGCD == 0) {
                break;
            }
            if(idx == A.length-1) {
                max = Math.max(max, bGCD);
            }
        }

        return max;
    }

    private static int getGCD(int num1, int num2) {
        if (num1 % num2 == 0) {
            return num2;
        }
        return getGCD(num2, num1%num2);
    }
}
 ```
