package com.fernandocejas.android10.restrofit.enity.mapper.buyer;

import com.fernandocejas.android10.order.domain.buyer.Image_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.ImageEntity_Buyer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImageEntityDataMapper_Buyer {
    @Inject
    ImageEntityDataMapper_Buyer() {

    }

    public Image_Buyer transform(ImageEntity_Buyer imageEntity) {
        Image_Buyer image_buyer = null;
        if (imageEntity != null) {
            image_buyer = new Image_Buyer();
            image_buyer.setImage(imageEntity.getImage());
        }
        return image_buyer;
    }

    public List<Image_Buyer> transform(Collection<ImageEntity_Buyer> imageEntityCollection) {
        final List<Image_Buyer> imageList = new ArrayList<>();
        for (ImageEntity_Buyer imageEntity_buyer : imageEntityCollection) {
            final Image_Buyer image = transform(imageEntity_buyer);
            if (image != null) {
                imageList.add(image);
            }
        }
        return imageList;
    }

    public ImageEntity_Buyer transform(Image_Buyer image_buyer) {
        ImageEntity_Buyer imageEntity_buyer = null;
        if (image_buyer != null) {
            imageEntity_buyer = new ImageEntity_Buyer();
            imageEntity_buyer.setImage(image_buyer.getImage());
        }
        return imageEntity_buyer;
    }

    public List<ImageEntity_Buyer> transformEntity(Collection<Image_Buyer> imageCollection) {
        final List<ImageEntity_Buyer> imageEntityList = new ArrayList<>();
        for (Image_Buyer image_buyer : imageCollection) {
            final ImageEntity_Buyer imageEntity_buyer = transform(image_buyer);
            if (imageEntity_buyer != null) {
                imageEntityList.add(imageEntity_buyer);
            }
        }
        return imageEntityList;
    }
}
