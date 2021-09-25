package fosalgo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CraftingCode_044 {

    public static void main(String[] args) {
        String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco";
        String url = "berita.txt";
        
        CraftingCode_044 cc44 = new CraftingCode_044();
        
        boolean sts = cc44.tulisFile(url, content);
        System.out.println(sts);
        
        String isinya = cc44.bacaFile(url);
        System.out.println("Isi File: "+isinya);
    }

    public String bacaFile(String url) {
        String isiFile = null;
        try {
            FileReader fr = new FileReader(url);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String baris;
            while ((baris = br.readLine()) != null) {
                sb.append(baris);
            }
            br.close();
            fr.close();
            isiFile = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isiFile;
    }

    public boolean tulisFile(String url, String isiFile) {
        boolean status = false;
        try {
            FileWriter fw = new FileWriter(url);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(isiFile);
            bw.close();
            fw.close();
            status = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }
}
