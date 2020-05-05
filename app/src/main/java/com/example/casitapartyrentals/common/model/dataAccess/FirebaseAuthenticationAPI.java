package com.example.casitapartyrentals.common.model.dataAccess;

import com.example.casitapartyrentals.common.pojo.User;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthenticationAPI {
    private  FirebaseAuth mFirebaseAuth;
    private static class SingletonHolder{
        private static final FirebaseAuthenticationAPI INSTANCE= new FirebaseAuthenticationAPI();
    }

    private FirebaseAuthenticationAPI() {
        mFirebaseAuth= FirebaseAuth.getInstance();
    }

    public FirebaseAuth getmFirebaseAuth() {
        return this.mFirebaseAuth;
    }

    public static FirebaseAuthenticationAPI getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public User getAuthUser() {
        User user = new User();
        if (mFirebaseAuth != null && mFirebaseAuth.getCurrentUser() != null){
            user.setUid(mFirebaseAuth.getCurrentUser().getUid());
            user.setUsername(mFirebaseAuth.getCurrentUser().getDisplayName());
            user.setEmail(mFirebaseAuth.getCurrentUser().getEmail());
            user.setUri(mFirebaseAuth.getCurrentUser().getPhotoUrl());
        }
        return user;
    }

}
