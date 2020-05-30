package com.example.casitapartyrentals.mainModule;

import com.example.casitapartyrentals.mainModule.events.MainEvent;
import com.example.casitapartyrentals.mainModule.model.MainInteractor;
import com.example.casitapartyrentals.mainModule.model.MainInteractorClass;
import com.example.casitapartyrentals.mainModule.view.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainPresenterClass implements MainPresenter {
    private MainView mView;
    private MainInteractor mInteractor;

    public MainPresenterClass(MainView mView) {
        this.mView = mView;
        this.mInteractor=new MainInteractorClass();
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        mView.showProgress();
    }

    @Override
    public void onResume() {
        if (mView!=null){
            mInteractor.subscribeToMueble();
        }
    }

    @Override
    public void onPause() {
        if (mView!=null){
            mInteractor.unsubscribeToMueble();
        }
    }

    @Override
    public void onDestroy() {
        mView=null;
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    @Override
    public void onEvent(MainEvent event) {
        switch (event.getTypeEvent()){
            case MainEvent.MUEBLES_SUCCESS:
                mView.hideProgress();
                mView.getMuebles(event.getMuebles());
                break;
            case MainEvent.ERROR_NETWORK:
            case MainEvent.ERROR_DISCONNECT:
                mView.showMessage(event.getResMsg());
                break;
        }
    }
}
