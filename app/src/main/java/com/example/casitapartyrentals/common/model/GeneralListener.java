package com.example.casitapartyrentals.common.model;

public interface GeneralListener {
    void onSuccess();
    void onError( int typeEvent,int resMsg);
}
