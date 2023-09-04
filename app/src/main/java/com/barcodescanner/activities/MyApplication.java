package com.barcodescanner.activities;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.barcodescanner.utils.AdUtils;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback;

public class MyApplication extends Application {
    public AppOpenAdManager appOpenAdManager;
    private static MyApplication mInstance;

    public class AppOpenAdManager {
        private static final String LOG_TAG = "hahaha";
        private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/3419835294";

        private AppOpenAd appOpenAd = null;
        private boolean isLoadingAd = false;
        private boolean isShowingAd = false;

        /**
         * Constructor.
         */
        public AppOpenAdManager() {
        }

        public void loadAd(Context context) {
            // Do not load ad if there is an unused ad or one is already loading.
            if (isLoadingAd || isAdAvailable() || !AdUtils.AD_ALLOWED) {
                return;
            }

            isLoadingAd = true;
            AdRequest request = new AdRequest.Builder().build();
            AppOpenAd.load(
                    context, AD_UNIT_ID, request,
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                    new AppOpenAdLoadCallback() {
                        @Override
                        public void onAdLoaded(AppOpenAd ad) {
                            // Called when an app open ad has loaded.
                            Log.d(LOG_TAG, "Ad was loaded.");
                            appOpenAd = ad;
                            isLoadingAd = false;

                        }

                        @Override
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            // Called when an app open ad has failed to load.
                            Log.d(LOG_TAG, loadAdError.getMessage());
                            isLoadingAd = false;
                        }
                    });
        }


        /**
         * Check if ad exists and can be shown.
         */
        private boolean isAdAvailable() {
            return appOpenAd != null;
        }

        /**
         * Shows the ad if one isn't already showing.
         */
        public void showAdIfAvailable(
                @NonNull final Activity activity, Intent nextActivityIntent) {
            // If the app open ad is already showing, do not show the ad again.
            if (isShowingAd) {
                Log.d(LOG_TAG, "The app open ad is already showing.");
                return;
            }

            // If the app open ad is not available yet, invoke the callback then load the ad.
            if (!isAdAvailable()) {
                Log.d(LOG_TAG, "The app open ad is not ready yet.");
//                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
                if (nextActivityIntent != null) {
                    activity.startActivity(nextActivityIntent);
                }
                return;
            }

            appOpenAd.setFullScreenContentCallback(
                    new FullScreenContentCallback() {

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when fullscreen content is dismissed.
                            // Set the reference to null so isAdAvailable() returns false.
                            Log.d(LOG_TAG, "Ad dismissed fullscreen content.");
                            appOpenAd = null;
                            isShowingAd = false;
                            if (nextActivityIntent != null) {
                                activity.startActivity(nextActivityIntent);
                            }
//                    onShowAdCompleteListener.onShowAdComplete();
                            loadAd(activity);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when fullscreen content failed to show.
                            // Set the reference to null so isAdAvailable() returns false.
                            Log.d(LOG_TAG, adError.getMessage());
                            appOpenAd = null;
                            isShowingAd = false;

//                    onShowAdCompleteListener.onShowAdComplete();
                            loadAd(activity);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Called when fullscreen content is shown.
                            Log.d(LOG_TAG, "Ad showed fullscreen content.");
                        }
                    });
            isShowingAd = true;
            appOpenAd.show(activity);
        }

    }

    /**
     * Interface definition for a callback to be invoked when an app open ad is complete.
     */
    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this);
        appOpenAdManager = new AppOpenAdManager();
        appOpenAdManager.loadAd(this);
        AdUtils.loadInterstitialAd(this);
        if (mInstance == null) {
            mInstance = this;
        }
    }

    public static synchronized MyApplication getInstance() {
        MyApplication myApplication;
        synchronized (MyApplication.class) {
            myApplication = mInstance;
        }
        return myApplication;
    }
}
