import java.util.Arrays;

public class ChangeMaker {
    public static void main(String[] args){
        int[] coins = new int[]{100,25,10,5,1};
        int[] res = change_greedy(87, coins);
        System.out.println(Arrays.toString(res));
    }
    public static int[] change_greedy(int n, int[] d){
        int[] res = new int[d.length];
        for(int i=0; i<d.length; i++){
            res[i] = n/d[i];
            n -= d[i]*res[i];
        }
        return res;
    }
}
