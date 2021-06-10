package fosalgo;

import java.util.Scanner;

public class CraftingCode_042 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int MIN = a;
        int MAX = b;
        if (a > b) {
            MIN = b;
            MAX = a;
        }
        
        int sum = 0;
        for(int i=MIN; i<=MAX; i++){
            if(i>MIN){
                System.out.print(" + ");
            }
            System.out.print(i);
            sum += i;//operasi increment 
        }
        double nilaiRataRata = (double)sum / (MAX-MIN+1);
        System.out.println(" = "+sum); 
        System.out.println("Nilai Rata-Rata = "+nilaiRataRata);
    }
}
