package huce.fit.mvvmpattern.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import huce.fit.mvvmpattern.api.AccountService;
import huce.fit.mvvmpattern.model.Account;
import huce.fit.mvvmpattern.model.DataJson;
import huce.fit.mvvmpattern.utils.Status;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private Application application;
    private MutableLiveData<Integer> loginStatus = new MutableLiveData<>();
    private MutableLiveData<Integer> signUpStatus = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<DataJson<Account>> acc = new MutableLiveData<>();

    public AccountRepository(Application application) {
        this.application = application;
    }
    public AccountRepository(){}

    public MutableLiveData<Integer> getLoginStatus(Account account) {
        if (account.getUsername()!=null && account.getPassword()!=null) {
            AccountService.accountService.checkAccount(account)
                    .enqueue(new Callback<DataJson<Account>>() {
                        @Override
                        public void onResponse(Call<DataJson<Account>> call, Response<DataJson<Account>> response) {
                            DataJson<Account> dataJson = response.body();
                            if (dataJson != null) {
                                message.setValue(dataJson.getMessage());
                                if (dataJson.isStatus() == true) {

                                    loginStatus.setValue(Status.loginSuccess);
                                    Status.current_username = account.getUsername();
                                }
                                else {
                                    loginStatus.setValue(Status.loginFail);
                                }
                            }
                            else {
                                loginStatus.setValue(Status.loginFail);
                            }
                        }

                        @Override
                        public void onFailure(Call<DataJson<Account>> call, Throwable t) {
                            Log.e("ERROR", this.getClass().getName()+"onClickLogin()->onFailure: "+t.getMessage());
                            loginStatus.setValue(Status.loginFail);
                        }
                    });
        }
        return loginStatus;
    }

    // dont use binding
    public MutableLiveData<Integer> getLoginStatus2(Account account) {
        if (account.getUsername()!=null && account.getPassword()!=null && account.getPassword()!="") {
            AccountService.accountService2.checkAccount2(account)
                    .enqueue(new Callback<DataJson<Account>>() {
                        @Override
                        public void onResponse(Call<DataJson<Account>> call, Response<DataJson<Account>> response) {
                            DataJson<Account> dataJson = response.body();
                            if (dataJson != null) {
                                message.setValue(dataJson.getMessage());
                                if (dataJson.isStatus() == true) {
                                    Log.e("status",dataJson.isStatus()+"|"+dataJson.getMessage().toString());
                                    loginStatus.setValue(Status.correctPassword);

                                }
                                else {
                                    Log.e("status",dataJson.isStatus()+"|"+dataJson.getMessage().toString());
                                    loginStatus.setValue(Status.incorrectPassword);
                                }
                            }
                            else {
                                Log.e("status",dataJson.isStatus()+"|"+dataJson.getMessage().toString());
                                loginStatus.setValue(Status.updateFail);
                            }
                        }

                        @Override
                        public void onFailure(Call<DataJson<Account>> call, Throwable t) {
                            Log.e("ERROR", this.getClass().getName()+"onClickLogin()->onFailure: "+t.getMessage());
                            loginStatus.setValue(Status.updateFail);
                        }
                    });
        }
        else{
            loginStatus.setValue(Status.emptyPassword);
        }
        return loginStatus;
    }


    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus.setValue(loginStatus);
    }

    public MutableLiveData<Integer> getSignUpStatus (Account account) {
        if (account.getUsername() != null && account.getPassword() != null) {
            AccountService.accountService.addAccount(account)
                    .enqueue(new Callback<DataJson<Account>>() {
                        @Override
                        public void onResponse(Call<DataJson<Account>> call, Response<DataJson<Account>> response) {
                            DataJson<Account> dataJson = response.body();
                            if (dataJson != null) {
                                message.setValue(dataJson.getMessage());
                                if (dataJson.isStatus() == true) {
                                    signUpStatus.setValue(Status.signUpSuccess);
                                }
                                else {
                                    loginStatus.setValue(Status.signUpFail);
                                }
                            }
                            else {
                                loginStatus.setValue(Status.signUpFail);
                            }
                        }

                        @Override
                        public void onFailure(Call<DataJson<Account>> call, Throwable t) {
                            Log.e("ERROR", this.getClass().getName()+"onClickLogin()->onFailure: "+t.getMessage());
                            signUpStatus.setValue(Status.signUpFail);
                        }
                    });
        }
        return signUpStatus;
    }

    public MutableLiveData<DataJson<Account>> changePassword(Account account){
        if(account.getPassword()!=null && account.getUsername() != null){
            AccountService.accountService2.updateAccount2(account)
                    .enqueue(new Callback<DataJson<Account>>() {
                        @Override
                        public void onResponse(Call<DataJson<Account>> call, Response<DataJson<Account>> response) {
                            DataJson<Account> dj = response.body();
                            if(dj!=null){
                                message.setValue(dj.getMessage());
                                if(dj.isStatus() == true) {
                                    acc.setValue(dj);
                                    signUpStatus.setValue(Status.updateSuccess);
                                }
                                else{
                                    signUpStatus.setValue(Status.updateFail);
                                }
                            }
                            else{
                                signUpStatus.setValue(Status.updateFail);
                            }
                        }

                        @Override
                        public void onFailure(Call<DataJson<Account>> call, Throwable t) {
                            Log.e("ERROR", this.getClass().getName()+"->onFailure: "+t.getMessage());
                            signUpStatus.setValue(Status.updateFail);
                        }
                    });
        }
        return acc;
    }
    public void setSignUpStatus (Integer signUpStatus) {
        this.signUpStatus.setValue(signUpStatus);
    }

    public MutableLiveData<String> getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message.setValue(message);
    }
}
