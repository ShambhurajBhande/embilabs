import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CheeseEatenProblem {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int test_cases = Integer.parseInt(bufferedReader.readLine());
            while (test_cases > 0) {
                int cheese_count = Integer.parseInt(bufferedReader.readLine());
                String[] cheeseStrArr = bufferedReader
                        .readLine()
                        .split(" ");
                Integer[] cheeseWeights = Arrays
                        .stream(cheeseStrArr)
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                System.out.println(maxCheeseWeight(cheeseWeights));
                test_cases--;
            }


        } catch (Exception exception) {
            System.out.println("Invalid input!");
            exception.printStackTrace();
        }
    }

    private static int maxCheeseWeight(Integer[] cheeseWeight) {
        Integer[][] cheeseDp=new Integer[cheeseWeight.length][2];
        return Math.max(maxCheese(cheeseWeight, 0, false,cheeseDp),
                maxCheese(cheeseWeight, 0, true, cheeseDp));
    }
    private static int maxCheese(Integer[] cheeseWeights,int index,boolean previousEaten,final Integer[][] cheeseDp){
        if(index==cheeseWeights.length){
            return 0;
        }

        //if value is already calculated return it from collection
        int preIndex=previousEaten?1:0;
        if (cheeseDp[index][preIndex]!=null) {
            return cheeseDp[index][preIndex];
        }

        // if previous block is eaten we cannot eat current cheese block
        if (previousEaten) {
            cheeseDp[index][1]=maxCheese(cheeseWeights, index + 1, false, cheeseDp);
            return cheeseDp[index][1];
        }

        // Choice diagram if previous block is not eaten mouse can eat or skip
        cheeseDp[index][0]=Math.max(maxCheese(cheeseWeights, index + 1, true, cheeseDp) + cheeseWeights[index],
                maxCheese(cheeseWeights, index + 1, false, cheeseDp));
        return cheeseDp[index][0];
    }
}

