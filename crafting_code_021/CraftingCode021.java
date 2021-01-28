package fosalgo;

import java.util.Scanner;

public class CraftingCode021 {

    public static void main(String[] args) {
        String[][] db = {
            {"Alfonso", "kandacong"},
            {"Iwan", "karokaro"},
            {"Meo", "creamy"},
            {"Herborist", "naturalScen120"},
            {"Acang Polo", "awrftpq"}
        };

        boolean berhasil = false;

        while (!berhasil) {
            Scanner sc = new Scanner(System.in);
            System.out.println("INPUT");
            System.out.print("username: ");
            String username = sc.nextLine();
            System.out.print("password: ");
            String password = sc.nextLine();

            int index = -1;
            for (int i = 0; i < db.length; i++) {
                //cari username di list
                if (username.equals(db[i][0])) {
                    //usename ditemukan
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                //username terdaftar
                if (password.equals(db[index][1])) {
                    berhasil = true;
                    System.out.println("Login Berhasil");
                } else {
                    System.out.println("password salah");
                }
            } else {
                //jika index tetap = -1
                //maka user tidak terdaftar
                System.out.println("username: " + username + " tidak terdaftar");
            }
        }
    }
}
