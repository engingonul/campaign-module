package service;

import model.Campaign;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class CampaignService extends SuperService implements ICrudService<Campaign>{

    @Override
    public Campaign get(String name) {
        String infoMessage = "Campaign " + name + " could not be found.";
        Campaign campaign = null;
        if (campaignList != null && campaignList.size() > 0) {
            campaign = getCampaign(name);
        }
        if (campaign != null) {
            infoMessage = campaign.toString();
        }

        System.out.println("INFO : " + infoMessage);

        return campaign;
    }

    @Override
    public Campaign create(List<String> optionList) {
        if (campaignList == null) {
            campaignList = new ArrayList<>();
        }

        String name = optionList.get(0);
        String productCode = optionList.get(1);
        int duration = Integer.parseInt(optionList.get(2));
        int limit = Integer.parseInt(optionList.get(3));
        int targetSaleCount = Integer.parseInt(optionList.get(4));
        Campaign campaign = getCampaign(name);
        if (campaign == null) {
            Product product = getProduct(productCode);
            if (product == null) {
                System.out.println("Product " + productCode + " could not be found!");
            } else {
                campaign = new Campaign(name, product, duration, limit, targetSaleCount);
                campaignList.add(campaign);
                System.out.println("CREATE : Campaign [name=" + campaign.getName()
                                + ", product=" + campaign.getProduct().getCode()
                                + ", duration=" + campaign.getDuration()
                                + ", limit=" + campaign.getPmLimit()
                                + ", targetSales=" + targetSaleCount + "]");
            }
        } else {
            System.out.println("CREATE : There is already a campaign named " + name);
        }

        return campaign;
    }

    private Campaign getCampaign(String name) {
        Campaign campaign = null;
        if (campaignList != null && campaignList.size() > 0) {
            campaign = campaignList.stream()
                    .filter(p -> p.getName().equals(name))
                    .findFirst()
                    .orElse(null);
        }

        return campaign;
    }

    public void increaseTime(String hour) {
        int hoursPassed = Integer.parseInt(hour);
        time += hoursPassed;

        for (Campaign campaign : campaignList) {
            if (!campaign.isActive()) {
                continue;
            }
            campaign.setRemainingHours(campaign.getRemainingHours() - hoursPassed);
        }

        System.out.println("Time is " + time + ":00" );
    }

    public void ReCalculate(String hour) {
        int hoursPassed = Integer.parseInt(hour);

        for (Campaign campaign : campaignList) {
            // set state
            if (campaign.getRemainingHours() <= 0) {
                campaign.setActive(false);
                campaign.getProduct().setPrice(campaign.getInitialPrice());
                continue;
            }

            // set svg price
            Product currentProduct = campaign.getProduct();
            if (campaign.getTotalSaleCount() > 0) {
                campaign.setAvgPrice(currentProduct.getTotalSum() / campaign.getTotalSaleCount());
            }

            // set price
            currentProduct.setPrice(currentProduct.getPrice() - (5 * hoursPassed));
            double priceChangePct = (Math.abs(campaign.getInitialPrice()- currentProduct.getPrice()) * 100 / campaign.getInitialPrice());
            if (campaign.getPmLimit() < priceChangePct) {
                currentProduct.setPrice(campaign.getInitialPrice() - (campaign.getInitialPrice() * campaign.getPmLimit() / 100));
            }

        }
    }
}
