import java.util.Arrays;
import java.util.Random;
import java.util.function.DoubleToIntFunction;

public class arraysPractice {
    static int[] makeArray(int size, int range){
        Random random = new Random();
        int[] array = new int[size];
        for(int i=0;i<array.length;i++){
            array[i] = random.nextInt(range) + 1;
        }
        return array;
    }
    static int maxDigits(int[] array){
        int maxDigits = 0;
        for(int num : array){
            if(Integer.toString(num).length()>maxDigits)
                maxDigits = Integer.toString(num).length();
        }
        return maxDigits;
    }
    static int[] sortArray(int[] array, int maxDigits){
        String[] bins = {"","","","","","","","","",""};
        int[] returnArray = array.clone();
        for(int i=1;i<=maxDigits;i++) {
            for(int num : returnArray) {
                if (Integer.toString(num).length()>=i)
                    bins[Integer.parseInt(Integer.toString(num).split("")[Integer.toString(num).length()-i])] += " " + num;
                else
                    bins[0] += " " + num;
            }
            String temp = "";
            for(int j=0;j<10;j++){
                temp += bins[j];
                bins[j] = "";
            }
            for(int j=0;j<returnArray.length;j++){
                returnArray[j] = Integer.parseInt(temp.split(" ")[j+1]);
            }
        }
        return returnArray;
    }
    static int[] radixSort(int[] array, int length, int maxDigits){
        int e = 1;
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
    static void timeFunction(int[] arr, int range){
        double mil = 1000000.00;
        int[] arr2 = arr.clone();
        int size = arr2.length;
        long startTime, endTime;
        /*startTime = System.nanoTime();//start timer
        sortArray(arr, range);
        endTime = System.nanoTime();//end timer
        System.out.println("Shitty Radix Sort (String Conversion): " + (double)(endTime-startTime)/mil + " milliseconds");*/
        startTime = System.nanoTime();//start timer
        Arrays.sort(arr);
        endTime = System.nanoTime();//end timer
        //for(int num : arr){System.out.print(num + " ");}
        System.out.println();
        System.out.println("Quicksort (Dual-Pivot): " + (double)(endTime-startTime)/mil + " milliseconds");
        startTime = System.nanoTime();//start timer
        radixSort(arr2,size,range);
        endTime = System.nanoTime();//end timer
        //for(int num : arr2){System.out.print(num + " ");}
        System.out.println();
        System.out.println("New Radix Sort (fingers crossed): " + (double)(endTime-startTime)/mil + " milliseconds");
    }
    public static void main(String[] args){
        int[] ranges = {20, 200, 2000, 20000, 200000, 2000000};
        int[] sizes = {20, 200, 2000, 20000, 200000, 2000000};
        //int[] arr = makeArray(size, range);
        //for(int num : arr){System.out.print(num + " ");}
        System.out.println();
        System.out.println("Unsorted Array ^");
        /*int[] sorted = sortArray(arr,Integer.toString(range).length());
        for(int num : sorted){System.out.print(num + " ");}*/
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++) {
                System.out.println("\nArray size: " + sizes[i] + ", Value range: " + ranges[j]);
                int[] arr = makeArray(sizes[i], ranges[j]);
                timeFunction(arr, Integer.toString(ranges[j]).length());
            }
        }
    }
}
