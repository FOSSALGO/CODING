package fosalgo;

public class CraftingCode029 {

    public static void main(String[] args) {

        MataKuliah[] mataKuliah = {
            new MataKuliah("INF213", "Pemrograman Dasar", 2, 4),
            new MataKuliah("INF214", "Algoritma dan Struktur Data", 3, 3),
            new MataKuliah("INF215", "Pemrograman Terstruktur", 3, 2),
            new MataKuliah("INF216", "Pemrograman Berorientasi Objek", 4, 4),
            new MataKuliah("INF217", "Pengolahan Data Citra", 2, 2)
        };
        
        for(MataKuliah m:mataKuliah){
            System.out.println(m.toString());
        }
        
        //olah data
        double totalSKS = 0;
        double totalSKSxNilai = 0;
        for(int i=0;i<mataKuliah.length;i++){
            totalSKS = totalSKS + mataKuliah[i].sks;
            double sksXNilai = mataKuliah[i].sks * mataKuliah[i].nilai;
            totalSKSxNilai = totalSKSxNilai + sksXNilai;
        }
        double ips = totalSKSxNilai/totalSKS;
        System.out.println("Total SKS yang diprogram = "+totalSKS);
        System.out.println("Total SKS x Nilai        = "+totalSKSxNilai);
        System.out.println("IPS: "+ips);

    }
}

class MataKuliah {

    String kode;
    String nama;
    int sks;
    int nilai;

    public MataKuliah(String kode, String nama, int sks, int nilai) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.nilai = nilai;
    }
    
    public String toString(){
        return "["+this.kode+"] "+this.nama+"; "+this.sks+" SKS; Nilai: "+this.nilai;        
    }

}
