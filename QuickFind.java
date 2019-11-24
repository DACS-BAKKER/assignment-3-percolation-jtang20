import java.util.Scanner;

public class QuickFind {
    private static int[] id;
    //private static int[] id = new int[]{0,1,2,3,4,5,6,7,8,9}; used this array to test
    public static void main(String args[]){ //runner
        Scanner sam = new Scanner(System.in);
        System.out.println("Please determine size of array");
        int n = sam.nextInt();
        QuickFind qf = new QuickFind(n);
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
            qf.union(p,q);
            for(int i = 0; i < n; i++){
                System.out.print(id[i]+" ");
            }
        }
    }
    public QuickFind(int n) {
        id = new int[n*n+2];
        for (int i = 0; i < n*n+2; i++)
            id[i] = i;
    }
    public void union(int p, int q){
        for(int i = 0;i < id.length;i++){
            if(i!=p && id[i] == id[p]){
                id[i] = id[q];
            }
        }
        id[p]=id[q];
    }
}
