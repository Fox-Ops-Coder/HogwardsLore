package com.foxdev.hogwartslore.modules;

import android.app.Application;

import androidx.room.Room;

import com.foxdev.hogwartslore.data.sql.HogwartsDb;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public final class DbModule {

    @Provides
    public HogwartsDb provideAppDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                HogwartsDb.class,
                "HogwartsDb")
                .build();
    }
}
