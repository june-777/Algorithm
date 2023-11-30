### 프로그래머스 - 예상 대진표
- 2023.12.01 (자바 5분, 파이썬 15분)
- 입력 크기를 고려하여 log scale을 생각해야 함
- 파이썬은 `나누기 연산은 실수 처리함에 주의`
- 7 /= 2 -> 3.5
- 7 // 2 -> 3

#### java
```java
class Solution {
    public int solution(int n, int a, int b) {
        
        int answer = 0;
        
        while (a != b) {
            a = update(a);
            b = update(b);
            answer ++;
        }
        
        return answer;
    }
    
    int update(int target) {
        if (target % 2 == 1) {
            target /= 2;
            target += 1;
            return target;
        }
        return target /= 2;
    }
    
}
```

```python
def solution(n,a,b):
    answer = 0

    while a != b:
        a, b = (a+1) // 2, (b+1) // 2
        answer += 1

    return answer
```
