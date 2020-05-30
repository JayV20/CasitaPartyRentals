package com.example.casitapartyrentals.mainModule.model.dataAccess;

import androidx.annotation.NonNull;

import com.example.casitapartyrentals.R;
import com.example.casitapartyrentals.common.model.dataAccess.FirebaseRealtimeDatabaseAPI;
import com.example.casitapartyrentals.common.pojo.Mueble;
import com.example.casitapartyrentals.mainModule.events.MainEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RealtimeDatabase {
    private FirebaseRealtimeDatabaseAPI mDatabaseAPI;
    private ValueEventListener listener;

    public RealtimeDatabase() {
        mDatabaseAPI= FirebaseRealtimeDatabaseAPI.getInstance();
    }
    public void subscribeToMueble(ValueMuebleCallback callback){
        if (listener==null){
            listener= new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    callback.onChange(getMuebles(dataSnapshot));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    switch (databaseError.getCode()){
                        case DatabaseError.DISCONNECTED:
                            callback.onError(MainEvent.ERROR_DISCONNECT, R.string.error_disconnected);
                            break;
                        case DatabaseError.NETWORK_ERROR:
                            callback.onError(MainEvent.ERROR_NETWORK,R.string.error_network);
                            break;
                    }
                }
            };
            mDatabaseAPI.getMueblesReference().addValueEventListener(listener);
        }
    }

    private ArrayList<Mueble> getMuebles(DataSnapshot dataSnapshot) {
        ArrayList<Mueble> muebles= new ArrayList<Mueble>();
        for (DataSnapshot snapshot: dataSnapshot.getChildren()){
            Mueble mueble= snapshot.getValue(Mueble.class);
            if (mueble!=null){
                mueble.setId(dataSnapshot.getKey());
                muebles.add(mueble);
            }
        }

        return muebles;
    }

    public void unsubscribeToMueble(){
        if (listener!=null){
        mDatabaseAPI.getMueblesReference().removeEventListener(listener);
        }
    }
}
