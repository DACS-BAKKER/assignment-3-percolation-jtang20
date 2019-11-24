import java.util.Scanner;

public class WeightedQuickUnion {
    private static int[] id;   // array
    private int[] size;     // size[i] = number of sites in subtree rooted at i

    public static void main(String args[]){ // runner
        Scanner sam = new Scanner(System.in);
        System.out.println("Please determine size of array");
        int n = sam.nextInt();
        WeightedQuickUnion wqu = new WeightedQuickUnion(n);
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
                System.out.print(id[i]+" ");
            }
        }
    }
    public WeightedQuickUnion(int n){
        id = new int[n*n+2];
        size = new int[n*n+2];
        for (int i = 0; i < n*n+2; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }
    public int root(int i){  //finds root
        while(i!=id[i]){
            i=id[i];
        }
        return i;
    }
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }
}
