package id.rezayds.tedi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.rezayds.tedi.process.DrawingCanvas;

public class DrawingActivity extends AppCompatActivity {

    DrawingCanvas drawingCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        drawingCanvas = findViewById(R.id.draw);

        Button btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDrawing();
                recognizeText();
            }
        });

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawingCanvas.clear();
            }
        });
    }

    public void saveDrawing() {
        File sdCard = Environment.getExternalStorageDirectory();
        File folder = new File(sdCard.getAbsolutePath() + "/TeDi");

        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }

        File file = new File(folder, "letter.png");

        if (!file.exists()) {
            try {
                success = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream ostream = null;
        try {
            ostream = new FileOutputStream(file);

            Bitmap well = drawingCanvas.getBitmap();
            Bitmap save = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            Canvas now = new Canvas(save);
            now.drawRect(new Rect(0,0,320,480), paint);
            now.drawBitmap(well, new Rect(0, 0, well.getWidth(), well.getHeight()),
                    new Rect(0,0,320,480), null);

            save.compress(Bitmap.CompressFormat.PNG, 100, ostream);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Null error", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "File error", Toast.LENGTH_SHORT).show();
        }
    }

    private void recognizeText() {
        TextView res = findViewById(R.id.textResult);
        String imagePath = Environment.getExternalStorageDirectory() + "/TeDi/letter.png";
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            res.setText("Error");
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray items = textRecognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < items.size(); i++) {
                TextBlock item = (TextBlock)items.valueAt(i);
                stringBuilder.append(item.getValue());
                stringBuilder.append('/');
                for (Text line : item.getComponents()) {
                    Log.v("lines", line.getValue());
                    for (Text element : item.getComponents()) {
                        Log.v("element", element.getValue());
                    }
                }
            }

            String detectedLetter = stringBuilder.toString().trim();
            if (detectedLetter.equals("")) {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Hmm..")
                        .setContentText("Tulisan kamu belum bisa dibaca. Coba lagi ya!")
                        .show();
//                res.setText(detectedLetter);
            } else {
                String result = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
//                res.setText(result);
                if (result.trim().equals("Bb")) {
                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Berhasil!")
                            .setContentText("Kamu berhasil menulis dengan baik!")
                            .show();
                } else {
                    new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Yah..")
                            .setContentText("Jawaban kamu masih salah. Coba lagi ya!")
                            .show();
                }
            }
        }
    }
}