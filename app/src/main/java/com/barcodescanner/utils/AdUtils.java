package com.barcodescanner.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;

import androidx.annotation.NonNull;

import com.barcodescanner.BuildConfig;
import com.barcodescanner.R;
import com.barcodescanner.activities.SplashActivity;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class AdUtils {
    static InterstitialAd mInterstitialAd;
    static ProgressDialog progressDialog;
    static RewardedAd rewardedAd;
    static boolean isRewardEarned;
    public static boolean AD_ALLOWED = true;

    public static void loadBannerAd(AdView adView) {
        if (!AD_ALLOWED) return;
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public static void showInterstitialAd(Activity activity) {
        if (!AD_ALLOWED) {
            return;
        }

        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    loadInterstitialAd(activity);
                }
            });
        } else {
            loadInterstitialAd(activity);
        }
    }

    public static void loadInterstitialAd(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, context.getResources().getString(R.string.interstitial_test_id), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });

    }

    private static void startNextActivity(Activity activity, Intent nextActivityIntent) {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
        if (nextActivityIntent == null) return;
        activity.startActivity(nextActivityIntent);
        if (activity instanceof SplashActivity) {
            activity.finish();
        }
    }

    public static void showRewardedAd(Activity activity, Intent nextActivityIntent) {
        if (!AD_ALLOWED) {
            startNextActivity(activity, nextActivityIntent);
            return;
        }
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(Html.fromHtml("<font color='black'>Loading...</font>"));
        progressDialog.setCancelable(false);
        if (!(activity instanceof SplashActivity)) {
            progressDialog.show(); // We don't want to show progress dialog in case of SplashActivity
        }
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(activity, activity.getResources().getString(R.string.rewarded_test_id),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        rewardedAd = null;
                        startNextActivity(activity, nextActivityIntent);
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        rewardedAd.show(activity, rewardItem -> isRewardEarned = true);
                        rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                rewardedAd = null;
                                startNextActivity(activity, nextActivityIntent);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                rewardedAd = null;
                                startNextActivity(activity, nextActivityIntent);
                            }
                        });
                    }
                });
    }
}
