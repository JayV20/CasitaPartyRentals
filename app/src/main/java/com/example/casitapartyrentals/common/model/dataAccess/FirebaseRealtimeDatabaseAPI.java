package com.example.casitapartyrentals.common.model.dataAccess;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRealtimeDatabaseAPI {
    public static final String PATH_USERS="users";

    private DatabaseReference mDatabase;
    private static class SingletonHolder{
        private static final FirebaseRealtimeDatabaseAPI  INSTANCE= new FirebaseRealtimeDatabaseAPI();
    }
    public static FirebaseRealtimeDatabaseAPI getInstance(){
        return SingletonHolder.INSTANCE;
    }
    private FirebaseRealtimeDatabaseAPI() {
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }
    /*
     *   References
     * */
    public DatabaseReference getRootReference(){
        return mDatabase.getRoot();
    }
    public DatabaseReference getUserReferenceByUid(String uid){
        return getRootReference().child(PATH_USERS).child(uid);
    }

}
