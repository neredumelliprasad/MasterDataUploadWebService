package com.eunice.sap.controller;

import com.eunice.sap.model.MasterDataUpdateResponse;
import com.eunice.sap.model.RawProductData;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestinationProperties;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Product> processData() throws Exception
    {
        return productMasterDataService.getProductTop10();
    }

    @RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public MasterDataUpdateResponse processData(@RequestBody List<RawProductData> rawProductDataList) throws Exception
    {
        Set<Product> products = productMasterDataService.constructProductData(rawProductDataList);
        return productMasterDataService.persistProducts(products);
    }
}
