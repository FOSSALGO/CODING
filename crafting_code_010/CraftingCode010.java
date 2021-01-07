import java.util.Scanner;

public class CraftingCode010 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("INPUT");
        String myStr = scn.nextLine();
        int beginIndex = scn.nextInt();
        int endIndex = scn.nextInt();
        //String subStr = myStr.substring(beginIndex);
        String subStr = myStr.substring(beginIndex, endIndex);
        System.out.println("OUTPUT");
        System.out.println("string input: "+myStr);   
        System.out.println("beginIndex  : "+beginIndex);
        System.out.println("endIndex    : "+endIndex);
        System.out.println("substring   : "+subStr);
    }
}
