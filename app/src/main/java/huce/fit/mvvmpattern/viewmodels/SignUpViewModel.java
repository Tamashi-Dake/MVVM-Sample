package huce.fit.mvvmpattern.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import huce.fit.mvvmpattern.model.LoginUser;
import huce.fit.mvvmpattern.utils.Status;
import huce.fit.mvvmpattern.utils.ValidatorUtil;

public class SignUpViewModel extends ViewModel {

    private MutableLiveData<Integer> LoginStatus = new MutableLiveData<>();

//    public void onclickSignUp() {
//        try {
//            if (validateData()) {
////               //call api login here
//                LoginStatus.setValue(Status.loginSuccess);
//
//            }
//        } catch (Exception ex) {
//            Log.e("Login viewmodel",ex.getMessage());
//        }
//
//    }
//    private boolean validateData() {
//        try {
//            if (ValidatorUtil.emptyValue(loginUser.getUsername())) {
//                LoginStatus.setValue(Status.emptyUsername);
//                return false;
//            } else if (ValidatorUtil.emptyValue(loginUser.getPassword())) {
//                LoginStatus.setValue(Status.emptyPassWord);
//                return false;
//            }
//        } catch (Exception ex) {
//            Log.e("Validate viewmodel",ex.getMessage());
//        }
//        return true;
//    }

}