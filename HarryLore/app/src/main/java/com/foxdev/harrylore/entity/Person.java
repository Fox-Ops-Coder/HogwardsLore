package com.foxdev.harrylore.entity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.versionedparcelable.NonParcelField;

import com.foxdev.harrylore.sql.util.BooleanConverter;
import com.foxdev.harrylore.sql.util.DrawableConverter;
import com.foxdev.harrylore.sql.util.IntConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ColumnInfo.BLOB;
import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ColumnInfo.TEXT;

@Entity(tableName = "Persons")
public final class Person implements Parcelable
{
    private static final int yearOfBirthNotInitiated = -1;

    //region    [Поля]
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(typeAffinity = INTEGER)
    private int personId;

    @SerializedName("actor")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    private String actor;

    @SerializedName("alive")
    @Expose
    @ColumnInfo(typeAffinity = INTEGER)
    @TypeConverters({ BooleanConverter.class })
    private boolean alive;

    @SerializedName("ancestry")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    private String ancestry;

    @SerializedName("dateOfBirth")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    @TypeConverters({ IntConverter.class })
    private String dateOfBirth;

    @SerializedName("eyeColour")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    private String eyeColour;

    @SerializedName("gender")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    private String gender;

    @SerializedName("hairColour")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    private String hairColour;

    @SerializedName("hogwartsStaff")
    @Expose
    @ColumnInfo(typeAffinity = INTEGER)
    @TypeConverters({ BooleanConverter.class })
    private boolean hogwartsStaff;

    @SerializedName("hogwartsStudent")
    @Expose
    @ColumnInfo(typeAffinity = INTEGER)
    @TypeConverters({ BooleanConverter.class })
    private boolean hogwartsStudent;

    @SerializedName("house")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    private String house;

    @SerializedName("image")
    @Expose
    @Ignore
    @NonParcelField
    @NonNull
    private String image;

    @ColumnInfo(typeAffinity = BLOB)
    @NonNull
    @TypeConverters({ DrawableConverter.class })
    private Drawable imageBytes;

    @SerializedName("name")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @NonNull
    private String name;

    @SerializedName("patronus")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    private String patronus;

    @SerializedName("species")
    @Expose
    @ColumnInfo(typeAffinity = TEXT)
    @Nullable
    private String species;

    @SerializedName("wand")
    @Expose
    @Ignore
    private Wand wand;

    @SerializedName("yearOfBirth")
    @Expose
    @ColumnInfo(typeAffinity = INTEGER)
    @Nullable
    @TypeConverters({ IntConverter.class })
    private String yearOfBirth;
    //endregion

    //region    [Геттеры и сеттеры]
    public int getPersonId() { return personId; }

    public void setPersonId(int personId) { this.personId = personId; }

    @NonNull
    public String getActor() { return actor; }

    public void setActor(@NonNull String actor) { this.actor = actor; }

    public boolean getAlive() { return alive; }

    public String getAliveString() { return alive ? "Жив" : "Мёртв"; }

    public void setAlive(boolean alive) { this.alive = alive; }

    @NonNull
    public String getAncestry() { return ancestry; }

    public void setAncestry(@NonNull String ancestry) { this.ancestry = ancestry; }

    @NonNull
    public String getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(@NonNull String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    @NonNull
    public String getEyeColour() { return eyeColour; }

    public void setEyeColour(@NonNull String eyeColour) { this.eyeColour = eyeColour; }

    @NonNull
    public String getGender() { return gender; }

    public void setGender(@NonNull String gender) { this.gender = gender; }

    @NonNull
    public String getHairColour() { return hairColour; }

    public void setHairColour(@NonNull String hairColour) { this.hairColour = hairColour; }

    public boolean getHogwartsStaff() { return hogwartsStaff; }

    public void setHogwartsStaff(boolean hogwartsStaff) { this.hogwartsStaff = hogwartsStaff; }

    public boolean getHogwartsStudent() { return hogwartsStudent; }

    public void setHogwartsStudent(boolean hogwartsStudent) { this.hogwartsStudent = hogwartsStudent; }

    @NonNull
    public String getHouse() { return house; }

    public void setHouse(@NonNull String home) { this.house = home; }

    @NonNull
    public String getImage() { return image; }

    public void setImage(@NonNull String image) { this.image = image; }

    @NonNull
    public Drawable getImageBytes() { return imageBytes; }

    public void setImageBytes(@NonNull Drawable imageBytes) { this.imageBytes = imageBytes; }

    @NonNull
    public String getName() { return name; }

    public void setName(@NonNull String name) { this.name = name; }

    @NonNull
    public String getPatronus() { return patronus; }

    public void setPatronus(@NonNull String patronus) { this.patronus = patronus; }

    @NonNull
    public String getSpecies() { return species; }

    public void setSpecies(@NonNull String species) { this.species = species; }

    public Wand getWand() { return wand; }

    public void setWand(Wand wand) { this.wand = wand; }

    @Nullable
    public String getYearOfBirth() { return yearOfBirth; }

    public void setYearOfBirth(@Nullable String yearOfBirth) { this.yearOfBirth = yearOfBirth; }
    //endregion

    //region    [Конструкторы]
    public Person()
    {
        actor = "";
        ancestry = "";
        dateOfBirth = "";
        eyeColour = "";
        gender = "";
        hairColour = "";
        house = "";
        image = "";
        name = "";
        patronus = "";
        species = "";
        imageBytes = null;
    }

    @Ignore
    public Person(Parcel in)
    {
        actor = in.readString();
        ancestry = in.readString();
        dateOfBirth = in.readString();
        eyeColour = in.readString();
        gender = in.readString();
        hairColour = in.readString();
        house = in.readString();
        name = in.readString();
        patronus = in.readString();
        species = in.readString();

        byte[] imageBytes = new byte[0];
        in.readByteArray(imageBytes);
        this.imageBytes = new DrawableConverter().ByteToDrawable(imageBytes);

        personId = in.readInt();
        alive = in.readInt() != 0;
        hogwartsStaff = in.readInt() != 0;
        hogwartsStudent = in.readInt() != 0;

        this.yearOfBirth = in.readString();

        if (in.dataAvail() != 0) wand = in.readParcelable(Wand.class.getClassLoader());
        else wand = null;

        image = "";
    }
    //endregion

    //region    [Parcelable]
    @Ignore
    public static final Creator<Person> CREATOR = new Creator<Person>()
    {
        @Override
        public Person createFromParcel(Parcel source) { return new Person(source); }

        @Override
        public Person[] newArray(int size) { return new Person[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(actor);
        dest.writeString(ancestry);
        dest.writeString(dateOfBirth);
        dest.writeString(eyeColour);
        dest.writeString(gender);
        dest.writeString(hairColour);
        dest.writeString(house);
        dest.writeString(name);
        dest.writeString(patronus);
        dest.writeString(species);

        byte[] imageBytes = new DrawableConverter().DrawableToByte(this.imageBytes);
        dest.writeByteArray(imageBytes, 0, imageBytes.length);
        dest.writeInt(personId);
        dest.writeInt(alive ? 1 : 0);
        dest.writeInt(hogwartsStaff ? 1 : 0);
        dest.writeInt(hogwartsStudent ? 1 : 0);

        dest.writeString(yearOfBirth);

        if (wand != null) dest.writeParcelable(wand, flags);
    }
    //endregion
}