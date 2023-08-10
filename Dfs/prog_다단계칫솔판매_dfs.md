## Programmers Problem

#### 다단계칫솔판매 (https://school.programmers.co.kr/learn/courses/30/lessons/77486)

- **date:** 
  -  14:30 ~ 13:20 solve (dfs version)

- **type:** `bruteforce`, `dfs`

- **learned:** 
  - 이 후의 방문 필요가 없는 경우, 방문하지 않음으로서 최적화
  - 해당 문제의 최악의 경우는 `노드개수 10만개`, `편향 트리`, `시작노드(리프노드) 10만개`
  - O(10만 x 10만) -> TLE
  - 방문 마다, 금액이 10%씩 줄어듦으로, 방문 시 금액을 base case로 걸 수 있다.
  - 따라서, 각각의 총 방문 수는 10개로 줄일 수 있다.
  - O(10 x 10만) -> 가능
  
---

```java
 dfs 설계
        
 최적화:
 노드를 방문할 때의 금액은 10%로 계속 줄어든다.
 => 즉, 방문했을 때 금액의 10% 금액이 1원 미만이 된 경우는 더 이상 방문할 필요가 없다.
 => 0원인채로 방문해봐야 누적 수익금에는 변동이 없기 때문
 => seller당 최대 방문 10회로 최적화 가능
 => O(10 * 10만) => 가능

        
 체크인: 현재 금액의 10% 금액 계산. 나머지 금액에 대하여 현재 노드에 누적갱신.
 base: 없음. 갈수있는 곳에서 다 검출한 로직임.
 연결된 곳: 부모노드
 갈수있는곳: 현재 금액의 10% 금액이 1원 이상이고, 현재 노드가 루트노드가 아닌 경우
```

---

 ```java
public class Solution2_dfs {
    private Map<String, String> curAndParent;
    private Map<String, Integer> curAndProfit;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = pro2(enroll, referral, seller, amount);
        return answer;
    }

    private int[] pro2(String[] enroll, String[] referral, String[] seller, int[] amount) {
        initCurAndParent(enroll, referral);
        initCurAndProfit(enroll);
        for(int start=0; start<seller.length; start++) {
            dfs(seller[start], amount[start] * 100);
        }
        return getAnswer(enroll);
    }

    private int[] getAnswer(String[] enroll) {
        int[] answer = new int[enroll.length];
        for(int idx = 0; idx < answer.length; idx ++) {
            answer[idx] = curAndProfit.get(enroll[idx]);
        }
        return answer;
    }

    private void initCurAndParent(String[] enroll, String[] referral) {
        curAndParent = new HashMap<>();
        for(int idx=0; idx<referral.length; idx++) {
            curAndParent.put(enroll[idx], curAndParent.getOrDefault(enroll[idx], "") + referral[idx]);
        }
    }

    private void initCurAndProfit(String[] enroll) {
        curAndProfit = new HashMap<>();
        for(String name: enroll) {
            curAndProfit.put(name, 0);
        }
    }

    private void dfs(String node, int price) {

        // 체크인: 현재금액의 10% 계산. 나머지 금액은 수익 누적 갱신
        int toParentProfit = (int) (((double) price * 10) / 100);
        int myProfit = price - toParentProfit;
        curAndProfit.put(node, curAndProfit.getOrDefault(node, 0) + myProfit);

        // 연결된곳: 부모노드
        // 갈수있는가?: 현재 금액의 10%가 1원 이상이고, 부모노드가 루트가 아닌 경우
        String parent = curAndParent.get(node);
        if(toParentProfit >= 1 && !parent.equals("-")) {
            dfs(parent, toParentProfit);
        }
    }
}
 ```
