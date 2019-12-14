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
    @Value("${sap.base.url}")
    private String baseUrl;
    @Value("${sap.api.user}")
    private String user;
    @Value("${sap.api.user.password}")
    private String password;
   @Bean
   public ProductMasterServiceBatch getProductMasterService(){
       ProductMasterService productMasterService = new DefaultProductMasterService();
       ProductMasterServiceBatch batchServiceBatchChangeSet = new DefaultProductMasterServiceBatch(productMasterService);
       return batchServiceBatchChangeSet;
   }

    @Bean
    public HttpDestinationProperties destinationProperties() {
       ErpHttpDestination destination = DestinationAccessor.getDestination("ErpQueryEndpoint").asHttp().decorate(DefaultErpHttpDestination::new);

        return new DefaultErpHttpDestination(destination);
    }
}
