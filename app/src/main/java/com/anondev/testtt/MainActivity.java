package com.anondev.testtt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ContentInfoCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anondev.testtt.databinding.ActivityMainBinding;
import com.anondev.testtt.databinding.LayoutDialogBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 111;
    private static final int CODE_READ_DATE = 1111;
    private ActivityMainBinding binding;
    private Uri image;
    private Adapter adapter;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvTime.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int h, int m) {
                    calendar.set(Calendar.HOUR_OF_DAY, h);
                    calendar.set(Calendar.MINUTE, m);
                    setAlarm(calendar.getTimeInMillis());
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
            timePickerDialog.show();
        });
//        adapter = new Adapter();
//        List<String> list = new ArrayList<>();
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        list.add("A");
//        adapter.list = list;
//        binding.rv.setAdapter(adapter);
//        initViews();
//        initListeners();
    }

    private void setAlarm(long time) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setAction(AlarmReceiver.ALARM_REMINDER);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    private void initViews() {
    }

    private void initListeners() {
//        binding.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                    selectImage();
//                } else {
//                    requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
//                }
//            }
//        });
//        binding.iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showImage();
//            }
//        });
    }

    private Boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(String permission) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, CODE_READ_DATE);
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null) {
            image = data.getData();
//            binding.iv.setImageURI(image);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_READ_DATE) {
            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            } else {
                Toast.makeText(this, "Không có quyền truy cập", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showImage() {
        Dialog dialog = new Dialog(MainActivity.this);
        LayoutDialogBinding dialogBinding = LayoutDialogBinding.inflate(LayoutInflater.from(MainActivity.this), null, false);
        dialog.setContentView(dialogBinding.getRoot());
        dialogBinding.iv.setImageURI(image);
        dialog.show();
        dialogBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}