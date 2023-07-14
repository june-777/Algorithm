## Union-Find Code Template
#### Time Complexity: O(log V) (V: 노드개수)

```java
public class UnionFind {
    private static int totalNode;
    private static int[] parents;

    public static void main(String[] args) {

    }

    private static void initParents() {
        parents = new int[totalNode + 1];
        for(int node=1; node<=totalNode; node++) {
            parents[node] = node;
        }
    }

    private static void union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);
        if(rootA != rootB) {
            parents[rootA] = rootB;
        }
    }

    private static int find(int node) {
        if(parents[node] == node) {
            return node;
        }
        return parents[node] = find(parents[node]);
    }
}
```
