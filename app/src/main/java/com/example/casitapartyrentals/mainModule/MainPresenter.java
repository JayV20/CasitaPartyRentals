package com.example.casitapartyrentals.mainModule;

import com.example.casitapartyrentals.mainModule.events.MainEvent;

public interface MainPresenter {
    void onCreate();
    void onResume();
    void onPause();
    void onDestroy();

    void onEvent(MainEvent event);
}
