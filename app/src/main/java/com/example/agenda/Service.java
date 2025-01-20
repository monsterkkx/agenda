package com.example.agenda;

import android.os.Parcel;
import android.os.Parcelable;

public class Service implements Parcelable {
    private String name;
    private String description;
    private int iconResId;
    private boolean isSelected;

    // Construtor
    public Service(String name, String description, int iconResId) {
        this.name = name;
        this.description = description;
        this.iconResId = iconResId;
        this.isSelected = false;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    // Métodos Parcelable
    protected Service(Parcel in) {
        name = in.readString();
        description = in.readString();
        iconResId = in.readInt();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(iconResId);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
