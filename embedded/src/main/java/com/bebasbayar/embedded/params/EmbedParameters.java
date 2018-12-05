package com.bebasbayar.embedded.params;

import android.os.Parcel;
import android.os.Parcelable;

public class EmbedParameters implements Parcelable {
    private String apiKey;
    private String partnerUserId;
    private String userEmail;
    private String userPhoneNumber;
    private String userFullName;
    private String clientId;
    private boolean isDevelopment;

    public EmbedParameters() {}

    public EmbedParameters(String apiKey, String partnerUserId, boolean isDevelopment, String userEmail, String userPhoneNumber, String userFullName, String clientId) {
        this.apiKey = apiKey;
        this.partnerUserId = partnerUserId;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userFullName = userFullName;
        this.clientId = clientId;
        this.isDevelopment = isDevelopment;
    }

    public boolean isProperParameters() {
        return getClientId() != null && (getClientId() == null || !getClientId().trim().equals("")) &&
                getApiKey() != null && (getApiKey() == null || !getApiKey().trim().equals("")) &&
                getPartnerUserId() != null && (getPartnerUserId() == null || !getPartnerUserId().trim().equals(""));
    }

    public EmbedParameters(Parcel in) {
        String[] dataStrings = new String[6];
        boolean[] dataBooleans = new boolean[1];

        in.readStringArray(dataStrings);
        in.readBooleanArray(dataBooleans);

        setApiKey(dataStrings[0]);
        setPartnerUserId(dataStrings[1]);
        setUserEmail(dataStrings[2]);
        setUserPhoneNumber(dataStrings[3]);
        setUserFullName(dataStrings[4]);
        setClientId(dataStrings[5]);
        setDevelopment(dataBooleans[0]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {getApiKey(), getPartnerUserId(), getUserEmail(), getUserPhoneNumber(), getUserFullName(), getClientId()});
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
