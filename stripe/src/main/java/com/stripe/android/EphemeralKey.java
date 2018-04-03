package com.stripe.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.stripe.android.model.StripeJsonModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an Ephemeral Key that can be used temporarily for certain operations.
 */
class EphemeralKey extends StripeJsonModel implements Parcelable {

    static final String FIELD_CREATED = "created";
    static final String FIELD_EXPIRES = "expires";
    static final String FIELD_SECRET = "secret";
    static final String FIELD_LIVEMODE = "livemode";
    static final String FIELD_OBJECT = "object";
    static final String FIELD_ID = "id";
    static final String FIELD_ASSOCIATED_OBJECTS = "associated_objects";
    static final String FIELD_TYPE = "type";

    static final String NULL = "null";

    private long mCreated;
    private @NonNull String mCustomerId;
    private long mExpires;
    private @NonNull String mId;
    private boolean mLiveMode;
    private @NonNull String mObject;
    private @NonNull String mSecret;
    private @NonNull String mType;

    /**
     * Parcel constructor for this {@link Parcelable} class.
     * Note that if you change the order of these read values,
     * you must change the order in which they are written in
     * {@link #writeToParcel(Parcel, int)} below.
     *
     * @param in the {@link Parcel} in which this Ephemeral Key has been stored.
     */
    private EphemeralKey(Parcel in) {
        mCreated = in.readLong();
        mCustomerId = in.readString();
        mExpires = in.readLong();
        mId = in.readString();
        mLiveMode = in.readInt() == 1;
        mObject = in.readString();
        mSecret = in.readString();
        mType = in.readString();
    }

    private EphemeralKey(
            long created,
            @NonNull String customerId,
            long expires,
            @NonNull String id,
            boolean liveMode,
            @NonNull String object,
            @NonNull String secret,
            @NonNull String type
    ) {
        mCreated = created;
        mCustomerId = customerId;
        mExpires = expires;
        mId = id;
        mLiveMode = liveMode;
        mObject = object;
        mSecret = secret;
        mType = type;
    }

    @NonNull
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray associatedObjectsArray = new JSONArray();
        JSONObject associatedObject = new JSONObject();

        try {
            jsonObject.put(FIELD_CREATED, mCreated);
            jsonObject.put(FIELD_EXPIRES, mExpires);
            jsonObject.put(FIELD_OBJECT, mObject);
            jsonObject.put(FIELD_ID, mId);
            jsonObject.put(FIELD_SECRET, mSecret);
            jsonObject.put(FIELD_LIVEMODE, mLiveMode);

            associatedObject.put(FIELD_TYPE, mType);
            associatedObject.put(FIELD_ID, mCustomerId);
            associatedObjectsArray.put(associatedObject);

            jsonObject.put(FIELD_ASSOCIATED_OBJECTS, associatedObjectsArray);
        } catch (JSONException impossible) {
            // An exception can only be thrown from put operations if the key is null
            // or the value is a non-finite number.
            throw new IllegalArgumentException("JSONObject creation exception thrown.");
        }

        return jsonObject;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> toMap() {
        Map map = new HashMap<>();
        map.put(FIELD_CREATED, mCreated);
        map.put(FIELD_EXPIRES, mExpires);
        map.put(FIELD_OBJECT, mObject);
        map.put(FIELD_ID, mId);
        map.put(FIELD_SECRET, mSecret);
        map.put(FIELD_LIVEMODE, mLiveMode);

        List<Object> associatedObjectsList = new ArrayList<>();
        Map<String, String> associatedObjectMap = new HashMap<>();
        associatedObjectMap.put(FIELD_ID, mCustomerId);
        associatedObjectMap.put(FIELD_TYPE, mType);
        associatedObjectsList.add(associatedObjectMap);

        map.put(FIELD_ASSOCIATED_OBJECTS, associatedObjectsList);
        return map;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Write the object into a {@link Parcel}. Note that if the order of these
     * write operations is changed, an identical change must be made to
     * {@link #EphemeralKey(Parcel)} constructor above.
     *
     * @param out a {@link Parcel} into which to write this object
     * @param flags any flags (unused) for writing this object
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(mCreated);
        out.writeString(mCustomerId);
        out.writeLong(mExpires);
        out.writeString(mId);
        // There is no writeBoolean
        out.writeInt(mLiveMode ? 1 : 0);
        out.writeString(mObject);
        out.writeString(mSecret);
        out.writeString(mType);
    }

    long getCreated() {
        return mCreated;
    }

    @NonNull
    String getCustomerId() {
        return mCustomerId;
    }

    long getExpires() {
        return mExpires;
    }

    @VisibleForTesting
    void setExpires(long value) {
        mExpires = value;
    }

    @NonNull
    String getId() {
        return mId;
    }

    boolean isLiveMode() {
        return mLiveMode;
    }

    @NonNull
    String getObject() {
        return mObject;
    }

    @NonNull
    String getSecret() {
        return mSecret;
    }

    @NonNull
    String getType() {
        return mType;
    }

    public static final Parcelable.Creator<EphemeralKey> CREATOR
            = new Parcelable.Creator<EphemeralKey>() {

        @Override
        public EphemeralKey createFromParcel(Parcel in) {
            return new EphemeralKey(in);
        }

        @Override
        public EphemeralKey[] newArray(int size) {
            return new EphemeralKey[size];
        }
    };

    @Nullable
    static EphemeralKey fromString(@Nullable String rawJson) {
        if (rawJson == null) {
            return null;
        }

        try {
            JSONObject object = new JSONObject(rawJson);
            return fromJson(object);
        } catch (JSONException ignored) {
            return null;
        }
    }

    @Nullable
    static EphemeralKey fromJson(@Nullable JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }

        try {
            long created = jsonObject.getLong(FIELD_CREATED);
            long expires = jsonObject.getLong(FIELD_EXPIRES);
            String id = jsonObject.getString(FIELD_ID);
            boolean liveMode = jsonObject.getBoolean(FIELD_LIVEMODE);
            String object = jsonObject.getString(FIELD_OBJECT);
            String secret = jsonObject.getString(FIELD_SECRET);

            // Get the values from the associated objects array first element
            JSONArray associatedObjectArray = jsonObject.getJSONArray(FIELD_ASSOCIATED_OBJECTS);
            JSONObject typeObject = associatedObjectArray.getJSONObject(0);
            String type = typeObject.getString(FIELD_TYPE);
            String customerId = typeObject.getString(FIELD_ID);

            return new EphemeralKey(
                    created,
                    customerId,
                    expires,
                    id,
                    liveMode,
                    object,
                    secret,
                    type);

        } catch (JSONException ignored) {
            return null;
        }
    }
}
