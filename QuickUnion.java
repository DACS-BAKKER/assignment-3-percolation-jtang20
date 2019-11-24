import java.util.Scanner;

public class QuickUnion {
    private static int[] id;
    public static void main(String args[]){ //runner
        Scanner sam = new Scanner(System.in);
        System.out.println("Please determine size of array");
        int n = sam.nextInt();
        QuickUnion qu = new QuickUnion(n);
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
            qu.union(p,q);
            for(int i = 0; i < n; i++){
                System.out.print(id[i]+" ");
            }
        }
    }
    public QuickUnion(int n) {
        id = new int[n*n+2];
        for (int i = 0; i < n*n+2; i++) {
            id[i] = i;
        }
    }
    public int root(int i){  //finds root
        while(i!=id[i]){
            i=id[i];
        }
        return i;
    }
    public void union(int p, int q) {
        if (root(p) == root(q)){
            return;
        }
        id[root(p)] = root(q);
    }
}
