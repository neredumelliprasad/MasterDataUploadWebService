package com.eunice.sap.mappers;
/**
 * Created by roychoud on 18 Dec 2019.
 */

import com.eunice.sap.model.DataConstructionContext;
import com.eunice.sap.model.RawProductData;

/**
 * History:
 * <ul>
 * <li> 18 Dec 2019 : roychoud - Created
 * </ul>
 *
 * @authors roychoud : Arunava Roy Choudhury
 * © 2019 HERE
 */
public interface RawDataMapper<T extends Object>
{

    T createEntity(RawProductData rawProductData,DataConstructionContext constructionContext);
}
