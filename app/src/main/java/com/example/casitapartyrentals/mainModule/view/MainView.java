package com.example.casitapartyrentals.mainModule.view;

import com.example.casitapartyrentals.common.pojo.Mueble;

public interface MainView {
    void showProgress();
    void hideProgress();

    void showMessage(int resMsg);
    void getMuebles(Mueble mueble);
    void showDialogConfirm();

}