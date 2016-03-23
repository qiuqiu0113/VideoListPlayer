package com.waynell.videolist.target;

import android.view.View;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.File;

/**
 * @author Wayne
 */
public class VideoProgressTarget extends ProgressTarget<String, File> {
    private final CircularProgressBar progress;

    public VideoProgressTarget(VideoLoadTarget target, CircularProgressBar progress) {
        super(target);
        this.progress = progress;
        this.progress.setVisibility(View.INVISIBLE);
        this.progress.setProgress(0);
    }

    @Override
    public float getGranualityPercentage() {
        return 0.1f; // this matches the format string for #text below
    }

    @Override
    protected void onConnecting() {
        progress.setVisibility(View.VISIBLE);
        progress.setProgress(0);
    }

    @Override
    protected void onDownloading(long bytesRead, long expectedLength) {
        progress.setProgress((int) (100 * bytesRead / expectedLength));
    }

    @Override
    protected void onDownloaded() {
    }

    @Override
    protected void onDelivered() {
        progress.setVisibility(View.GONE);
    }
}
