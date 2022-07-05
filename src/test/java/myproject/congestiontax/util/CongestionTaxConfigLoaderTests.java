package myproject.congestiontax.util;

import myproject.congestiontax.model.CityTaxConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class CongestionTaxConfigLoaderTests {

    private static final String CITY = "Gothenburg";

    @Test
    public void testLoadConfigSuccess() {
        CongestionTaxConfigLoader congestionTaxConfigLoader = new CongestionTaxConfigLoader();
        CityTaxConfiguration config = congestionTaxConfigLoader.getConfig(CITY);
        assertNotNull(config);
        assertEquals(config.getCityName(), CITY);
    }
}
