import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CandidateCode {
    public static List<String> printAllSubSequences(int[] arrInput, List<String> list) {
        list = new ArrayList<>();

        int[] temp = new int[arrInput.length];

        int index = 0;

        solve(arrInput, index, temp, list);
        return list;

    }


    private static void solve(int[] arrInput, int index, int[] temp, List<String> list) {

        if (index == arrInput.length) {

            print(arrInput, temp, list);

            return;

        }

        //set the current index bit and solve it recursively

        temp[index] = 1;

        solve(arrInput, index + 1, temp, list);

        //unset the current index bit and solve it recursively

        temp[index] = 0;

        solve(arrInput, index + 1, temp, list);

    }


    private static void print(int[] arrInput, int[] temp, List<String> list) {

        String result = "";

        for (int i = 0; i < temp.length; i++) {

            if (temp[i] == 1)

                result += arrInput[i] + " ";

        }

       /* if(result=="")

            result = "{Empty Set}";*/
        list.add(result);


    }

    public static void main(String[] args) {
        long count = 0;
        List<Integer> fList = new ArrayList<>();
        List<Long> lList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        int noOfTestCases = sc.nextInt();
        sc.nextLine();

        for (int q = 0; q < noOfTestCases; q++) {
            String input = sc.nextLine();
            String[] inputSeperated = input.split(" ");
            int lower = Integer.parseInt(inputSeperated[0]);
            int upper = Integer.parseInt(inputSeperated[1]);

            for (int i = lower; i <= upper; i++) {
                String[] array = String.valueOf(i).split("");
                Integer[] integers = Arrays.stream(array).mapToInt(s -> Integer.parseInt(s)).boxed().toArray(Integer[]::new);
                int[] intstream = Arrays.stream(integers).flatMapToInt(s -> IntStream.of(s)).toArray();
                List<String> li = new ArrayList<>();
                li = printAllSubSequences(intstream, list);
                List<String> modifiedList = new ArrayList<>();
                for (int k = 0; k < li.size(); k++) {
                    if (li.get(k) == "") {
                        k++;
                    } else
                        modifiedList.add(li.get(k));
                }

                int sum = modifiedList.stream().map(s -> s.replaceAll("\\s+", "").trim()).mapToInt(s -> Integer.parseInt(s)).sum();

                fList.add(sum);

            }

            count = fList.stream().filter(s -> s % 2 == 0).count();
            fList = new ArrayList<>();
            lList.add(count);


        }


        lList.forEach(s -> System.out.println(s));

    }


}
