import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class FunctionTester {
    public static class FunctionTimer {
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
        double timeFunction(int size, int range) {
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
                        total += timeFunction(i,j);
                    }
                    System.out.println(name + " : " + (double)total/iterations + " ms[avg] (size - " + i + ", range - " + j + ")");
                }
            }
        }
    }
    public static class SortingFunctions {
        int[] radixSort(int[] array){
            int e = 1;
            int length = array.length;
            int maxDigits = 0;
            for(int i=0; i<length; i++) {
                int temp = Integer.toString(array[i]).length();
                if (temp > maxDigits)
                    maxDigits = temp;
            }
            for(int i=1;i<=maxDigits;i++){
                int j = 0;
                int[][] bins = new int[10][length];
                for(int num : array){
                    bins[(num/e)%10][j] = num;
                    j++;
                }
                j = 0;
                for(int[] bin : bins){
                    for(int num : bin){
                        if(num!=0){
                            array[j] = num;
                            j++;
                        }
                    }
                }
                e*=10;
            }
            return array;
        }
        int[] arraySort(int[] array){
            Arrays.sort(array);
            return array;
        }
    }
    public static void main(String ... args){
        SortingFunctions functions = new SortingFunctions();
        FunctionTimer functionTimer = new FunctionTimer();
        functionTimer.setValues(1000, new int[]{10000},new int[]{10000});
        functionTimer.time("radix sort",functions::radixSort);
        functionTimer.time("arrays.sort",functions::arraySort);
    }
}
