package com.fernandocejas.android10.restrofit.enity.buyer;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

/**
 * Created by vandongluong on 3/30/18.
 */
@AutoValue
public abstract class ShipInfroListEntityResponse_Buyer {
    @SerializedName("status")
    public abstract boolean status();

    @SerializedName("message")
    @Nullable
    public abstract String message();

    @SerializedName("data")
    @Nullable
    public abstract Collection<ShipInfoEntity_Buyer> data();

    public static TypeAdapter<ShipInfroListEntityResponse_Buyer> typeAdapter(Gson gson) {
        return new AutoValue_ShipInfroListEntityResponse_Buyer.GsonTypeAdapter(gson);
    }
}
