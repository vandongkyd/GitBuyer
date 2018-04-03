package com.fernandocejas.android10.restrofit.enity.buyer;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vandongluong on 3/30/18.
 */
@AutoValue
public abstract class ShipInfoEntityResponse_Buyer {
    @SerializedName("status")
    public abstract boolean status();

    @SerializedName("message")
    @Nullable
    public abstract String message();

    @SerializedName("data")
    @Nullable
    public abstract ShipInfoEntity_Buyer data();

    public static TypeAdapter<ShipInfoEntityResponse_Buyer> typeAdapter(Gson gson) {
        return new AutoValue_ShipInfoEntityResponse_Buyer.GsonTypeAdapter(gson);
    }
}
