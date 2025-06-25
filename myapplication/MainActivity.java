package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    HomeFragment homeFragment = new HomeFragment();
    SettngsFragment settingsFragment = new SettngsFragment();
    ProfileFragment profileFragment = new ProfileFragment();


    private String My_CHANNEL_ID = "my_channel";


    ImageButton imageButton;

    ToggleButton languageToggle;
    TextView helloText, profile_tv;

    SwitchCompat themeSwitch;






    public static boolean isGeorgian = false;
    public static boolean isNightMode = false;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//1
        bottom_nav = findViewById(R.id.bottom_navigation);


        bottom_nav.setOnItemSelectedListener(item->{
            int itemId = item.getItemId();

           if(itemId == R.id.item1){
               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
               return true;
           }

           else if(itemId==R.id.item2){
               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, settingsFragment).commit();
               return true;
           }
           else if(itemId == R.id.item3){
               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, profileFragment).commit();
               return true;
           }
           return true;
        });


 //2
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
        }

        createNotificationChannel();
        findViewById(R.id.btton_notification).setOnClickListener(v->showNotification());




//3
        imageButton = findViewById(R.id.move_to_second_activity_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });




        //4
        languageToggle = findViewById(R.id.languageToggle);
        helloText = findViewById(R.id.helloText);



        languageToggle.setOnCheckedChangeListener((buttonView, isChecked)->{
            isGeorgian = isChecked;
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
            if (currentFragment instanceof ProfileFragment) {
                ((ProfileFragment) currentFragment).updateLanguage();
            }
            SecondActivity.updateLanguage();

            if(isChecked){
                helloText.setText("გამარჯობა");

            }
            else {
                helloText.setText("Hello");

            }
        });



        themeSwitch = findViewById(R.id.theme_switch);
        themeSwitch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
             if(buttonView.isPressed()) {
                 toggleTheme(isChecked);
             }
        }));

    }


    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(My_CHANNEL_ID, "arxi", NotificationManager.IMPORTANCE_HIGH);
            getSystemService(NotificationManager.class).createNotificationChannel(channel);

        }
    }


    private void showNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, My_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("satauri")
                .setContentText("teqsti")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat.from(this).notify(1, builder.build());




    }

    private void toggleTheme(boolean isNightMode){
        this.isNightMode = isNightMode;

        if(isNightMode){

        }

    }




}