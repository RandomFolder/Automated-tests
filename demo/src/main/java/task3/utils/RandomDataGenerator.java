package task3.utils;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class RandomDataGenerator
{
    public static String generateRandomStringFromAlphabet(int length, String alphabet)
    {
        String result = "";

        for (int i = 0; i < length; i++)
        {
            result += alphabet.charAt((new Random()).nextInt(alphabet.length()));
        }

        return result;
    }


    public static String generateRandomStringFromAlphabets(int length, String... alphabets)
    {
        String result = "";

        for (String str : alphabets)
        {
            result += str.charAt((new Random()).nextInt(str.length()));
        }

        int currentResultLength = result.length();

        for (int i = 0; i < length - currentResultLength; i++)
        {
            result += (new Random()).nextInt(length);
        }

        return result;
    }


    public static int generateRandomNumber(int boundingValue)
    {
        int number = (new Random()).nextInt(boundingValue);

        return number;
    }


    public static int[] generateRandomSequence(int count, int max)
    {
        Random random = new Random();
        Set<Integer> numbers = new LinkedHashSet<>();

        while (numbers.size() < count)
        {
            Integer newNumber = random.nextInt(max);
            numbers.add(newNumber);
        }

        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
