package myproject.congestiontax.service;

import myproject.congestiontax.model.CityTaxConfiguration;
import myproject.congestiontax.model.TaxRequest;
import myproject.congestiontax.model.TaxResponse;
import myproject.congestiontax.model.Vehicle;
import myproject.congestiontax.util.CongestionTaxCalculator;
import myproject.congestiontax.util.CongestionTaxConfigLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CongestionTaxService: service to handle tax calculation from REST API
 */
@Service
public class CongestionTaxService {

    @Autowired
    CongestionTaxConfigLoader congestionTaxConfigLoader;

    @Autowired
    CongestionTaxCalculator congestionTaxCalculator;

    /**
     * Handle tax calculation
     * @param taxRequest taxRequest from api
     * @param name city name
     * @return taxResponse
     */
    public TaxResponse calculateTax(TaxRequest taxRequest, String name)  {
        CityTaxConfiguration cityTaxConfiguration = congestionTaxConfigLoader.getConfig(name);
        Vehicle vehicle = congestionTaxCalculator.getVehicle(taxRequest.getVehicle());
        return new TaxResponse(taxRequest.getVehicle(), congestionTaxCalculator.getTax(vehicle, taxRequest.getDateEntries() , cityTaxConfiguration));
    }
}
