## 알고리즘을 진행하며 학습한 구현 테크닉 정리


#### board[i][j]를 power만큼 감소시키고, 0 미만이면 0으로 설정
- before
```java
board[i][j] -= power;
if(board[i][j] < 0) {
    board[i][j] = 0;    
}
```

- after
```java
board[i][j] = Math.max(0, board[i][j] - power);
```

<br> </br>

#### 우측 하단부터, ↙ 방향으로 2차원 배열 탐색 (idx = 0부터 시작)
```java
for(int sum = R + C - 2; sum >= 0; sum --) {
    for(int col = C - 1; col >= 0; col --) {
        int row = sum - col;
        if (row < 0 || row >= R) continue;
    }
}
```
<br> </br>

#### 좌측 상단부터, ↗ 방향으로 2차원 배열 탐색 (idx = 0부터 시작)
```java
for(int sum = 0; sum <= R + C - 2; sum ++) {
    for(int col = 0; col < C; col++) {
        int row = sum - col;
        if(row < 0 || row >= R) continue;
    }
}
```
<br> </br>

#### 격자를 벗어났을 때, 반대편으로 넘어가는 테크닉

- before
```java
if (nr == R) {
    row = 0;    
} else if (nr == -1) {
    row = R-1;
}
```

- after
```java
row = (nr + R) % R;    //인덱스 0부터 시작
row = (nr + R -1) % R + 1; //인덱스 1부터 시작
```
<br> </br>

#### 숫자 num1과 num2의 최대공약수 구하기 (a >= b)
- 방법1: 유클리드 호제법
- ex. gcd(48, 32) -> gcd(32, 16) -> gcd(16, 0) -> return 16
```java
private int gcd(int num1, int num2) {
    if(num2 == 0) {
        return num1;
    }
    return gcd(num2, num1 % num2);
}
```

- 방법2: `BigInteger` gcd API 사용
```java
BigInteger n1 = BigInteger.valueOf(num1);
BigInteger n2 = BigInteger.valueOf(num2);
int gcd = n1.gcd(n2).intValue();
```
