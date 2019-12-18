package com.eunice.sap.controller;

import com.eunice.sap.model.DataConstructionContext;
import com.eunice.sap.model.MasterDataUpdateResponse;
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
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.ProductMasterService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMasterDataService
{
    @Autowired
    ProductMasterServiceBatch productMasterServiceBatch;

    @Autowired
    ProductMasterService productMasterService;

    @Autowired
    HttpDestinationProperties connection;

    private ProductUnitsOfMeasure createUnitOfMeasure(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }

    private ProductPlant createProductPlant(RawProductData productData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }

    private ProductStorageLocation createProductStorageLocation(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }
    private ProductSalesDelivery createProductSalesDelivery(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }

    private ProductDescription createProductDescription(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }
    private ProductValuation createProductValuation(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }

    private Product createProduct(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }

    @Transactional
    public MasterDataUpdateResponse persistProducts(Set<Product> products) throws ODataException
    {
        ProductMasterServiceBatchChangeSet changeSet = productMasterServiceBatch.beginChangeSet();
        for (Product product:products){
            changeSet = changeSet.updateProduct(product);
        }
        BatchResponse response = changeSet.endChangeSet().execute(connection);
        return new MasterDataUpdateResponse(response);
    }

    @Transactional
    public List<Product> getProductTop10() throws ODataException
    {
        return productMasterService.getAllProduct().top(10).execute(connection);
    }

    public Set<Product> constructProductData(List<RawProductData> rawProductData)
    {
        DataConstructionContext constructionContext = new DataConstructionContext();
        Set<Product> products = new HashSet<>();
        rawProductData.stream().forEach(each->{
            Product eachProduct = createProduct(each,constructionContext);
            eachProduct = getFromMap(eachProduct,constructionContext.getProductMap());
            products.add(eachProduct);
        });
    return products;
    }

    public <T extends Object>T getFromMap(T newData,Map<String,T> map){
        String key = constructKey(newData);
        if (!map.containsKey(key)){
            map.put(key,newData);
        }
        return map.get(newData);
    }

    private String constructKey(Object newData){
        //TODO to implement
        return null;
    }

}
