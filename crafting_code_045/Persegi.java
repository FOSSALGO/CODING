package fosalgo;

public class Persegi {
    
    String warna;
    double sisi, keliling, luas;

    public Persegi(String warna, double sisi) {
        this.warna = warna;
        this.sisi = sisi;
        this.keliling = 4 * sisi;
        this.luas = sisi * sisi;
    }

    @Override
    public String toString() {
        String values = "";
        values += "Warna   : "+this.warna+"\n";
        values += "Sisi    : "+this.sisi+" cm\n";
        values += "Keliling: "+this.keliling+" cm\n";
        values += "Luas    : "+this.luas+" cm2\n";
        return values;
    }  
}
