package com.foxdev.hogwartslore.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.foxdev.hogwartslore.util.converters.FloatConverter;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Wands", foreignKeys = @ForeignKey(
        entity = Person.class,
        parentColumns = "personId",
        childColumns = "wandId",
        onDelete = CASCADE))
public final class Wand implements Parcelable {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(typeAffinity = INTEGER)
    public long wandId;

    @SerializedName("wood")
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    public String wood;

    @SerializedName("core")
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    public String core;

    @SerializedName("length")
    @ColumnInfo(typeAffinity = TEXT)
    @TypeConverters({ FloatConverter.class })
    @Nullable
    public String length;

    public boolean isEmpty() {
        return (wood == null || wood.isEmpty()) &&
                (core == null || core.isEmpty()) &&
                (length == null || length.isEmpty());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(wandId);
        dest.writeString(wood);
        dest.writeString(core);
        dest.writeString(length);
    }

    public Wand() {}

    protected Wand(Parcel in) {
        wandId = in.readLong();
        wood = in.readString();
        core = in.readString();
        length = in.readString();
    }

    @Ignore
    public static final Creator<Wand> CREATOR = new Creator<Wand>() {
        @Override
        public Wand createFromParcel(Parcel source) {
            return new Wand(source);
        }

        @Override
        public Wand[] newArray(int size) {
            return new Wand[size];
        }
    };
}