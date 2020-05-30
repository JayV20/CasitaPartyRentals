package com.example.casitapartyrentals.mainModule.view;

import com.example.casitapartyrentals.common.pojo.Mueble;

import java.util.ArrayList;

public interface MainView {
    void showProgress();
    void hideProgress();

    void showMessage(int resMsg);
    void getMuebles(ArrayList<Mueble> muebles);
    //void showDialogConfirm();

}