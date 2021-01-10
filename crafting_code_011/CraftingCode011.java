import java.util.Scanner;

public class CraftingCode011 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("INPUT");
        
        System.out.print("Masukkan String: ");
        String stringKu = sc.nextLine();
        
        System.out.print("Masukkan indek : ");
        int index = sc.nextInt();
        char karakter = stringKu.charAt(index);
        System.out.println("OUTPUT");
        System.out.print("Karakter pada index="+index+" adalah karakter ");
        System.out.println(karakter);
    }
}
