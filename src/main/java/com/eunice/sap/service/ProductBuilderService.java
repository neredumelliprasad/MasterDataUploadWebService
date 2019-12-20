package com.eunice.sap.service;

import com.eunice.sap.model.RawProductData;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmullapu on 18/12/19.
 */
@Service
public class ProductBuilderService {

    public List<Product> buildProductMasterData(List<RawProductData> rawProductData) {
        List<Product> productList = new ArrayList<>();

        rawProductData.stream().forEach( e -> {
            Product product = new Product();
            product.setProduct(e.getNumber());
            product.setProductType(e.getMaterialType());
            product.addPlant(buildProductPlant(e));

            ProductDescription productDescription = new ProductDescription();
            productDescription.setProductDescription(e.getMaterialDescription());
            product.addDescription(productDescription);

            product.setProductOldID(e.getOldMaterialNumber());
            product.setDivision(e.getDivision());
            product.setCrossPlantStatus(e.getCrossPlantMaterialStatus());
            //product.setCrossPlantStatusValidityDate(e.getValidFromCrossPlantMaterial()); //Convert to local date time
            product.setWeightUnit(e.getWeightUnit());
            ProductSalesDelivery productSalesDelivery = new ProductSalesDelivery();
            productSalesDelivery.setSalesMeasureUnit(e.getSalesUnit());
            productSalesDelivery.setSupplyingPlant(e.getDeliveryPlantSpecStatus());
            product.setItemCategoryGroup(e.getItemCategoryGroup());
            product.setProductHierarchy(e.getProductHierarchy());
            product.setIsBatchManagementRequired(Boolean.valueOf(e.getBatchManagement())); //validation null check and boolean check

            ProductSales productSales = new ProductSales();
            productSales.setTransportationGroup(e.getTransportationGroup());

            ProductPlantSales productPlantSales = new ProductPlantSales();
            productPlantSales.setLoadingGroup(e.getLoadingGroup());

            product.setSerialNumberProfile(e.getSerialNoProfile());
            product.setCountryOfOrigin(e.getCountryOfOrigin());

            ProductStorage productStorage = new ProductStorage();
            //productStorage.setMinRemainingShelfLife(e.getMinimumRemainingShelfLife()); //BigDecimal
            productStorage.setExpirationDate(e.getExpirationDate());
            product.setProductStorage(productStorage);

            ProductValuation productValuation = new ProductValuation();
            productValuation.setValuationClass(e.getValuationClass());
            productValuation.setPriceDeterminationControl(e.getPriceDeterminationControl());
            productValuation.setProductOriginType(e.getMaterialOrigin());
            product.addValuation(productValuation);

            List<ProductUnitsOfMeasure> puomList = new ArrayList<ProductUnitsOfMeasure>();
            ProductUnitsOfMeasure productUnitsOfMeasure = new ProductUnitsOfMeasure();
            //productUnitsOfMeasure.setQuantityDenominator(e.getQunatityDenominator()); //BigDecimal
            //productUnitsOfMeasure.setQuantityNumerator(e.getQunatityNumerator()); //BigDecimal
            puomList.add(productUnitsOfMeasure);
            product.setProductUnitsOfMeasure(puomList);

            //setProductPlantSales in product or productPlant
            //Set product Sales delivery in product
            product.setProductSales(productSales);
            productList.add(product);
        });
        return productList;
    }

    private ProductPlant buildProductPlant(RawProductData rawProductData) {
        ProductPlant productPlant = new ProductPlant();
        productPlant.setStorageLocation(buildProductStorageLocation(rawProductData));
        productPlant.setIsBatchManagementRequired(Boolean.valueOf(rawProductData.getBatchManagementPlant()));
        productPlant.setProfitCenter(rawProductData.getProfitCenter());
        productPlant.setProfileCode(rawProductData.getPlantSpecificMaterialStatus());
        //productPlant.setProfileValidityStartDate(rawProductData.getValidFromPlantMaterial()); //date conversion

        ProductSupplyPlanning productSupplyPlanning = new ProductSupplyPlanning();
        //productSupplyPlanning.setTotalReplenishmentLeadTime(rawProductData.getInhouseProductionTime()); //BigDecimal
        productSupplyPlanning.setPlanningStrategyGroup(rawProductData.getStrategyGroup());

        ProductPlantMRPArea productPlantMRPArea = new ProductPlantMRPArea();
        productPlantMRPArea.setIsMRPDependentRqmt(rawProductData.getMrpDependentRequirements());

        productPlant.addPlantMRPArea(productPlantMRPArea);
        productPlant.setProductSupplyPlanning(productSupplyPlanning);
        return productPlant;
    }

    private List<ProductStorageLocation> buildProductStorageLocation(RawProductData rawProductData) {
        List<ProductStorageLocation> productStorageLocationList = new ArrayList<>();
        ProductStorageLocation productStorageLocation = new ProductStorageLocation();
        productStorageLocation.setStorageLocation(rawProductData.getStoreLocation());
        productStorageLocation.setProduct(rawProductData.getNumber());
        productStorageLocationList.add(productStorageLocation);
        return productStorageLocationList;
    }

    private void buildProductStorageLocation1(RawProductData rawProductData) {
    }

}
