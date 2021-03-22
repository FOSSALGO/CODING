package fosalgo;

import java.util.Vector;

public class CraftingCode034 {
    
    public static void main(String[] args) {
        Vector<Integer> data = new Vector<>();
        data.add(40);
        data.add(2);
        data.add(1);
        data.add(43);
	data.add(3);
        data.add(65);
        data.add(0);
        data.add(-1);        
        data.add(68);
        data.add(3);
        data.add(42);
        data.add(4); 
        
        System.out.println("Original data: "+data);
        
        selectionSort(data);
        System.out.println("Sorted data: "+data);
        
        
    }
    
    static void tukarNilai(Vector<Integer> vector, int indexA, int indexB){
        if(indexA>=0&&indexA<vector.size()&&indexB>=0&&indexB<vector.size()&&indexA!=indexB){
            int A,B,C;
            A = vector.get(indexA);
            B = vector.get(indexB);
            C = A;
            A = B;
            B = C;
            vector.set(indexA, A);
            vector.set(indexB, B);
        }
    }
    
    static void selectionSort(Vector<Integer> vector){
        int n = vector.size();
        for(int i=0; i<n-1;i++){
            int jMAX = i;
            for(int j=i+1;j<n;j++){
                if(vector.get(j)>vector.get(jMAX)){
                    jMAX = j;
                }
            }
            if(jMAX != i){
                tukarNilai(vector, i, jMAX);
            }
        }
    }
    
}
