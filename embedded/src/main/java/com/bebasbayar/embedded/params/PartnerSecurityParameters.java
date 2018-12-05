package com.bebasbayar.embedded.params;

import android.os.Parcel;
import android.os.Parcelable;

public class PartnerSecurityParameters implements Parcelable {
    private String signature;

    public PartnerSecurityParameters() {}

    public PartnerSecurityParameters(String signature) {
        this.signature = signature;
    }

    public boolean isProperParameters() {
        if(getSignature() == null || (getSignature() != null && getSignature().trim().equals(""))) {
            return false;
        }

        return true;
    }

    public PartnerSecurityParameters(Parcel in) {
        String[] data = new String[1];

        in.readStringArray(data);

        setSignature(data[0]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {getSignature()});
    }

    public static final Creator CREATOR = new Creator() {
        public PartnerSecurityParameters createFromParcel(Parcel in) {
            return new PartnerSecurityParameters(in);
        }

        public PartnerSecurityParameters[] newArray(int size) {
            return new PartnerSecurityParameters[size];
        }
    };

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
