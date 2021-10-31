package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {

    public static String getCommandName(String input) {
        String result = "";
        List<String> parameterList = new ArrayList<>(Arrays.asList(input.split("\\s+")));
        if (parameterList != null && parameterList.size() > 0) {
            result = parameterList.get(0);
        }
        return result;
    }

    public static List<String> getCommandOptions(String input) {
        List<String> result = new ArrayList<>(Arrays.asList(input.split("\\s+")));
        if (result != null && result.size() > 0) {
            result.remove(0);
        }
        return result;
    }

    public static Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static List<String> ReadFile(String path)  {
        List<String> inputList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                inputList.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Input file not found!" + ex.getMessage());
        } catch (IOException e) {
            System.out.println("Error occurred while reading file : " + e.getMessage());
        }

        return inputList;
    }
}
