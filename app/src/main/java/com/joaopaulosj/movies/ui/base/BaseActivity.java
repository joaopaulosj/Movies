package com.joaopaulosj.movies.ui.base;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.joaopaulosj.movies.BaseApplication;
import com.joaopaulosj.movies.R;
import com.joaopaulosj.movies.di.component.ApplicationComponent;
import com.joaopaulosj.movies.ui.utils.DialogHelper;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private LifecycleRegistry mLifecyleRegistry = new LifecycleRegistry(this);
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecyleRegistry;
    }

    protected ApplicationComponent applicationComponent(){
        return ((BaseApplication)getApplication()).getComponent();
    }

    public void setActionBar(String title, boolean displayHomeAsUpEnabled) {
        setActionBar(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
    }

    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    //ACTION BAR METHODS
    public void setActionBar(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(title);
    }

    //PROGRESS DIALOG METHODS
    private void showProgressDialog() {
        mProgressDialog = (mProgressDialog == null) ? DialogHelper.createProgressDialog(this) : mProgressDialog;
        mProgressDialog.show();
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public void showProgressDialog(boolean show) {
        if (show) {
            showProgressDialog();
        } else {
            dismissProgressDialog();
        }
    }
}
