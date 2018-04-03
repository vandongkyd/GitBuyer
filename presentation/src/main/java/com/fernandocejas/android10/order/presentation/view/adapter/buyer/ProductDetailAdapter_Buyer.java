/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.adapter.buyer;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.presentation.model.OrderModel;
import com.fernandocejas.android10.order.presentation.model.buyer.ImageModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.order.presentation.utils.Utils;
import com.fernandocejas.android10.order.presentation.view.buyer.ProductDetailAdapterView_Buyer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of {@link OrderModel}.
 */
public class ProductDetailAdapter_Buyer extends RecyclerView.Adapter<ProductDetailAdapter_Buyer.ProductViewHolder> implements ProductDetailAdapterView_Buyer {

    private List<ProductModel_Buyer> productsCollection;
    private final LayoutInflater layoutInflater;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onMinusQuantity(ProductModel_Buyer productModel, int position);

        void onPlusQuantity(ProductModel_Buyer productModel, int position);

        void onWeightChange(ProductModel_Buyer productModel, String s);

        void onPriceChange(ProductModel_Buyer productModel, String s);

        void onLinkClicked(ProductModel_Buyer productModel);

        void onPickPhotoClicked(ProductModel_Buyer productModel, int position);

        void onShowSlideshowClicked(ProductModel_Buyer productModel);

    }

    @Inject
    ProductDetailAdapter_Buyer(Context context) {
        this.context = context;
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.productsCollection = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.productsCollection != null) ? this.productsCollection.size() : 0;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_product_detail, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        final ProductModel_Buyer productModel = this.productsCollection.get(position);
        //show name
        showNameProductInView(holder, productModel.getName());
        //show image list
        renderImageList(holder, productModel.getImages());
        //show description
        showDescriptionInView(holder, productModel.getDescription());
        //show link
        String link = getCommonLink(productModel.getLink());
        showLinkInView(holder, link);
        //show weight
        showWeightInView(holder, productModel.getWeight());
        //show price
        showPriceInView(holder, productModel.getPrice());
//        //show weight unit
//        showWeightUnitInView(holder, productModel.getWeight_unit());
//        //show currency symbol
//        String currency_symbol = getCurrencySymbol(productModel.getCurrency());
//        showCurrencySymbolInView(holder, currency_symbol);
        //show quantity
        showQuantityInView(holder, productModel.getQuantity(), 1000);

        //action events

        holder.btn_add_quantity.setOnClickListener(v -> {
            ProductDetailAdapter_Buyer.this.onPlusQuantityClicked(productModel, position);
        });

        holder.btn_remove_quantity.setOnClickListener(v -> {
            ProductDetailAdapter_Buyer.this.onMinusQuantityClicked(productModel, position);
        });

        holder.btn_link.setOnClickListener(v -> {
            ProductDetailAdapter_Buyer.this.onLinkClicked(productModel);
        });

        holder.btn_add_more_image.setOnClickListener(v -> {
            ProductDetailAdapter_Buyer.this.onPickPhotoClicked(productModel, position);
        });

        holder.edt_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onPriceInputChanged(holder, productModel);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.edt_weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onWeightInputChanged(holder, productModel);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        for (ImageView imageView : holder.imv_images) {
            imageView.setOnClickListener(v -> {
                ProductDetailAdapter_Buyer.this.onShowSlideshowClicked(productModel);
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setProductsCollection(Collection<ProductModel_Buyer> productsCollection) {
        this.validateCountriesCollection(productsCollection);
        this.productsCollection = (List<ProductModel_Buyer>) productsCollection;
        this.notifyDataSetChanged();
    }

    public void setProduct(ProductModel_Buyer product, int position) {
        this.productsCollection.set(position, product);
        this.notifyItemChanged(position);
    }

    private void validateCountriesCollection(Collection<ProductModel_Buyer> productsCollection) {
        if (productsCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    private String getCurrencySymbol(String currency) {
        return Utils.getCurrencySymbol(currency);
    }

    private float getWeightAmount(String weight, String quantity) {
        float w = 0;
        try {
            w = Float.valueOf(weight) * Integer.valueOf(quantity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return w;
    }

    private float getPriceAmount(String price, String quantity) {
        float p = 0;
        try {
            p = Float.valueOf(price) * Integer.valueOf(quantity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;
    }

    private String getCommonLink(String link) {
        try {
            String l = link.replace("https://", "");
            l = l.split("/")[0];
            return l;
        } catch (Exception ex) {
        }
        return link;
    }

    @Override
    public void renderImageList(ProductViewHolder viewHolder, Collection<ImageModel_Buyer> imageModels) {
        for (int i = 0; i < viewHolder.imv_images.length; i++) {
            if (i >= imageModels.size()) {
                //we have no more image to show
                viewHolder.imv_images[i].setVisibility(View.GONE);
            } else {
                viewHolder.imv_images[i].setVisibility(View.VISIBLE);
                //
                String image_url = null;
                try {
                    image_url = ((List<ImageModel_Buyer>) imageModels).get(i).getImage();
                } catch (Exception ex) {
                }
                Glide.with(context)
                        .load(image_url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(viewHolder.imv_images[i]);
            }
        }
    }

    @Override
    public void showNameProductInView(ProductViewHolder viewHolder, String name) {
        viewHolder.tv_name.setText(name);
    }

    @Override
    public void showDescriptionInView(ProductViewHolder viewHolder, String description) {
        viewHolder.tv_description.setText(Utils.fromHtml(description));
    }

    @Override
    public void showLinkInView(ProductViewHolder viewHolder, String link) {
        viewHolder.btn_link.setText(link);
    }

    @Override
    public void showQuantityInView(ProductViewHolder viewHolder, String quantity, int stock) {
        viewHolder.tv_quantity.setText(quantity);
        int q = Integer.valueOf(quantity);
        //less than 1
        if (q <= 1) {
            viewHolder.btn_remove_quantity.setImageResource(R.drawable.ic_minus_disable);
            viewHolder.btn_remove_quantity.setEnabled(false);
        } else {
            viewHolder.btn_remove_quantity.setImageResource(R.drawable.ic_minus_enable);
            viewHolder.btn_remove_quantity.setEnabled(true);
        }
        //more than stock
        if (q >= stock) {
            viewHolder.btn_add_quantity.setImageResource(R.drawable.ic_plus_disable);
            viewHolder.btn_add_quantity.setEnabled(false);
        } else {
            viewHolder.btn_add_quantity.setImageResource(R.drawable.ic_plus_enable);
            viewHolder.btn_add_quantity.setEnabled(true);
        }
    }

    @Override
    public void showWeightInView(ProductViewHolder viewHolder, String weight) {
        viewHolder.edt_weight.setText(weight);
    }

    @Override
    public void showPriceInView(ProductViewHolder viewHolder, String price) {
        viewHolder.edt_price.setText(price);
    }

    @Override
    public void showWeightUnitInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String unit) {
        viewHolder.tv_unit.setText(unit);
    }

    @Override
    public void showCurrencySymbolInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String symbol) {
        viewHolder.tv_currency.setText(symbol);
    }

    @Override
    public void onPlusQuantityClicked(ProductModel_Buyer productModel, int position) {
        this.onItemClickListener.onPlusQuantity(productModel, position);
    }

    @Override
    public void onMinusQuantityClicked(ProductModel_Buyer productModel, int position) {
        this.onItemClickListener.onMinusQuantity(productModel, position);
    }

    @Override
    public void onWeightInputChanged(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, ProductModel_Buyer productModel) {
        onItemClickListener.onWeightChange(productModel,
                viewHolder.edt_weight.getText().toString()
        );
    }

    @Override
    public void onPriceInputChanged(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, ProductModel_Buyer productModel) {
        onItemClickListener.onPriceChange(productModel,
                viewHolder.edt_price.getText().toString()
        );
    }

    @Override
    public void onLinkClicked(ProductModel_Buyer productModel) {
        onItemClickListener.onLinkClicked(productModel);
    }

    @Override
    public void onPickPhotoClicked(ProductModel_Buyer productModel, int position) {
        onItemClickListener.onPickPhotoClicked(productModel, position);
    }

    @Override
    public void onShowSlideshowClicked(ProductModel_Buyer productModel) {
        onItemClickListener.onShowSlideshowClicked(productModel);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_description)
        TextView tv_description;
        @Bind(R.id.btn_link)
        TextView btn_link;
        @Bind(R.id.tv_quantity)
        TextView tv_quantity;
        @Bind(R.id.edt_weight)
        EditText edt_weight;
        @Bind(R.id.edt_price)
        EditText edt_price;
        @Bind({R.id.imv_1, R.id.imv_2, R.id.imv_3})
        ImageView[] imv_images;
        @Bind(R.id.btn_remove_quantity)
        AppCompatImageButton btn_remove_quantity;
        @Bind(R.id.btn_add_quantity)
        AppCompatImageButton btn_add_quantity;
        @Bind(R.id.btn_add_more_image)
        AppCompatImageButton btn_add_more_image;
        @Bind(R.id.tv_unit)
        TextView tv_unit;
        @Bind(R.id.tv_currency)
        TextView tv_currency;

        ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
