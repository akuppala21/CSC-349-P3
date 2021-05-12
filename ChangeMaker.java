import java.util.Arrays;
import java.util.Scanner;

public class ChangeMaker {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of coin-denominations and the set of coin values:");
        int[] denominations = new int[input.nextInt()];
        for(int i=0; i<denominations.length; i++){
            denominations[i] = input.nextInt();
        }
        System.out.println();
        while(true){
            System.out.println("Enter a positive amount to be changed (enter 0 to quit):");
            int n = input.nextInt();
            if(n<=0) break;

            int[] resultDynamicProgramming = change_dp(n, denominations);
            System.out.println("\nDP algorithm results");
            System.out.printf("Amount: %d\n", n);
            System.out.printf("Optimal distribution: %s\n", format(denominations, resultDynamicProgramming));
            System.out.printf("Optimal coin count: %d\n\n", sum(resultDynamicProgramming));

            int[] resultGreedy = change_greedy(n, denominations);
            System.out.println("Greedy algorithm results");
            System.out.printf("Amount: %d\n", n);
            System.out.printf("Optimal distribution: %s\n", format(denominations, resultGreedy));
            System.out.printf("Optimal coin count: %d\n\n", sum(resultGreedy));
        }
        System.out.println("Thanks for playing. Good Bye.");
    }

    private static String format(int[] denominations, int[] counts){
        int n = counts.length;
        if(n==0 || denominations.length!=counts.length){
            return "Error";
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            if(counts[i]!=0){
                if(sb.length()>0){
                    sb.append(" + ");
                }
                sb.append(counts[i]).append('*').append(denominations[i]).append('c');
            }
        }
        return sb.toString();
    }

    private static int sum(int[] arr){
        int total = 0;
        for(int i: arr) total+=i;
        return total;
    }

    public static int[] change_greedy(int n, int[] d){
        int[] res = new int[d.length];
        for(int i=0; i<d.length; i++){
            res[i] = n/d[i];
            n -= d[i]*res[i];
        }
        return res;
    }

    public static int[] change_dP(int n, int[] d) {
		int k = d.length;
		int[] c = new int[n+1];
		int[] a = new int[n+1];
		for (int j = 1; j < n+1; j++) {
			int min = Integer.MAX_VALUE;
			int savedI = -1;
			for (int i = 0; i < k; i++) {
				if ((j - d[i]) < 0) {
					continue;
				} else {
					
					if (c[j-d[i]] < min) {
						min = c[j-d[i]];
						savedI = i;
					}
				}
			}
			c[j] = 1 + min;
			a[j] = savedI;
		}
		int[] b = getSolution(a, n, d);
		return b;
	}
