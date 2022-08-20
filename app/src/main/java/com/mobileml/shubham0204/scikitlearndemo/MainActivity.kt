package com.mobileml.shubham0204.scikitlearndemo

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.nio.FloatBuffer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the views
        val inputEditText = findViewById<EditText>( R.id.input_edittext )
        val outputTextView = findViewById<TextView>( R.id.output_textview )
        val button = findViewById<Button>( R.id.predict_button )

        button.setOnClickListener {
            // Parse input from inputEditText
            val inputs = inputEditText.text.toString().toFloatOrNull()
            if ( inputs != null ) {
                val ortEnvironment = OrtEnvironment.getEnvironment()
                val ortSession = createORTSession( ortEnvironment )
                val output = runPrediction( inputs , ortSession , ortEnvironment )
                outputTextView.text = "Output is ${output}"
            }
            else {
                Toast.makeText( this , "Please check the inputs" , Toast.LENGTH_LONG ).show()
            }
        }

    }

    // Create an OrtSession
    private fun createORTSession( ortEnvironment: OrtEnvironment ) : OrtSession {
        val modelBytes = resources.openRawResource( R.raw.sklearn_model ).readBytes()
        return ortEnvironment.createSession( modelBytes )
    }

    // Make predictions with given inputs
    private fun runPrediction( input : Float , ortSession: OrtSession , ortEnvironment: OrtEnvironment ) : Float {
        // Get the name of the input node
        val inputName = ortSession.inputNames?.iterator()?.next()
        // Make a FloatBuffer of the inputs
        val floatBufferInputs = FloatBuffer.wrap( floatArrayOf( input ) )
        // Create input tensor with floatBufferInputs of shape ( 1 , 1 )
        val inputTensor = OnnxTensor.createTensor( ortEnvironment , floatBufferInputs , longArrayOf( 1, 1 ) )
        // Run the model
        val results = ortSession.run( mapOf( inputName to inputTensor ) )
        // Fetch and return the results
        val output = results[0].value as Array<FloatArray>
        return output[0][0]
    }


}
