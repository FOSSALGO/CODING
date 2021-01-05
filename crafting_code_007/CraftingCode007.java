import java.util.Scanner;

public class CraftingCode007 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("INPUT");
        int n = sc.nextInt();
        System.out.println("OUTPUT");
        //memulai menulis bintang di dalam looping for
        for(int i=1;i<=n;i++){//iterasi baris
            for(int j=0;j<(n-i);j++){//iterasi untuk spasi
                System.out.print("  ");
            }
            for(int j=1;j<=i;j++){//iterasi kolom untuk menampilkan simbol bintang sebanyak i
                System.out.print("* ");
            }
            System.out.println();
        }
        
    }
}
