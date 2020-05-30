package com.example.casitapartyrentals.mainModule.model;

import com.example.casitapartyrentals.common.pojo.Mueble;
import com.example.casitapartyrentals.mainModule.events.MainEvent;
import com.example.casitapartyrentals.mainModule.model.dataAccess.Authentication;
import com.example.casitapartyrentals.mainModule.model.dataAccess.ChildMuebleCallback;
import com.example.casitapartyrentals.mainModule.model.dataAccess.RealtimeDatabase;

import org.greenrobot.eventbus.EventBus;

public class MainInteractorClass implements MainInteractor {
    private Authentication mAuthentication;
    private RealtimeDatabase mDatabase;

    public MainInteractorClass() {
        mDatabase= new RealtimeDatabase();
        mAuthentication= new Authentication();
    }

    @Override
    public void subscribeToMueble() {
        mDatabase.subscribeToMueble(new ChildMuebleCallback() {
            @Override
            public void onAdd(Mueble mueble) {
                post(MainEvent.SUCCESS_MUEBLES,mueble);
            }

            @Override
            public void onChange(Mueble mueble) {
                post(MainEvent.SUCCESS_MUEBLES,mueble);
            }

            @Override
            public void onError(int typeEvent, int resMsg) {
                post(typeEvent,resMsg);
            }
        });
    }

    private void post(int typeEvent, int resMsg, Mueble mueble){
        MainEvent event= new MainEvent();
        event.setTypeEvent(typeEvent);
        event.setResMsg(resMsg);
        event.setMueble(mueble);
        EventBus.getDefault().post(event);
    }
    private void post(int typeEvent, Mueble mueble) {
        post(typeEvent,0,mueble);
    }
    private void post(int typeEvent, int resMsg){
        post(typeEvent,resMsg,null);
    }

    @Override
    public void unsubscribeToMueble() {
        mDatabase.unsubscribeToMueble();
    }

    @Override
    public void logout() {
        mAuthentication.logout();
    }
}
