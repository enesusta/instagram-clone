package com.enesusta.instagramclone.activities.daggertut;

import dagger.Component;

@Component(modules = {WheelsModule.class,DieselEngineModule.class})
public interface CarComponent {

    Car getCar();
    void inject(Dagger dagger);

}
