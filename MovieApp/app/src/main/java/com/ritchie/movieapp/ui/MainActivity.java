package com.ritchie.movieapp.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.ritchie.movieapp.BuildConfig;
import com.ritchie.movieapp.R;
import com.ritchie.movieapp.api.ApiService;
import com.ritchie.movieapp.ui.watched_movies.WatchedMoviesFragment;
import com.ritchie.movieapp.utils.AppConstants;

import java.util.Objects;

import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView nvView;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupApiService();
        setupDrawer();
        navigateToScreen(AppConstants.SCREEN_MOVIE_SEARCH);
    }

    private void setupApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private void setupDrawer() {
        mToolbar = findViewById(R.id.toolbar);
        nvView = findViewById(R.id.nv_menu);
        nvView.setNavigationItemSelectedListener(this);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        mDrawerLayout = findViewById(R.id.dl_navigation_drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        Drawable toolbarDrawable = ContextCompat.getDrawable(this, R.drawable.ic_baseline_menu_24);
        actionBarDrawerToggle.setToolbarNavigationClickListener(v -> {
            if (mDrawerLayout.isDrawerOpen(nvView)) {
                mDrawerLayout.closeDrawers();
            } else {
                mDrawerLayout.openDrawer(nvView);
            }
        });
        mToolbar.setNavigationIcon(toolbarDrawable);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (mDrawerLayout.isDrawerOpen(nvView))
            mDrawerLayout.closeDrawer(nvView);
        if (menuItem.getItemId() == R.id.nav_movie_search) {
            navigateToScreen(AppConstants.SCREEN_MOVIE_SEARCH);
            return true;
        } else if (menuItem.getItemId() == R.id.nav_movie_details) {
            navigateToScreen(AppConstants.SCREEN_MOVIE_DETAILS);
            return true;
        } else if (menuItem.getItemId() == R.id.nav_watched_movies) {
            navigateToScreen(AppConstants.SCREEN_WATCHED_MOVIES);
            return true;
        } else if (menuItem.getItemId() == R.id.nav_settings) {
            navigateToScreen(AppConstants.SCREEN_SETTINGS);
            return true;
        }
        return false;
    }

    private void navigateToScreen(int screenMovieSearch) {
        Fragment fragment;
        switch (screenMovieSearch) {
            case AppConstants.SCREEN_MOVIE_DETAILS:
                fragment = new MovieDetailsFragment(apiService);
                break;
            case AppConstants.SCREEN_MOVIE_SEARCH:
                fragment = new MovieSearchFragment(apiService);
                break;
            case AppConstants.SCREEN_SETTINGS:
                fragment = new SettingsFragment(apiService);
                break;
            default:
                fragment = new WatchedMoviesFragment(apiService);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}