import java.util.Scanner;

public class CraftingCode002 {
    public static void main(String[]scw){
        System.out.print("Masukkan TEXT: ");
        Scanner sc = new Scanner(System.in);
        //coba baca input dari keyboard sebagai nilai String
        String str = sc.nextLine();
        
        //tampilkan text yang diinput melalui keyboard
        System.out.println("TEXT yang diketikkan: "+str);
    }
}
