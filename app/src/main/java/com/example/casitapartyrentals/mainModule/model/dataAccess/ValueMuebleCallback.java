package com.example.casitapartyrentals.mainModule.model.dataAccess;

import com.example.casitapartyrentals.common.pojo.Mueble;

import java.util.ArrayList;

public interface ValueMuebleCallback {

    void onChange(ArrayList<Mueble> muebles);

    void onError(int typeEvent, int resMsg);
}
