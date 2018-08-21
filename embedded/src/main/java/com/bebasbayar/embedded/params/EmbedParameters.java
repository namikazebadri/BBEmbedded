package com.bebasbayar.embedded.params;

import android.os.Parcel;
import android.os.Parcelable;

public class EmbedParameters implements Parcelable {
    private String apiKey;
    private String partnerUserId;
    private boolean isDevelopment;

    public EmbedParameters(String apiKey, String partnerUserId, boolean isDevelopment) {
        this.apiKey = apiKey;
        this.partnerUserId = partnerUserId;
        this.isDevelopment = isDevelopment;
    }

    public EmbedParameters(Parcel in) {
        String[] data = new String[2];
        boolean[] dataSecond = new boolean[1];

        in.readStringArray(data);
        in.readBooleanArray(dataSecond);

        setApiKey(data[0]);
        setPartnerUserId(data[1]);
        setDevelopment(dataSecond[0]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {getApiKey(), getPartnerUserId()});
        dest.writeBooleanArray(new boolean[]{isDevelopment()});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public EmbedParameters createFromParcel(Parcel in) {
            return new EmbedParameters(in);
        }

        public EmbedParameters[] newArray(int size) {
            return new EmbedParameters[size];
        }
    };

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPartnerUserId() {
        return partnerUserId;
    }

    public void setPartnerUserId(String partnerUserId) {
        this.partnerUserId = partnerUserId;
    }

    public boolean isDevelopment() {
        return isDevelopment;
    }

    public void setDevelopment(boolean development) {
        isDevelopment = development;
    }
}
