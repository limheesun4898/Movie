package com.heesun.movie_moa.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

public class AreaTheatherItem implements Parcelable {
    public String cd;
    public String cdNm;
    public boolean isSelected;

    public AreaTheatherItem(String cd, String cdNm) {
        this.cd = cd;
        this.cdNm = cdNm;
    }

    public AreaTheatherItem(String cd, String cdNm, boolean isSelected) {
        this.cd = cd;
        this.cdNm = cdNm;
        this.isSelected = isSelected;
    }

    protected AreaTheatherItem(Parcel in) {
        cd = in.readString();
        cdNm = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<AreaTheatherItem> CREATOR = new Creator<AreaTheatherItem>() {
        @Override
        public AreaTheatherItem createFromParcel(Parcel in) {
            return new AreaTheatherItem(in);
        }

        @Override
        public AreaTheatherItem[] newArray(int size) {
            return new AreaTheatherItem[size];
        }
    };

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCdNm() {
        return cdNm;
    }

    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cd);
        dest.writeString(cdNm);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
