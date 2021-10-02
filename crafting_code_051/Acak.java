package fosalgo;

public class Acak {
    
    static int acakInt(int min, int max) {
        if(min>max){
            int temp = min;
            min = max;
            max = temp;
        }
        return (int) (min + Math.random() * (max - min + 1));
    }
    
    static float acakFloat(float min, float max) {
        if(min>max){
            float temp = min;
            min = max;
            max = temp;
        }
        return (float) (min + (max - min) * Math.random());
    }
    
    static double acakDouble(double min, double max) {
        if(min>max){
            double temp = min;
            min = max;
            max = temp;
        }
        return min + (max - min) * Math.random();
    }
}
