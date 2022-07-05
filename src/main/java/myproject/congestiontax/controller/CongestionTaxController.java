package myproject.congestiontax.controller;

import myproject.congestiontax.model.TaxRequest;
import myproject.congestiontax.model.TaxResponse;
import myproject.congestiontax.service.CongestionTaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * CongestionTaxController : Api end point for Congestion Tax
 */
@RestController
@CrossOrigin ("*")
public class CongestionTaxController {

    private static final Logger logger = LoggerFactory.getLogger(CongestionTaxController.class);

    @Autowired
    CongestionTaxService congestionTaxService;

    /**
     * Post /api/calculateTax
     * @param taxRequest taxRequest
     * @param name city config name
     * @return taxResponse
     */
    @PostMapping(value = "/api/calculateTax", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaxResponse> calculateTax(@RequestBody TaxRequest taxRequest, @RequestParam String name) {
        long start = new Date().getTime();
        logger.debug("Incoming request started at: {}", start);
        TaxResponse result = congestionTaxService.calculateTax(taxRequest, name);
        long end = new Date().getTime();
        logger.debug("Incoming request ended at: {}", end);
        logger.info("Processing time {} ms", end - start);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
