package com.eunice.sap.mappers;
/**
 * Created by roychoud on 18 Dec 2019.
 */

import com.eunice.sap.model.DataConstructionContext;
import com.eunice.sap.model.RawProductData;
import com.sap.cloud.sdk.datamodel.odata.helper.VdmEntity;

/**
 * History:
 * <ul>
 * <li> 18 Dec 2019 : roychoud - Created
 * </ul>
 *
 * @authors roychoud : Arunava Roy Choudhury
 * © 2019 HERE
 */
public interface RawDataMapper<T extends VdmEntity>
{

    T createEntity(RawProductData rawProductData,DataConstructionContext constructionContext);
}
