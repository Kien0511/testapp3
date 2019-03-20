package com.example.testapp;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //
//    SimpleExoPlayerView exoPlayerView;
//
//    SimpleExoPlayer exoPlayer;
//StringBuilder mFormatBuilder;
//    Formatter mFormatter;
//    private SeekBar skb;
//    private TextView txtTime;
//    Calendar calendar;
//    String h = "", m = "";
//    AlarmManager alarmManager;
//    PendingIntent pendingIntent;

    private ImageView imv,imv1;
    private FrameLayout layout;
    private LinearLayout layout1;

    private boolean cancel = false;
    private boolean cancel1 = false;

    private boolean check = false;

    private Button btnstart;

    private TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imv = findViewById(R.id.imv);
        imv1 = findViewById(R.id.imv1);
        layout = findViewById(R.id.layout);
        layout1 = findViewById(R.id.layout1);
        btnstart = findViewById(R.id.btnstart);
        txtTitle = findViewById(R.id.txtTitle);

        btnstart.setText("pause");

//        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.scale_up);
//        animatorSet.setTarget(layout);


//        AnimatorSet animatorSet2 = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.rotation);
//        animatorSet2.setTarget(layout1);


//        animatorSet.start();

//        animatorSet2.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                cancel1 = true;
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                if(!cancel)
//                {
//                    animatorSet2.start();
//                }
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                cancel1 = false;
//
//            }
//        });

        ObjectAnimator animator = ObjectAnimator.ofFloat(layout1,"rotation",0,360);
        animator.setDuration(10000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();


//        animatorSet.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                cancel = true;
//            }
//
//            @Override
//            public void onAnimationPause(Animator animation) {
//                cancel = false;
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                cancel = false;
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                if(!cancel)
//                {
//                    animatorSet.start();
//                }
//            }
//
//        });

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(layout,"scaleX",1, (float) 1.5);
        animator1.setDuration(3500);
        animator1.setInterpolator(new LinearInterpolator());

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(layout,"scaleY",1, (float) 1.5);
        animator2.setDuration(3500);
        animator2.setInterpolator(new LinearInterpolator());

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(layout,"scaleX", (float) 1.5,1);
        animator3.setDuration(3500);
        animator3.setInterpolator(new LinearInterpolator());
        animator3.setStartDelay(3000);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(layout,"scaleY", (float) 1.5,1);
        animator4.setDuration(3500);
        animator4.setInterpolator(new LinearInterpolator());
        animator4.setStartDelay(3000);

        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(animator1,animator2);

        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(animator3,animator4);

        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playSequentially(animatorSet1,animatorSet2);

        animatorSet3.start();

        animatorSet3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorSet3.start();
            }
        });
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = !check;
                if(check)
                {
                    btnstart.setText("start");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        animatorSet3.pause();
                        animator.pause();
                    }


                }
                else
                {
                    btnstart.setText("pause");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        animatorSet3.resume();
                        animator.resume();
                    }
                }
            }
        });

        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                if(layout1.getRotation() >= 0 && layout1.getRotation() < 150)
                {
                    txtTitle.setText("breathe in");
                }
                else if(layout1.getRotation() >= 150 && layout1.getRotation() < 210)
                {
                    txtTitle.setText("hold");
                }
                else
                {
                    txtTitle.setText("breathe out");
                }
                handler.post(this);
            }
        });
//        txtTime = findViewById(R.id.txtTime);
//
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
//
//        calendar = Calendar.getInstance();
//        txtTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog = new Dialog(MainActivity.this);
//                dialog.setContentView(R.layout.layout_dialog_time_picker);
//
//                Button btnCancel, btnOk;
//                TimePicker timePicker;
//
//
//                btnCancel = dialog.findViewById(R.id.btnCancel);
//                btnOk = dialog.findViewById(R.id.btnOk);
//                timePicker = dialog.findViewById(R.id.txtTimePicker);
//
//                btnOk.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
//                        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
//
//                        int hour = timePicker.getCurrentHour();
//                        int minute = timePicker.getCurrentMinute();
//
//
//                        h = hour + "";
//                        m = minute + "";
//                        if (hour > 12) {
//                            h = (hour - 12) + "";
//                        }
//                        if (minute < 10) {
//                            m = "0" + minute;
//                        }
//
//                        if (hour > 12) {
//                            txtTime.setText(h + ":" + m + " PM");
//                        } else {
//                            txtTime.setText(h + ":" + m + " AM");
//
//                        }
//
//                        pendingIntent = PendingIntent.getBroadcast(MainActivity.this
//                                , 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
//                        dialog.dismiss();
//
//                    }
//                });
//
//                btnCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        });
//        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
//        Sprite doubleBounce = new ThreeBounce();
//        progressBar.setIndeterminateDrawable(doubleBounce);
//        skb = findViewById(R.id.skb);
//
//        mFormatBuilder = new StringBuilder();
//        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

//        exoPlayerView = findViewById(R.id.exoPlayer);
//
//        try {
//            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
//            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
//
//            Uri uri = Uri.parse("https://assets-videos.calm.com/hls/d1dd4eb7f50fd268692dd433b1dfa8af/high-d1dd4eb7f50fd268692dd433b1dfa8af.mp4");
//
//            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
//            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//
//            MediaSource videoSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);
//            exoPlayerView.setPlayer(exoPlayer);
//            exoPlayer.prepare(videoSource);
//            exoPlayer.setPlayWhenReady(true);
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
//        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
//                Util.getUserAgent(this, "mediaPlayerSample"), defaultBandwidthMeter);
//
//        TrackSelector trackSelector = new DefaultTrackSelector();
//        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse("http://assets.calm.com/bd6afad2ef1d0930019c7d135315ccbc.oga"), dataSourceFactory, extractorsFactory, null, null);
//
//        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
//
//        player.prepare(mediaSource);
//
//        player.setPlayWhenReady(true);
//
//
//
//
//        Log.e("qqqqqqqqqqqqqq",player.getDuration()+"");
//        Handler handler = new Handler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                skb.setProgress((int) player.getCurrentPosition());
//                Log.e("ccccccc1",player.getCurrentPosition()+"");
//                Log.e("qqqqqqqqqqqqqqr",player.getDuration()+"");
//
//                handler.post(this);
//            }
//        });
//
//        skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                player.seekTo(skb.getProgress());
//            }
//        });
    }

//    private String stringForTime(int timeMs) {
//        int totalSeconds = timeMs / 1000;
//
//        int seconds = totalSeconds % 60;
//        int minutes = (totalSeconds / 60) % 60;
//        int hours   = totalSeconds / 3600;
//
//        mFormatBuilder.setLength(0);
//        if (hours > 0) {
//            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
//        } else {
//            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
//        }
//    }
}
