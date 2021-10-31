package validator;

import util.Helper;

import java.util.List;

public class CommandValidator {

    public void ValidateCommandName(String input, List<String> commandList) throws InvalidOperationException {
        String commandName = Helper.getCommandName(input);
        if (commandName.isEmpty() || !commandList.contains(commandName)) {
            throw new InvalidOperationException("Unknown command : " + commandName);
        }
    }

    public void ValidateOptions(String input) throws InvalidOperationException {
        List<String> optionList = Helper.getCommandOptions(input);
        if (optionList == null) {
            throw new InvalidOperationException("Empty command options!");
        } else {
            boolean isValid = true;
            String command = Helper.getCommandName(input);
            if (command.contains("create")) {
                if (command.contains("product")) {
                    isValid = optionList.size() == 3
                            && Helper.tryParseInt(optionList.get(1)) != null
                            && Helper.tryParseInt(optionList.get(2)) != null;
                } else if (command.contains("order")){
                    isValid = optionList.size() == 2 && Helper.tryParseInt(optionList.get(1)) != null;
                } else if (command.contains("campaign")){
                    isValid = optionList.size() == 5
                            && Helper.tryParseInt(optionList.get(2)) != null
                            && Helper.tryParseInt(optionList.get(3)) != null
                            && Helper.tryParseInt(optionList.get(4)) != null;
                } else {
                    isValid = false;
                }
            } else if (command.contains("get")) {
                if (command.contains("product")) {
                    isValid = optionList.size() == 1;
                } else if (command.contains("campaign")){
                    isValid = optionList.size() == 1;
                } else {
                    isValid = false;
                }
            } else if (command.contains("increase")) {
                isValid = optionList.size() == 1  && Helper.tryParseInt(optionList.get(0)) != null;
            } else {
                isValid = false;
            }

            if (!isValid) {
                throw new InvalidOperationException("Invalid command options : " + input);
            }
        }
    }
}
