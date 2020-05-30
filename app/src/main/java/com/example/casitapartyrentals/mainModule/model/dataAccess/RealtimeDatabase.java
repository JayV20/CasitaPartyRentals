package com.example.casitapartyrentals.mainModule.model.dataAccess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.casitapartyrentals.R;
import com.example.casitapartyrentals.common.model.dataAccess.FirebaseRealtimeDatabaseAPI;
import com.example.casitapartyrentals.common.pojo.Mueble;
import com.example.casitapartyrentals.mainModule.events.MainEvent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class RealtimeDatabase {
    private FirebaseRealtimeDatabaseAPI mDatabaseAPI;
    private ChildEventListener listener;

    public RealtimeDatabase() {
        mDatabaseAPI= FirebaseRealtimeDatabaseAPI.getInstance();
    }
    public void subscribeToMueble(ChildMuebleCallback callback){
        if (listener==null){
            listener= new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                     callback.onAdd(getMuebles(dataSnapshot));
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    callback.onChange(getMuebles(dataSnapshot));
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    switch (databaseError.getCode()){
                        case DatabaseError.NETWORK_ERROR:
                            callback.onError(MainEvent.ERROR_NETWORK, R.string.error_network);
                            break;
                        case DatabaseError.DISCONNECTED:
                            callback.onError(MainEvent.ERROR_DISCONNECT,R.string.error_disconnected);
                            break;
                    }
                }
            };
            mDatabaseAPI.getMueblesReference().addChildEventListener(listener);
        }
    }

    private Mueble getMuebles(DataSnapshot dataSnapshot) {
        Mueble mueble= dataSnapshot.getValue(Mueble.class);
        if (mueble!=null){
            mueble.setId(dataSnapshot.getKey());
        }
        return mueble;
    }

    public void unsubscribeToMueble(){
        if (listener!=null){
        mDatabaseAPI.getMueblesReference().removeEventListener(listener);
        }
    }
}
