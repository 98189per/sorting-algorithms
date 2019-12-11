import java.util.Arrays;

public class SortingFunctions {
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
