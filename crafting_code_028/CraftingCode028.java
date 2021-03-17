
import java.util.Scanner;

public class CraftingCode028 {
    public static void main(String[] args) {
        boolean running = true;
        while(running == true){
            Scanner sc = new Scanner(System.in);
            String kata = sc.nextLine();
            System.out.println("Anda memasukkan kata: "+kata);
            if(kata.equals("END")){
                System.out.println("Looping terhenti");
                running = false;
            }
        }
    }
}
