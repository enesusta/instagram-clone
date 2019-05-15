package com.enesusta.instagramclone.activities.daggertut;

import dagger.Module;
import dagger.Provides;

@Module
public class PetrolEngineModule {

    @Provides
    Engine provideEngine(PetrolEngine engine) {
        return engine;
    }


}
