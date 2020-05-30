package com.example.casitapartyrentals.mainModule.events;

import com.example.casitapartyrentals.common.pojo.Mueble;

import java.util.ArrayList;

public class MainEvent {
    public static final int MUEBLES_SUCCESS= 0;
    public static final int ERROR_NETWORK=100;
    public static final int ERROR_DISCONNECT=101;

    private ArrayList<Mueble> muebles;
    private int typeEvent;
    private int resMsg;

    public MainEvent() {
    }

    public ArrayList<Mueble> getMuebles() {
        return muebles;
    }

    public void setMuebles(ArrayList<Mueble> muebles) {
        this.muebles = muebles;
    }

    public int getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(int typeEvent) {
        this.typeEvent = typeEvent;
    }

    public int getResMsg() {
        return resMsg;
    }

    public void setResMsg(int resMsg) {
        this.resMsg = resMsg;
    }
}
