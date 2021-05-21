package tw.edu.pu.conanjoker

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.tensorflow.lite.support.image.TensorImage
import tw.edu.pu.conanjoker.ml.Cartoon


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //將drawable的圖片轉換成Bitmap
        val bitmap: Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.conan1)

        val model = Cartoon.newInstance(this)

        // Creates inputs for reference.
        val image = TensorImage.fromBitmap(bitmap)

        // Runs model inference and gets result.
        val outputs = model.process(image)
        val probability = outputs.probabilityAsCategoryList

        // Releases model resources if no longer used.
        model.close()

        val txv: TextView = findViewById(R.id.txv)
        txv.text = probability.toString()
    }
}
