package com.xingyugu.facedetection2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    Button btnProcess;

    Bitmap sunGlass;
    //Bitmap jinGu;
    Canvas canvas;
    DrawView drawView;
    Paint rectPaint = new Paint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView5 = (ImageView)findViewById(R.id.imageView5);
        btnProcess = (Button) findViewById(R.id.btnProcess);

        final Bitmap myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.face);
        imageView.setImageBitmap(myBitmap);

        sunGlass = BitmapFactory.decodeResource(getResources(), R.drawable.sunglass);
        //jinGu = BitmapFactory.decodeResource(getResources(), R.drawable.jingu);

        Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
        canvas = new Canvas(tempBitmap);
        canvas.drawBitmap(myBitmap, 0, 0, null);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setMode(FaceDetector.FAST_MODE)
                        .build();
                if(!faceDetector.isOperational()){
                    Toast.makeText(MainActivity.this, "face detector not supported", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "face detector supported", Toast.LENGTH_SHORT).show();
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face> sparseArray = faceDetector.detect(frame);
                Toast.makeText(MainActivity.this, "face detect finished", Toast.LENGTH_SHORT).show();
                for(int i = 0; i < sparseArray.size(); i++){
                    com.google.android.gms.vision.face.Face face =sparseArray.valueAt(i);
                    detectLandmarks(face);
                }
            }
        });


    }

    private void detectLandmarks(Face face) {
        for(Landmark landmark : face.getLandmarks()) {
            int cx = (int) (landmark.getPosition().x);
            int cy = (int) (landmark.getPosition().y);

            cutFeature(landmark.getType(), cx, cy);
        }

    }

    private void cutFeature(int type, int cx, int cy) {
        Bitmap res;
        Bitmap myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.face);

        if(type == Landmark.LEFT_EYE){
            res = Bitmap.createBitmap(myBitmap, Math.max(0, cx - 60), Math.max(0, cy - 15), 120,  40);
            imageView2.setImageBitmap(res);
        }

        if(type == Landmark.RIGHT_EYE){
            res = Bitmap.createBitmap(myBitmap, Math.max(0, cx - 60), Math.max(0, cy - 15), 120,  40);
            imageView3.setImageBitmap(res);
        }

        if(type == Landmark.NOSE_BASE){
            res = Bitmap.createBitmap(myBitmap, Math.max(0, cx - 50), Math.max(0, cy - 120), 100, 120);
            imageView4.setImageBitmap(res);
        }

        if(type == Landmark.BOTTOM_MOUTH){
            res = Bitmap.createBitmap(myBitmap, Math.max(0, cx - 60), Math.max(0, cy - 50), 120, 50);
            imageView5.setImageBitmap(res);
        }
    }
}


