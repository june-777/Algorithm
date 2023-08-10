## Programmers Problem

#### 다단계칫솔판매 (https://school.programmers.co.kr/learn/courses/30/lessons/77486)

- **date:** 
  - 13:32 ~ 14:15 solve (while loop ver)

- **type:** `bruteforce`, `dfs`

- **learned:** 
  - 그래프를 Map으로 표현하고, while문으로 탐색하는 방법
  - ```java
        curAndParent <- Map<CurNode, ParentNode>
        String cur = startNode;    
        while(true) {
            cur = curAndParent.get(cur);
        }
    ```
---

**Input:** 
1. enroll[i] (구성원의 이름)
2. referral[i] (다단계에 참여시킨 다른 구성원의 이름)
3. seller[i]: (판매자 이름)
4. amount[i]: (판매자가 득한 이익금)


**Goal:** 각 판매원이 득한 총 이익금을 구하기.
- enroll에 민호의 이름은 없다.
- enroll의 길이와 referral의 길이는 같다.
- referral[i] = enroll[i] 구성원을 조직에 참여시킨 사람의 이름
- enroll[i] 의 부모노드
- referral[i] = "-"이라면, 루트노드에 분배한다.


```
이익 분배 규칙
 - 판매 이익의 10%는 부모에게 전달
 (판매 이익의 10%가 1원 미만이면 전달하지 않고 자신의 이익으로 계산)
 - 판매 이익의 90%는 자신의 이익
```

**설계:**
1. Map<자기자신, 부모>로 표현  
2. 판매자 seller[i]를 시작지점으로 하여 Map을 탐색한다.  
3. 탐색하며, 현재 금액에서 부모에게 전달할 금액과 자신이 받을 이익을 계산하여 누적한다.  
4. 현재 금액을 전달할 금액으로 갱신한다.  
5. 부모가 "-"이면 종료한다.  
6. **부모에게 전달할 금액이 1원 미만이여도 종료한다.**  
   1원 미만이면, 원 단위로 절삭하는 문제 조건 상 0원을 계속 부모에게 넘겨주는 꼴이다.  
   즉, 필요없는 행위이며, 노드개수 10,000개이기 때문에 해당 조건에서 break으로 최적화하지 않으면 시간초과가 발생한다.


---

 ```java
public class Solution1 {
    private Map<String, String> curAndParent;
    private Map<String, Integer> curAndProfit;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = pro(enroll, referral, seller, amount);
        return answer;
    }

    private int[] pro(String[] enroll, String[] referral, String[] seller, int[] amount) {

        // 1. Map<자기자신, 부모>로 표현
        initMap(enroll, referral);
        initProfit(enroll);

        // 2. 판매자 seller[i]를 시작지점으로 하여 Map을 탐색한다.
        for(int idx=0; idx<seller.length; idx++) {
            updateProfit(seller[idx], amount[idx] * 100);
        }
        // System.out.println(curAndProfit);
        int[] answer = getAnswer(enroll);
        return answer;
    }

    private int[] getAnswer(String[] enroll) {
        int[] answer = new int[enroll.length];
        for(int idx=0; idx<enroll.length; idx++) {
            String name = enroll[idx];
            answer[idx] = curAndProfit.get(name);
        }
        return answer;
    }

    private void initProfit(String[] enroll) {
        curAndProfit = new HashMap<>();
        for(String name: enroll) {
            curAndProfit.put(name, 0);
        }
    }

    // seller를 시작으로 하는 구성원들의 판매금액 누적갱신
    private void updateProfit(String seller, int amount) {
        String cur = seller;
        while(true) {
            // 3. 현재 금액에서 부모에게 전달할 금액과 자신이 받을 이익을 계산하여 누적한다.
            int parentProfit = calculateParentProfit(amount);
            // 부모에게 넘길 수입이 1원 미만인 경우, 이득을 분배하지 않고 자신이 모두 갖는다.
            if(parentProfit < 1) {
                curAndProfit.put(cur, curAndProfit.getOrDefault(cur, 0) + amount);
                break;  // 분배하지 않는다는 것은 더이상 탐색을 할 필요가 없음을 의미한다.
                //(없으면 시간초과 주의)
            }
            curAndProfit.put(cur, curAndProfit.getOrDefault(cur, 0) + amount - parentProfit);
            // 4. 현재 금액을 전달할 금액으로 갱신한다.
            amount = parentProfit;
            // 5. 부모가 "-"이면 종료한다.
            if(curAndParent.get(cur).equals("-")) {
                break;
            }
            cur = curAndParent.get(cur);
        }
    }

    // 10%를 계산하고, 원 단위로 절삭한다.
    private int calculateParentProfit(int amount) {
        return (int) (((double) amount * 10) / 100);
    }

    private void initMap(String[] enroll, String[] referral) {
        curAndParent = new HashMap<>();

        for(int idx=0; idx<enroll.length; idx++) {
            String cur = enroll[idx];
            String parent = referral[idx];
            curAndParent.put(cur, curAndParent.getOrDefault(cur, "") + parent);
        }
    }

}
 ```
