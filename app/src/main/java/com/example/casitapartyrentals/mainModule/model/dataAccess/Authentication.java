package com.example.casitapartyrentals.mainModule.model.dataAccess;

import com.example.casitapartyrentals.common.model.dataAccess.FirebaseAuthenticationAPI;

public class Authentication {
    private FirebaseAuthenticationAPI mAuthenticationAPI;

    public Authentication() {
        mAuthenticationAPI = FirebaseAuthenticationAPI.getInstance();
    }
    public void logout(){
        mAuthenticationAPI.logout();
    }
}
