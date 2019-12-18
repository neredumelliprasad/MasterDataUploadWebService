package com.eunice.sap.config;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestinationProperties;
import com.sap.cloud.sdk.s4hana.connectivity.DefaultErpHttpDestination;
import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.DefaultProductMasterServiceBatch;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.ProductMasterServiceBatch;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultProductMasterService;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.ProductMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfiguration
{
    @Value("${sap.destinationName}")
    private String destinationName;

    private static final Logger LOG = LoggerFactory.getLogger(UploadConfiguration.class);
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
        ErpHttpDestination destination;
       try{
           destination = DestinationAccessor.getDestination(destinationName).asHttp().decorate(DefaultErpHttpDestination::new);
       }
       catch (Exception e){
           LOG.warn("Failed to define SAP destination:"+destinationName+" No SAP connectors found!");
           destination = null;
       }
        return new DefaultErpHttpDestination(destination);

    }
}
