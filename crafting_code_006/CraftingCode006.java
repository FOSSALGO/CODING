import java.util.Scanner;
public class CraftingCode006 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("INPUT");
        int n = sc.nextInt();
        System.out.println("OUTPUT");
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){//looping for untuk menuliskan setiap baris bintang sebanyak i di tiap barisnya
                System.out.print("* ");
            }
            System.out.println();//buat baris barus jika baris bintang telah selesai ditampilkan
        }
    }
}
