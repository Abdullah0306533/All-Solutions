package com.projects.solutionpack.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.projects.solutionpack.repository.AuthenticationRepository;

public class MyViewModel extends ViewModel {
    private final AuthenticationRepository repository;

    public MyViewModel(Context context) {
        repository = new AuthenticationRepository(context);
    }

    public void getSignup() {
        repository.getSignUp();
    }

    public void getSignIn(String email,String password){repository.signIn(email, password);}

    public void getSignOut(){repository.signOut();}
}
