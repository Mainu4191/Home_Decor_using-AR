package com.example.arapp3.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.arapp3.Constant;
import com.example.arapp3.R;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.LoadGltfListener;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Objects;

public class ArViewActivity extends AppCompatActivity {

    ArFragment arFragment;
    Button btnCapture;
    ProgressBar progressBar;

    String modelUrl = Constant.IMAGE_URL;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_view);

        progressBar = findViewById(R.id.progressBar);


        Bundle bundle = getIntent().getExtras();
        String folderName = bundle.getString("folderName");
        String model = bundle.getString("model");
        Log.d("TAG", "onCreate: " + folderName);
        Log.d("TAG", "onCreate: " + model);

        modelUrl = modelUrl + folderName + "/" + model;








        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
                String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            }
        }





        btnCapture = findViewById(R.id.capture);


        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);



        btnCapture.setOnClickListener(v -> {

            progressBar.setVisibility(View.VISIBLE);


            Toast.makeText(this, "cLICK", Toast.LENGTH_SHORT).show();

            final Bitmap bitmap = Bitmap.createBitmap(arFragment.getArSceneView().getWidth(), arFragment.getArSceneView().getHeight()   ,
                    Bitmap.Config.ARGB_8888); // mSceneView is my ArFragment
            final HandlerThread handlerThread = new HandlerThread("PixelCopier");
            handlerThread.start();


            PixelCopy.request(arFragment.getArSceneView(), bitmap, (copyResult) -> {

                Log.d("TAG", "capture: " + copyResult);


                if (copyResult == PixelCopy.SUCCESS) {
                    try {


                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, "New Picture");
                        values.put(MediaStore.Images.Media.DISPLAY_NAME, "ArView");
                        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                        values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/ArView");
                        values.put(MediaStore.Images.Media.IS_PENDING, true);

                        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Log.d("TAG", "onCreate: " + uri);








                        if (uri != null) {
                            try {
                                OutputStream outputStream = getContentResolver().openOutputStream(uri);
                                assert outputStream != null;
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                                outputStream.close();
                                values.put(android.provider.MediaStore.Images.Media.IS_PENDING, false);
                                getContentResolver().update(uri, values, null, null);
                                android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Saved to Photos", android.widget.Toast.LENGTH_LONG);
                                toast.show();
                                Log.d("TAG", "capture: " + "Saved to Photos");
                            } catch (Exception e) {
                                android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, e.toString(), android.widget.Toast.LENGTH_LONG);
                                toast.show();
                                Log.d("TAG", "capture: " + "Error MediaScanner : " + e.toString());
                            }
                        } else {
                            android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Failed to copyPixels: " + copyResult, android.widget.Toast.LENGTH_LONG);
                            toast.show();
                            Log.d("TAG", "capture: " + "Failed to copyPixels -----: " + copyResult);
                        }







                    } catch (Exception e) {
                        android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, e.toString(), android.widget.Toast.LENGTH_LONG);
                        toast.show();
                        Log.d("TAG", "capture: " + "Error MediaScanner : " + e.toString());

                    } finally {
                       Log.d("TAG", "capture: " + "MediaScanner : " + "Completed");
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                } else {
                    android.widget.Toast toast = android.widget.Toast.makeText(ArViewActivity.this, "Failed to copyPixels: " + copyResult, android.widget.Toast.LENGTH_LONG);
                    toast.show();
                    Log.d("TAG", "capture: " + "Failed to copyPixels: " + copyResult);
                    progressBar.setVisibility(View.INVISIBLE);
                }
                handlerThread.quitSafely();
            }, new android.os.Handler(handlerThread.getLooper()));





        });


        assert arFragment != null;
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {

                Anchor anchor = hitResult.createAnchor();

                String GLTF_ASSET = modelUrl;
                ModelRenderable.builder()
                        .setSource(ArViewActivity.this, RenderableSource.builder().setSource(ArViewActivity.this, Uri.parse(GLTF_ASSET), RenderableSource.SourceType.GLTF2)
                                .setScale(0.5f)  // Scale the original model to 50%.
                                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                                .build())
                        .build()
                        .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                        .exceptionally(
                                throwable -> {
                                   Log.d("TAG", "onTapPlane: " + throwable.toString());
                                    return null;
                                });






            }
        });
    }



    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);

        TransformableNode transformableNode = new com.google.ar.sceneform.ux.TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);

        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }




}
