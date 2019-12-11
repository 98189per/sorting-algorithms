import java.util.function.Function;
import java.util.Random;
public class FunctionTimer {
    String name;
    Function<int[], int[]> sort;
    long startTime, endTime;
    int iterations;
    int[] sizes, ranges;
    void setValues(int iterations, int[] sizes, int[] ranges){
        this.iterations = iterations;
        this.sizes = sizes;
        this.ranges = ranges;
    }
    int[] generate(int size, int range){
        Random random = new Random();
        int[] array = new int[size];
        for(int i=0; i<size; i++){
            array[i] = random.nextInt(range) + 1;
        }
        return array;
    }
    double time(int size, int range) {
        int[] array = generate(size, range);
        startTime = System.nanoTime();
        sort.apply(array);
        endTime = System.nanoTime();
        return (double)(endTime-startTime)/1000000;
    }
    void time(String name, Function<int[], int[]> sort){
        this.name = name;
        this.sort = sort;
        for(int i : sizes){
            for(int j : ranges){
                double total = 0;
                for(int k=0; k<iterations; k++){
                    total += time(i,j);
                }
                System.out.println(name + " : " + (double)total/iterations + " ms[avg] (size - " + i + ", range - " + j + ")");
            }
        }
    }
}
