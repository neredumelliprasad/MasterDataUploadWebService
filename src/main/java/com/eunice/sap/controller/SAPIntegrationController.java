package com.eunice.sap.controller;

import com.eunice.sap.model.RawProductData;
import com.eunice.sap.service.ProductMasterDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestinationProperties;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/masterdata")
public class SAPIntegrationController {

    @Autowired
    HttpDestinationProperties properties;

    @Autowired
    ProductMasterDataService productMasterDataService;

    private static final Logger LOG = LoggerFactory.getLogger(SAPIntegrationController.class);
    private static final ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Product> processData() throws Exception
    {
        return productMasterDataService.getProductTop10();
    }

    @RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity processData(@RequestBody List<RawProductData> rawProductDataList) throws Exception
    {
        LOG.info("Input: "+mapper.writeValueAsString(rawProductDataList));
        //Set<Product> products = productMasterDataService.constructProductData(rawProductDataList);
        //return productMasterDataService.persistProducts(products);
        return ResponseEntity.ok(rawProductDataList);
    }
}
