package com.example.casitapartyrentals.mainModule.model.dataAccess;

import com.example.casitapartyrentals.common.pojo.Mueble;

public interface ChildMuebleCallback {
    void onAdd(Mueble mueble);
    void onChange(Mueble mueble);
    void onError(int typeEvent, int resMsg);
}
