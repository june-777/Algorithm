## Programmers Problem

#### 두큐합같게만들기 (https://school.programmers.co.kr/learn/courses/30/lessons/118667)

- **date:** 2023.06.27 `35m solve`

- **type:** `two pointer`

- **learned:** 
  - O(N) 시간복잡도를 요구한다면, 투포인터를 고려할 것
  - 문제와 예시를 꼼꼼히 읽고 도출할 수 있는 사실이 무엇인지 파악할 것

---

**Input:**

- Queue1길이 == Queue2길이: 1 ~ 300,000 -> `O(N)`
- Queue원소 = 1 ~ 10^9 -> `long type`

**Goal:** 두개의 큐 Q1, Q2가 있다. 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수는? 없는 경우는 -1

- 작업1회: 하나의 큐에서 pop, 다른 큐에서 add


```
ex. Q1 = [3, 2, 7, 2], Q2 = [4, 6, 5, 1]
방법1.
Q1 = [3, 2, 7, 2, 4, 6, 5] (작업 3회)
Q2 = [1]

Q1 = [4, 6, 5] (작업 4회)
Q2 = [1, 3, 2, 7, 2]

방법2.
Q1 = [2, 7, 2] (작업 1회)
Q2 = [4, 6, 5, 1, 3]

Q1 = [2, 7, 2, 4] (작업 1회)
Q2 = [6, 5, 1, 4]
```


```
도출할 수 있는 사실
1. 큐의 원소들은 순서를 유지한다. 즉, 최초 상태에서 포인터 p1, p2를 움직이며 두 수합이 최초로 같은 경우를 찾으면 된다.
     Q 1        Q 2
[3, 2, 7, 2,| 4, 6, 5, 1]
p1        p2
```





**설계:**

`edge case:` 두큐합이 홀수인 경우 각 큐 원소 합 같을 수 없다.

1. Q1Sum < 목표합 -> p2 ++
2. Q1Sum > 목표합 -> p1 ++
3. Q1Sum == 목표합 -> return

`종료조건:` 로직상, p1 or p2가 끝에 도달하는 경우

---

 ```java
public class Solution2 {
    public long solution(int[] queue1, int[] queue2) {
        long sum = getSum(queue1, queue2);
        if(sum % 2 == 1) {
            return -1;
        }
        List<Long> nums = makeList(queue1, queue2);
        long answer = twoPointer(nums, queue1.length, sum / 2);
        return answer;
    }

    private long twoPointer(List<Long> nums, int queueLength, long wantSum) {
        long q1Sum = initialQ1Sum(nums, queueLength);
        long q2Sum = initialQ2Sum(nums, queueLength);

        int p1 = 0, p2 = queueLength-1;
        int size = nums.size();
        long count = 0;
        while(p1 < size-1 && p2 < size-1) {
            if(q1Sum < wantSum) {
                p2 ++;
                q1Sum += nums.get(p2);
                q2Sum -= nums.get(p2);
            } else if(q1Sum > wantSum) {
                q1Sum -= nums.get(p1);
                q2Sum += nums.get(p1);
                p1 ++;
            } else {
                return count;
            }
            count++;
        }
        return -1;
    }

    private long initialQ1Sum(List<Long> nums, int length) {
        long q1Sum = 0L;
        for(int idx=0; idx<length; idx++) {
            q1Sum += nums.get(idx);
        }
        return q1Sum;
    }

    private long initialQ2Sum(List<Long> nums, int length) {
        long q2Sum = 0L;
        for(int idx=length; idx<nums.size(); idx++) {
            q2Sum += nums.get(idx);
        }
        return q2Sum;
    }

    private List<Long> makeList(int[] q1, int[] q2) {
        List<Long> list = new ArrayList<>();
        for(int num: q1) {
            list.add((long)num);
        }
        for(int num: q2) {
            list.add((long)num);
        }
        return list;
    }

    // edge: 원소합 홀수인 경우
    private long getSum(int[] q1, int[] q2) {
        long sum = 0;
        for(int idx=0, n=q1.length; idx<n; idx++) {
            sum += (long) q1[idx] + (long) q2[idx];
        }
        return sum;
    }
}
 ```
