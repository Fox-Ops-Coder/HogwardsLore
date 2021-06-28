package com.foxdev.hogwartslore.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.foxdev.hogwartslore.objects.constants.Genders;
import com.foxdev.hogwartslore.util.converters.BooleanConverter;
import com.foxdev.hogwartslore.util.converters.IntConverter;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;

@Entity(tableName = "Persons")
public final class Person implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(typeAffinity = INTEGER)
    public long personId;

    @SerializedName("actor")
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    public String actor = "";

    @Ignore
    public final UUID personUUID;

    @SerializedName("alive")
    @ColumnInfo(typeAffinity = INTEGER)
    @TypeConverters({ BooleanConverter.class })
    public boolean alive = true;

    @SerializedName("ancestry")
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    public String ancestry;

    @SerializedName("dateOfBirth")
    @Nullable
    public String dateOfBirth;

    @SerializedName("eyeColour")
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    public String eyeColour;

    @SerializedName("gender")
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    public String gender = "";

    @SerializedName("hairColour")
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    public String hairColour;

    @SerializedName("hogwartsStaff")
    @ColumnInfo(typeAffinity = INTEGER)
    @TypeConverters({ BooleanConverter.class })
    public boolean hogwartsStaff;

    @SerializedName("hogwartsStudent")
    @ColumnInfo(typeAffinity = INTEGER)
    @TypeConverters({ BooleanConverter.class })
    public boolean hogwartsStudent;

    @SerializedName("house")
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    public String house = "";

    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    public String localImage = "";

    @SerializedName("image")
    @Nullable
    @Ignore
    public String internetImage = "";

    @SerializedName("name")
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    public String name = "";

    @SerializedName("patronus")
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    public String patronus;

    @SerializedName("species")
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    public String species;

    @SerializedName("wand")
    @Ignore
    @Nullable
    public Wand wand;

    @SerializedName("yearOfBirth")
    @ColumnInfo(typeAffinity = INTEGER)
    @Nullable
    @TypeConverters({ IntConverter.class })
    public String yearOfBirth;

    public Person() { personUUID = UUID.randomUUID(); }

    protected Person(Parcel in) {
        personId = in.readLong();
        personUUID = UUID.fromString(in.readString());
        actor = in.readString();
        alive = in.readByte() != 0;
        ancestry = in.readString();
        dateOfBirth = in.readString();
        eyeColour = in.readString();
        gender = in.readString();
        hairColour = in.readString();
        hogwartsStaff = in.readByte() != 0;
        hogwartsStudent = in.readByte() != 0;
        house = in.readString();
        localImage = in.readString();
        internetImage = in.readString();
        name = in.readString();
        patronus = in.readString();
        species = in.readString();
        wand = in.readParcelable(Wand.class.getClassLoader());
        yearOfBirth = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @NonNull
    public String aliveString() {

        final boolean isMale = gender.equals(Genders.male);

        if (alive) return isMale ? "Жив" : "Жива";
        else return isMale ? "Мёртв" : "Мертва";
    }

    public String getGender() {
        return gender.equals(Genders.male) ? "Мужской" : "Женский";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(personId);
        dest.writeString(personUUID.toString());
        dest.writeString(actor);
        dest.writeByte((byte) (alive ? 1 : 0));
        dest.writeString(ancestry);
        dest.writeString(dateOfBirth);
        dest.writeString(eyeColour);
        dest.writeString(gender);
        dest.writeString(hairColour);
        dest.writeByte((byte) (hogwartsStaff ? 1 : 0));
        dest.writeByte((byte) (hogwartsStudent ? 1 : 0));
        dest.writeString(house);
        dest.writeString(localImage);
        dest.writeString(internetImage);
        dest.writeString(name);
        dest.writeString(patronus);
        dest.writeString(species);
        dest.writeParcelable(wand, flags);
        dest.writeString(yearOfBirth);
    }
}