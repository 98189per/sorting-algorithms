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
    static int[] radixSort(int[] array, int maxDigits){
        //Instead of using string conversions
        //You can divide by a power of 10 and use modulus to get digits
        //It is far superior resource-wise
        //And makes more sense anyway
        //Idiot
        return array;
    }
    static void timeFunction(int[] arr, int range){
        System.out.println();
        double mil = 1000000.00;
        long startTime = System.nanoTime();//start timer
        sortArray(arr, range);
        long endTime = System.nanoTime();//end timer
        System.out.println("Shitty Radix Sort (String Conversion): " + (double)(endTime-startTime)/mil + " milliseconds");
        startTime = System.nanoTime();//start timer
        Arrays.sort(arr);
        endTime = System.nanoTime();//end timer
        for(int num : arr){System.out.print(num + " ");}
        System.out.println();
        System.out.println("Quicksort (Dual-Pivot): " + (double)(endTime-startTime)/mil + " milliseconds");
    }
    public static void main(String[] args){
        int range = 9999;
        int size = 9999;
        int[] arr = makeArray(size, range);
        for(int num : arr){System.out.print(num + " ");}
        System.out.println();
        int[] sorted = sortArray(arr,Integer.toString(range).length());
        for(int num : sorted){System.out.print(num + " ");}
        timeFunction(arr,Integer.toString(range).length());
    }
}
