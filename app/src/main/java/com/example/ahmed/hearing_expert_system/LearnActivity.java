package com.example.ahmed.hearing_expert_system;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class LearnActivity extends AppCompatActivity {

    private GridView gridView;
    TextView Myquestion;
    String letters[]={"mom","fish","eat the fish"};
    int lettersicon[]={R.drawable.mompic,R.drawable.pic2,R.drawable.pic3};
    static TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Myquestion = (TextView)findViewById(R.id.question);

        final String My_Question = getIntent().getExtras().getString("Question");;

        Myquestion.setText(My_Question);

        gridView = (GridView) findViewById(R.id.gridView);
        GridAdapter adapter = new GridAdapter(LearnActivity.this,lettersicon,letters);
        gridView.setAdapter(adapter);

        textToSpeech = new TextToSpeech(LearnActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != textToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                    textToSpeech.speak(My_Question, textToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
//        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//        int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
//        am.setStreamVolume(am.STREAM_MUSIC, amStreamMusicMaxVol, 0);
//        tts.speak("Hello", TextToSpeech.QUEUE_FLUSH, null);
        Myquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech = new TextToSpeech(LearnActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != textToSpeech.ERROR) {
                            textToSpeech.setLanguage(Locale.US);
                            textToSpeech.speak(My_Question, textToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
            }
        });//preference fragment

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position==0) {
                    textToSpeech = new TextToSpeech(LearnActivity.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != textToSpeech.ERROR) {

                                Locale loc = new Locale("ar");

                                /*  under API 20 */
                                textToSpeech.setLanguage(loc);

                                /* over API 21 */
                                String voiceName = null;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                    voiceName = loc.toLanguageTag();
                                    Voice voice = new Voice(voiceName, loc, Voice.QUALITY_HIGH, Voice.LATENCY_HIGH, false, null);
                                    textToSpeech.setVoice(voice);
                                }


                               // textToSpeech.setLanguage(Locale.US);
                                textToSpeech.speak("يارب تشتغل", textToSpeech.QUEUE_FLUSH, null);
                               // textToSpeech.speak("no way", textToSpeech.QUEUE_FLUSH, null);
                            }
                        }
                    });
                }else {
                    textToSpeech = new TextToSpeech(LearnActivity.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != textToSpeech.ERROR) {
                                textToSpeech.setLanguage(Locale.US);
                                textToSpeech.speak("wrong answer", textToSpeech.QUEUE_FLUSH, null);
                            }
                        }
                    });
                }
            }
        });
    }
}
