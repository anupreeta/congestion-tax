package myproject.congestiontax.service;

import myproject.congestiontax.model.TaxRequest;
import myproject.congestiontax.model.TaxResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class CongestionTaxServiceTests {
    private static final String CITY = "Gothenburg";

    @Autowired
    CongestionTaxService congestionTaxService;

    @Test
    public void testCalculateTaxEmptyDaySuccess() {
        TaxResponse taxResponse = congestionTaxService.calculateTax(new TaxRequest("Car", new ArrayList<>()), CITY);
        assertNotNull(taxResponse);
        assertEquals(taxResponse.getVehicle(), "Car");
        assertEquals(taxResponse.getTaxAmount(), 0);
    }

    @Test
    public void testCalculateTaxNonEmptyDaySuccess() {
        TaxResponse taxResponse1 = congestionTaxService.calculateTax(new TaxRequest("Car", Arrays.asList("2013-01-14 21:00:00")), CITY);
        assertNotNull(taxResponse1);
        assertEquals(taxResponse1.getVehicle(), "Car");
        assertEquals(taxResponse1.getTaxAmount(), 0);

        TaxResponse taxResponse2 = congestionTaxService.calculateTax(new TaxRequest("Car", Arrays.asList("2013-01-14 07:10:00")), CITY);
        assertNotNull(taxResponse2);
        assertEquals(taxResponse2.getVehicle(), "Car");
        assertEquals(taxResponse2.getTaxAmount(), 18);
    }
}
