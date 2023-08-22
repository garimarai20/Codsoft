package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;




    public class MainActivity extends Activity {

        private CameraManager cameraManager;
        private String cameraId;
        private boolean isFlashOn;


        @Override
        public <T extends View> T findViewById(int id) {
            return super.findViewById(id);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Check if the device has a flash
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                Toast.makeText(this, "Flashlight is not available on this device", Toast.LENGTH_SHORT).show();
                finish();
            }

            // Initialize camera manager
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            try {
                cameraId = cameraManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

            // Set the initial state of the flashlight
            isFlashOn = true;
        }

        public void toggleFlashlight(View view) {
            try {
                if (isFlashOn) {
                    // Turn off the flashlight
                    cameraManager.setTorchMode(cameraId, false);

                    isFlashOn = false;
                } else {
                    // Turn on the flashlight

                    cameraManager.setTorchMode(cameraId, true);
                    isFlashOn = true;

                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onStop() {
            super.onStop();
            // Make sure to turn off the flashlight when the app is closed
            if (isFlashOn) {
                toggleFlashlight(null);
            }
    }}


