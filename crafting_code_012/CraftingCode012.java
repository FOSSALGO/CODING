import java.util.Scanner;

public class CraftingCode012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("INPUT");
        String kalimatku = sc.nextLine();
        System.out.println("OUTPUT");
        
        //CARA PERTAMA
        for(int i=0;i<kalimatku.length();i++){
            for(int j=0;j<=i;j++){
                System.out.print(kalimatku.charAt(j));
            }
            System.out.println();
        }
        
        //CARA KEDUA
        for(int i=0;i<kalimatku.length();i++){
            System.out.println(kalimatku.substring(0, i+1));
        }
    }
}
