package fosalgo;

import java.util.Scanner;

public class CraftingCode_043 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("input n: ");
        int n = sc.nextInt();
        System.out.print("input m: ");
        int m = sc.nextInt();
        
        //baris-0
        System.out.print("+");
        for (int j = 0; j < m; j++) {
            System.out.print(" -- +");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            //baris-1
            System.out.print("|");
            for (int j = 0; j < m; j++) {
                System.out.print("    |");
            }
            System.out.println();
            
            //baris-2
            System.out.print("+");
            for (int j = 0; j < m; j++) {
                System.out.print(" -- +");
            }
            System.out.println();
        }
    }
}
