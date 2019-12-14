package com.eunice.sap.controller;

import com.eunice.sap.model.RawProductData;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductPlant;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductStorageLocation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SAPIntegrationController {

    @Autowired
    ProductMasterDataService productMasterDataService;
    @RequestMapping(value = "/process", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public void processData(@RequestBody List<RawProductData> rawProductDataList) throws Exception
    {
        int i = 0;
        System.out.println("Product Bean is " + rawProductDataList.toString());
        RawProductData rawProductData = rawProductDataList.get(0);
        Product product = new Product();
        ProductPlant productPlant = new ProductPlant();
        List<ProductStorageLocation> productStorageLocationList = new ArrayList<>();
        ProductStorageLocation productStorageLocation = new ProductStorageLocation();
        product.setProduct(rawProductData.getNumber());
        product.setProductType(rawProductData.getMaterialType());
        productStorageLocation.setStorageLocation(rawProductData.getStoreLocation());
        productStorageLocationList.add(productStorageLocation);
        productStorageLocation.setProduct(rawProductData.getNumber());
        productPlant.setStorageLocation(productStorageLocationList);
        productMasterDataService.persistProduct(product);

    }
}
