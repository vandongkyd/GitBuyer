/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.order.presentation.mapper.buyer;

import com.fernandocejas.android10.order.domain.Product;
import com.fernandocejas.android10.order.domain.buyer.Product_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.CountryModelDataMapper;
import com.fernandocejas.android10.order.presentation.model.ProductModel;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class ProductModelDataMapper_Buyer {

    private final ImageModelDataMapper_Buyer imageModelDataMapperBuyer;
    private final CountryModelDataMapper countryModelDataMapper;

    @Inject
    public ProductModelDataMapper_Buyer(ImageModelDataMapper_Buyer imageModelDataMapperBuyer,
                                        CountryModelDataMapper countryModelDataMapper) {
        this.imageModelDataMapperBuyer = imageModelDataMapperBuyer;
        this.countryModelDataMapper = countryModelDataMapper;
    }

    public ProductModel_Buyer transform(Product_Buyer product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final ProductModel_Buyer productModel = new ProductModel_Buyer();
        productModel.setId(product.getId());
        productModel.setOrderid(product.getOrderid());
        productModel.setLink(product.getLink());
        productModel.setName(product.getName());
        productModel.setCode(product.getCode());
        productModel.setDescription(product.getDescription());
        productModel.setPrice(product.getPrice());
        productModel.setQuantity(product.getQuantity());
        productModel.setWeight(product.getWeight());
        productModel.setImages(imageModelDataMapperBuyer.transform(product.getImages()));
        if(product.getCountry()!= null){
            productModel.setCountry(countryModelDataMapper.transform(product.getCountry()));
        }
        return productModel;
    }

    public Collection<ProductModel_Buyer> transform(Collection<Product_Buyer> productsCollection) {
        Collection<ProductModel_Buyer> productModelsCollection;

        if (productsCollection != null && !productsCollection.isEmpty()) {
            productModelsCollection = new ArrayList<>();
            for (Product_Buyer Product : productsCollection) {
                productModelsCollection.add(transform(Product));
            }
        } else {
            productModelsCollection = Collections.emptyList();
        }

        return productModelsCollection;
    }

    public Product_Buyer transform(ProductModel_Buyer product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final Product_Buyer productModel = new Product_Buyer();
        productModel.setId(product.getId());
        productModel.setOrderid(product.getOrderid());
        productModel.setLink(product.getLink());
        productModel.setName(product.getName());
        productModel.setCode(product.getCode());
        productModel.setDescription(product.getDescription());
        productModel.setPrice(product.getPrice());
        productModel.setQuantity(product.getQuantity());
        productModel.setWeight(product.getWeight());
        productModel.setImages(imageModelDataMapperBuyer.transformToDomain(product.getImages()));
        if(product.getCountry()!= null){
            productModel.setCountry(countryModelDataMapper.transform(product.getCountry()));
        }
        return productModel;
    }

    public Collection<Product_Buyer> transformToDomain(Collection<ProductModel_Buyer> productsCollection) {
        Collection<Product_Buyer> productModelsCollection;

        if (productsCollection != null && !productsCollection.isEmpty()) {
            productModelsCollection = new ArrayList<>();
            for (ProductModel_Buyer Product : productsCollection) {
                productModelsCollection.add(transform(Product));
            }
        } else {
            productModelsCollection = Collections.emptyList();
        }

        return productModelsCollection;
    }

}

