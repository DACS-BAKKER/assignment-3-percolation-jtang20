import java.util.Scanner;

public class WeightedQuickUnionPathCompression{
    private static int[] parent;  // parent[i] = parent of i
    private static int[] size;    // size[i] = number of sites in tree rooted at i
    private int count;

    public static void main(String[] args) {
        Scanner sam = new Scanner(System.in);
        System.out.println("Please determine size of array");
        int n = sam.nextInt();
        WeightedQuickUnionPathCompression wqu = new WeightedQuickUnionPathCompression(n);
        while(true){
            System.out.println("Enter two indexes that you want to connect, enter -1 to stop");
            int p = sam.nextInt();
            if(p==-1){
                break;
            }
            int q = sam.nextInt();
            if(q==-1){
                break;
            }
            wqu.union(p,q);
            for(int i = 0; i < n; i++){
                System.out.print(parent[i]+" ");
                System.out.print(size[i]+" ");
            }
        }
    }
    public WeightedQuickUnionPathCompression(int n) {
        count = n;
        parent = new int[n*n+2];
        size = new int[n*n+2];
        for (int i = 0; i < n*n+2; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root])
            root = parent[root];
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

}
