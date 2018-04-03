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

import com.fernandocejas.android10.order.domain.Image;
import com.fernandocejas.android10.order.domain.buyer.Image_Buyer;
import com.fernandocejas.android10.order.presentation.model.ImageModel;
import com.fernandocejas.android10.order.presentation.model.buyer.ImageModel_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class ImageModelDataMapper_Buyer {

    @Inject
    public ImageModelDataMapper_Buyer() {
    }

    public ImageModel_Buyer transform(Image_Buyer image) {
        if (image == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final ImageModel_Buyer imageModel = new ImageModel_Buyer();
        imageModel.setImage(image.getImage());
        return imageModel;
    }

    public Collection<ImageModel_Buyer> transform(Collection<Image_Buyer> imagesCollection) {
        Collection<ImageModel_Buyer> imageModelsCollection;

        if (imagesCollection != null && !imagesCollection.isEmpty()) {
            imageModelsCollection = new ArrayList<>();
            for (Image_Buyer image : imagesCollection) {
                imageModelsCollection.add(transform(image));
            }
        } else {
            imageModelsCollection = Collections.emptyList();
        }

        return imageModelsCollection;
    }

    //

    public Image_Buyer transform(ImageModel_Buyer image) {
        if (image == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final Image_Buyer imageModel = new Image_Buyer();
        imageModel.setImage(image.getImage());
        return imageModel;
    }


    public Collection<Image_Buyer> transformToDomain(Collection<ImageModel_Buyer> imagesCollection) {
        Collection<Image_Buyer> imageModelsCollection;

        if (imagesCollection != null && !imagesCollection.isEmpty()) {
            imageModelsCollection = new ArrayList<>();
            for (ImageModel_Buyer image : imagesCollection) {
                imageModelsCollection.add(transform(image));
            }
        } else {
            imageModelsCollection = Collections.emptyList();
        }

        return imageModelsCollection;
    }

}

