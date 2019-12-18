package com.eunice.sap.config;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestinationProperties;
import com.sap.cloud.sdk.s4hana.connectivity.DefaultErpHttpDestination;
import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.DefaultProductMasterServiceBatch;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.ProductMasterServiceBatch;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultProductMasterService;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.ProductMasterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfiguration
{
    @Value("${sap.destinationName}")
    private String destinationName;
   @Bean
   public ProductMasterServiceBatch getProductMasterServiceBatch(){
       ProductMasterService productMasterService = getProductMasterService();
       ProductMasterServiceBatch batchServiceBatchChangeSet = new DefaultProductMasterServiceBatch(productMasterService);
       return batchServiceBatchChangeSet;
   }

    @Bean
    public ProductMasterService getProductMasterService(){
        ProductMasterService productMasterService = new DefaultProductMasterService();
        return productMasterService;
    }

    @Bean
    public HttpDestinationProperties destinationProperties() {
       ErpHttpDestination destination = DestinationAccessor.getDestination(destinationName).asHttp().decorate(DefaultErpHttpDestination::new);
        return new DefaultErpHttpDestination(destination);
    }
}
