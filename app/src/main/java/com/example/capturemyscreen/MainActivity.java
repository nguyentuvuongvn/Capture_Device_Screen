package com.example.capturemyscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



// Nạp android
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// Nạp androidx
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

// Nạp java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;



// Tập hợp các lệnh để chạy phần mềm
public class MainActivity

        // Nạp AppCompatActivity
        extends AppCompatActivity {



            // Nạp click
            Button click;

            // Nạp REQUEST_EXTERNAL_STORAGe
            private static final int REQUEST_EXTERNAL_STORAGe = 1;

            // Nạp permissionstorage
            private static String[] permissionstorage =
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE};



            // Thiết lập method: onCreate
            @Override
            protected void onCreate(Bundle savedInstanceState) {

                // Nạp onCreate
                super.onCreate(savedInstanceState);

                // Nạp setContentView
                setContentView(R.layout.activity_main);

                // Nạp findViewById
                click = findViewById(R.id.clickme);

                // Nạp verifystoragepermissions
                verifystoragepermissions(this);

                // Nạp âm thanh camera
                // TODO: có thể thay đổi sang file âm thanh khác trong app > res > raw
                // Lúc đó đổi tên MediaPlayer.create(this, R.raw.camera) sang tên khác
                // Ví dụ: MediaPlayer.create(this, R.raw.soundfile)
                // adding camera sound
                final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.camera);

                // Nạp và chạy setOnClickListener
                click.setOnClickListener(new View.OnClickListener() {



                    // Thiết lập method: onClick
                    @Override
                    public void onClick(View v) {

                        // TODO: đổi nội dung thông báo sau khi chụp xong màn hình
                        Toast.makeText(MainActivity.this, "Finish!" + " Check image folder.",
                                Toast.LENGTH_SHORT).show();

                        // TODO: Đổi tên của file ảnh, ví dụ result >> screen.shot. hoặc screen.capture.
                        screenshot(getWindow().getDecorView().getRootView(), "screen.capture.");

                        mediaPlayer.start();
                    } // Kết thúc method: onClick
                }); // Kết thúc Chạy setOnClickListener
            } // Kết thúc method: onCreate



            // Thiết lập method: File
            protected static File

                // Nạp screenshot
                screenshot(View view, String filename) {

                // Nạp date
                Date date = new Date();

                // TODO: Thay đổi tên của file ảnh chụp màn hình
                // Ví dụ: yyyy-MM-dd_hh:mm:ss thành yyyy.MM.dd.hh.mm.ss
                // Here we are initialising the format of our image name
                CharSequence format = android.text.format.DateFormat.format("yyyy.MM.dd.hh.mm.ss", date);

                try {

                    // TODO: có thể đổi sang vị trí lưu file khác nếu thích
                    // Tạo thư mục chỉ để đọc: String dirpath = Environment.getExternalStorageDirectory() + "";
                    // Ví dụ: Screenshot thành "" hoặc "Screencapture
                    // Initialising the directory of storage
                    String dirpath = Environment.getExternalStorageDirectory() + "";
                    File file = new File(dirpath);

                    if (!file.exists())
                        {
                        boolean mkdir = file.mkdir();
                        }

                    // File name
                    String path = dirpath + "/" + filename + "-" + format + ".jpeg";
                    view.setDrawingCacheEnabled(true);

                    Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
                    view.setDrawingCacheEnabled(false);

                    File imageurl = new File(path);
                    FileOutputStream outputStream = new FileOutputStream(imageurl);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);

                    outputStream.flush();
                    outputStream.close();

                    return imageurl;
                    }

            catch (FileNotFoundException io)
                {
                io.printStackTrace();
                }

            catch (IOException e)
                {
                e.printStackTrace();
                }

            return null;
            } // Kết thúc method: screenshot



            // Thiết lập method: verifystoragepermissions
            // verifying if storage permission is given or not
            public static void verifystoragepermissions(Activity activity) {

                // Định nghĩa checkSelfPermission
                int permissions = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                // Định nghĩa PERMISSION_GRANTED
                // If storage permission is not given then request for External Storage Permission
                if (permissions != PackageManager.PERMISSION_GRANTED)
                    {
                    ActivityCompat.requestPermissions(activity, permissionstorage, REQUEST_EXTERNAL_STORAGe);
                    }
            } // Kết thúc method: verifystoragepermissions



} // Kết thúc các lệnh để chạy phần mềm
