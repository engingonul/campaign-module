import service.CommandService;
import util.Helper;
import validator.InvalidOperationException;

import java.util.List;

public class Application {

    public static void main(String[] args)  {

        List<String> inputList = Helper.ReadFile("./input.txt");
        CommandService commandService = new CommandService();
        for (String input : inputList) {
            try {
                commandService.Validate(input);
                commandService.Execute(input);
            } catch (InvalidOperationException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Error occurred while executing command : " + ex.getMessage());
            }
        }

    }
}
