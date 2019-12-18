package com.eunice.sap.service;

import com.eunice.sap.mappers.RawDataProductMapper;
import com.eunice.sap.model.DataConstructionContext;
import com.eunice.sap.model.MasterDataUpdateResponse;
import com.eunice.sap.model.RawProductData;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestinationProperties;
import com.sap.cloud.sdk.datamodel.odata.helper.ExpressionFluentHelper;
import com.sap.cloud.sdk.datamodel.odata.helper.batch.BatchResponse;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.ProductMasterServiceBatch;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.ProductMasterServiceBatchChangeSet;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.ProductMasterService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMasterDataService extends RawDataProductMapper
{
    @Autowired
    ProductMasterServiceBatch productMasterServiceBatch;

    @Autowired
    ProductMasterService productMasterService;

    @Autowired
    HttpDestinationProperties connection;

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
        return productMasterService.getAllProduct().
            top(10).execute(connection);
    }

    public List<Product> getProductsByKey(List<String> productKeys) throws ODataException
    {
        return productMasterService.
            getAllProduct().
            filter(getFilterQuery(productKeys)).
            execute(connection);
    }

    public Set<Product> constructProductData(List<RawProductData> rawProductData)
    {
        DataConstructionContext constructionContext = new DataConstructionContext();
        Set<Product> products = new HashSet<>();
        rawProductData.stream().forEach(each->{
            Product eachProduct = createEntity(each,constructionContext);
            products.add(eachProduct);
        });
    return products;
    }

    private ExpressionFluentHelper getFilterQuery(List<String> productKeys){
        ExpressionFluentHelper<Product> filter = null;
        if (!productKeys.isEmpty()) {
            for (String eachKey:productKeys){
                filter = filter == null?Product.PRODUCT.eq(eachKey):filter.or(Product.PRODUCT.eq(eachKey));
            }
        }
        return filter;
    }
}
