package task3.utils;

import java.util.ArrayList;

public class DataHandler
{
    public static boolean isIntsArraySorted(ArrayList<Integer> array)
    {
        for (int i = 0; i < array.size() - 1; i++)
        {
            if (array.get(i) > array.get(i + 1))
            {
                return false;
            }
        }

        return true;
    }
}
