package com.foxdev.harrylore.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.foxdev.harrylore.sql.util.FloatConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ColumnInfo.TEXT;
import static androidx.room.ColumnInfo.REAL;
import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Wands", foreignKeys = @ForeignKey(
        entity = Person.class,
        parentColumns = "personId",
        childColumns = "wandId",
        onDelete = CASCADE
))
public final class Wand implements Parcelable
{
    //region    [Поля]
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(typeAffinity = INTEGER)
    private int wandId;

    @SerializedName("title")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    private String core;

    @SerializedName("length")
    @Expose
    @ColumnInfo(typeAffinity = REAL)
    @Nullable
    @TypeConverters({ FloatConverter.class })
    private String length;

    @SerializedName("wood")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    private String wood;
    //endregion

    //region    [Сеттеры и геттеры]
    public int getWandId() { return wandId; }

    public void setWandId(int wandId) { this.wandId = wandId; }

    @NonNull
    public String getCore() { return core; }

    public void setCore(@NonNull String core) { this.core = core; }

    @NonNull
    public String getLength() { return length; }

    public void setLength(@NonNull String length) { this.length = length; }

    @NonNull
    public String getWood() { return wood; }

    public void setWood(@NonNull String wood) { this.wood = wood; }
    //endregion

    //region    [Конструкторы]
    public Wand()
    {
        wood = "";
        core = "";
        length = "0";
    }

    @Ignore
    public Wand(Parcel in)
    {
        core = in.readString();
        wood = in.readString();
        wandId = in.readInt();
        length = String.valueOf(in.readFloat());
    }
    //endregion

    //region    [Parcelable]
    @Ignore
    public static final Creator<Wand> CREATOR = new Creator<Wand>()
    {
        @Override
        public Wand createFromParcel(Parcel source) { return new Wand(source); }

        @Override
        public Wand[] newArray(int size) { return new Wand[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(core);
        dest.writeString(wood);
        dest.writeInt(wandId);
        dest.writeFloat(Float.parseFloat(length));
    }
    //endregion
}