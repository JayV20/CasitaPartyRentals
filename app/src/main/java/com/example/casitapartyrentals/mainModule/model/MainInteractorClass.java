package com.example.casitapartyrentals.mainModule.model;

import com.example.casitapartyrentals.common.pojo.Mueble;
import com.example.casitapartyrentals.mainModule.events.MainEvent;
import com.example.casitapartyrentals.mainModule.model.dataAccess.Authentication;
import com.example.casitapartyrentals.mainModule.model.dataAccess.ValueMuebleCallback;
import com.example.casitapartyrentals.mainModule.model.dataAccess.RealtimeDatabase;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class MainInteractorClass implements MainInteractor {
    private Authentication mAuthentication;
    private RealtimeDatabase mDatabase;

    public MainInteractorClass() {
        mDatabase= new RealtimeDatabase();
        mAuthentication= new Authentication();
    }

    @Override
    public void subscribeToMueble() {
        mDatabase.subscribeToMueble(new ValueMuebleCallback() {
            @Override
            public void onChange(ArrayList<Mueble> muebles) {
                post(MainEvent.MUEBLES_SUCCESS,muebles);
            }

            @Override
            public void onError(int typeEvent, int resMsg) {
                post(typeEvent,resMsg);
            }
        });
    }

    private void post(int typeEvent, int resMsg, ArrayList<Mueble> muebles){
        MainEvent event= new MainEvent();
        event.setTypeEvent(typeEvent);
        event.setResMsg(resMsg);
        event.setMuebles(muebles);
        EventBus.getDefault().post(event);
    }
    private void post(int typeEvent, ArrayList<Mueble> muebles) {
        post(typeEvent,0,muebles);
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
