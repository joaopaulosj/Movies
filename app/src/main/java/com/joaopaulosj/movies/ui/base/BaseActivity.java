package com.joaopaulosj.movies.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.joaopaulosj.movies.BaseApplication;
import com.joaopaulosj.movies.R;
import com.joaopaulosj.movies.di.component.ApplicationComponent;

/**
 * Created by jpsja_000 on 19/08/2017.
 */

public class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private LifecycleRegistry mLifecyleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected boolean onCreateOptionsMenu(Menu menu, int... idsToSetVisible) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);
        for (int id : idsToSetVisible) {
            MenuItem menuItem = menu.findItem(id);
            if (menuItem != null) menuItem.setVisible(true);
        }
        return true;
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

    protected ApplicationComponent applicationComponent() {
        return ((BaseApplication) getApplication()).getComponent();
    }

    protected void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    protected void setActionBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(title);
    }

    protected void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
