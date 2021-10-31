package service;

import util.Helper;
import validator.CommandValidator;
import validator.InvalidOperationException;

import java.util.Arrays;
import java.util.List;

public class CommandService {
    private static final List<String> commandList = Arrays.asList("create_product", "get_product_info", "create_order", "create_campaign","get_campaign_info","increase_time");

    public void Execute(String input) {
        String command = Helper.getCommandName(input);
        List<String> optionList = Helper.getCommandOptions(input);

        ICrudService operator = null;
        if (command.contains("product")) {
            operator = new ProductService();
        } else if (command.contains("order")) {
            operator = new OrderService();
        } else if (command.contains("campaign") || command.contains("increase") ) {
            operator = new CampaignService();
        }

        if (operator == null) {
            return;
        }

        if (command.contains("create")) {
            operator.create(optionList);
        } else if (command.contains("get")) {
            operator.get(optionList.get(0));
        } else if (command.contains("increase")) {
            CampaignService cs = new CampaignService();
            cs.increaseTime(optionList.get(0));
            cs.ReCalculate(optionList.get(0));
        }
    }

    public void Validate(String input) throws InvalidOperationException {
        CommandValidator validator = new CommandValidator();
        validator.ValidateCommandName(input, commandList);
        validator.ValidateOptions(input);
    }
}
