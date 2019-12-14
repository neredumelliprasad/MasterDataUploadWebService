package com.eunice.sap.model;

/**
 * Created by vmullapu on 14/12/19.
 */
public class RawProductData {
    String number;
    String material;
    String industrySector;
    String materialType;
    String plant;
    String storeLocation;
    String salesOrg;
    String distributionChannel;
    String materialDescription;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getIndustrySector() {
        return industrySector;
    }

    public void setIndustrySector(String industrySector) {
        this.industrySector = industrySector;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public String getDistributionChannel() {
        return distributionChannel;
    }

    public void setDistributionChannel(String distributionChannel) {
        this.distributionChannel = distributionChannel;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    @Override
    public String toString() {
        return "RawProductData{" +
                "number='" + number + '\'' +
                ", material='" + material + '\'' +
                ", industrySector='" + industrySector + '\'' +
                ", materialType='" + materialType + '\'' +
                ", plant='" + plant + '\'' +
                ", storeLocation='" + storeLocation + '\'' +
                ", salesOrg='" + salesOrg + '\'' +
                ", distributionChannel='" + distributionChannel + '\'' +
                ", materialDescription='" + materialDescription + '\'' +
                '}';
    }

}
