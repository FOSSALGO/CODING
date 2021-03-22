package fosalgo;

import java.util.Scanner;
import java.util.Vector;

public class CraftingCode033 {

    public static void main(String[] args) {
        Vector<Integer> vData = new Vector<>();
        
        Scanner sc = new Scanner(System.in);
        
        String sInput = null;
        
        while(sc.hasNextInt() && !(sInput=sc.next()).equals("EOF")){
            int value = Integer.parseInt(sInput);
            vData.add(value);
        }
        
        System.out.println("vData: "+vData);
    }
    
}
