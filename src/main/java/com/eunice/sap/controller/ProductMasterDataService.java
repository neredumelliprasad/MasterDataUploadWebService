package com.eunice.sap.controller;

import com.eunice.sap.model.RawProductData;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestinationProperties;
import com.sap.cloud.sdk.datamodel.odata.helper.batch.BatchResponse;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductDescription;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductPlant;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductSalesDelivery;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductStorageLocation;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductUnitsOfMeasure;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductValuation;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.ProductMasterServiceBatch;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.ProductMasterServiceBatchChangeSet;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMasterDataService
{
    @Autowired
    ProductMasterServiceBatch productMasterService;

    @Autowired
    HttpDestinationProperties connection;

    private ProductUnitsOfMeasure createUnitOfMeasure(RawProductData rawProductData){
        return null;
    }

    private ProductPlant createProductPlant(RawProductData productData){
        return null;
    }

    private ProductStorageLocation createProductStorageLocation(RawProductData rawProductData){
        return null;
    }
    private ProductSalesDelivery createProductSalesDelivery(RawProductData rawProductData){
        return ProductSalesDelivery.builder().product(rawProductData.getMaterial()).build();
    }

    private ProductDescription createProductDescription(RawProductData rawProductData){
        return null;
    }
    private ProductValuation createProductValuation(RawProductData rawProductData){
        return null;
    }

    @Transactional
    public void persistProduct(Product product) throws ODataException
    {
        ProductMasterServiceBatchChangeSet changeSet = productMasterService.beginChangeSet();
        changeSet.updateProduct(product);
        BatchResponse response = changeSet.endChangeSet().execute(connection);
    }
}
