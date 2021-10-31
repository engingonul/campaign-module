import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CampaignService;
import service.CommandService;
import service.OrderService;
import service.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ServiceTest {

    CommandService service;

    @BeforeEach
    void setUp() {
        service = new CommandService();
    }

    @Test
    public void testCreateProduct() {
        String code = "P1";
        ProductService service = new ProductService();
        createProduct(code, service);

        assertNotNull(service.get(code));
    }

    private Product createProduct(String code, ProductService service) {
        List<String> optionList = Arrays.asList(code, "100", "1000");
        return service.create(optionList);
    }

    @Test
    public void testCreateOrder() {
        String code = "P1";
        createProduct(code, new ProductService());

        List<String> optionList = Arrays.asList(code, "10");
        OrderService service = new OrderService();
        service.create(optionList);

        assertNotNull(service.get(code));
    }

    @Test
    public void testCreateCampaign() {
        createProduct("P1", new ProductService());

        String name = "C1";
        List<String> optionList = Arrays.asList(name, "P1", "10", "20", "100");
        CampaignService service = new CampaignService();
        service.create(optionList);

        assertNotNull(service.get(name));
    }

}
