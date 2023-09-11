package com.example.getvoice;

import java.io.IOException;


import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.UUID;


import com.example.getvoice.R;




import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class StartConversation extends Activity {
	private static final String TAG = "getvoice";
	 ImageButton speak, text;
	  TextView txtArduino,text_display;
	  TextToSpeech t1;
	  Handler h;
	  String strIncom,sbprint;
	  int speak_flag=0,text_flag=0;
	  final int RECIEVE_MESSAGE = 1;		// Status  for Handler
	  private BluetoothAdapter btAdapter = null;
	  private BluetoothSocket btSocket = null;
	  private StringBuilder sb = new StringBuilder();
	  
	  private ConnectedThread mConnectedThread;
	   
	  // SPP UUID service
	  private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	 
	  // MAC-address of Bluetooth module (you must edit this line)
	  private static String address = "98:D3:32:10:B4:FB";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_conversation);
		
		Typeface Mytypeface=Typeface.createFromAsset(getAssets(), "Berlin-Sans-Fb.ttf");
		
		 speak = (ImageButton) findViewById(R.id.speaker_btn);					// button LED ON
		    text = (ImageButton) findViewById(R.id.text_btn);				// button LED OFF
		    txtArduino = (TextView) findViewById(R.id.txtArduino);
		    txtArduino.setTypeface(Mytypeface);
		    text_display=(TextView) findViewById(R.id.text_display);
		    text_display.setTypeface(Mytypeface);
		    
		    h = new Handler() {
		    	public void handleMessage(android.os.Message msg) {
		    		switch (msg.what) {
		            case RECIEVE_MESSAGE:													// if receive massage
		            	byte[] readBuf = (byte[]) msg.obj;
		            	 strIncom = new String(readBuf, 0, msg.arg1);					// create string from bytes array
		            	sb.append(strIncom);												// append string
		            	int endOfLineIndex = sb.indexOf("\r\n");							// determine the end-of-line
		            	if (endOfLineIndex > 0) { 											// if end-of-line,
		            	sbprint = sb.substring(0, endOfLineIndex);				// extract string
		                    sb.delete(0, sb.length());										// and clear
		                	txtArduino.setText("Bluetooth connected..."); 
		                	if (text_flag==1)
				    		  {
		                	text_display.setText(sbprint);// update TextView
				    		  }
		                	 if (speak_flag==1)
				    		  {
				    		        t1.speak(sbprint, TextToSpeech.QUEUE_FLUSH, null);
				    		  }
		                }
		            	//Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");
		            	break;
		    		}
		        };
			};
			       t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener()
					{   
			 @Override
			          public void onInit(int status) 
			          {
			                if(status != TextToSpeech.ERROR)
			                {
			                t1.setLanguage(Locale.UK);
			                }
			          }
					});
			btAdapter = BluetoothAdapter.getDefaultAdapter();		// get Bluetooth adapter
		    checkBTState();
		 
		    speak.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) {
		    	 
		    	//mConnectedThread.write("0");	// Send "1" via Bluetooth
		    	if(speak_flag==0)
		    	{
		    	  speak_flag=1;
		    	  ImageButton iw = (ImageButton) findViewById(R.id.speaker_btn);
		    	  iw.setImageResource(R.drawable.speaker_on_btn);
		    	}
		    	else if(speak_flag==1)
		    	{
		    		 speak_flag=0;
		    		 ImageButton iw1 = (ImageButton) findViewById(R.id.speaker_btn);
			    	  iw1.setImageResource(R.drawable.speaker_off_btn);
		    	}
		      }
		    });
		  
		  
		    
		    
		    
		  
		    text.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) {
		    	//text.setEnabled(false);  
		    	//mConnectedThread.write("0");	// Send "0" via Bluetooth
		    	if(text_flag==0)
		    	{
		    	  text_flag=1;
		    	  ImageButton iw = (ImageButton) findViewById(R.id.text_btn);
		    	  iw.setImageResource(R.drawable.convo_on_btn);
		    	}
		    	else if(text_flag==1)
		    	{
		    		 text_flag=0;
		    		 ImageButton iw1 = (ImageButton) findViewById(R.id.text_btn);
			    	  iw1.setImageResource(R.drawable.convo_off_btn);
		    	}
		       // Toast.makeText(getBaseContext(), "Turn off LED", Toast.LENGTH_SHORT).show();
		      }
		    });
	}
	 private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
	      if(Build.VERSION.SDK_INT >= 10){
	          try {
	              final Method  m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
	              return (BluetoothSocket) m.invoke(device, MY_UUID);
	          } catch (Exception e) {
	              Log.e(TAG, "Could not create Insecure RFComm Connection",e);
	          }
	      }
	      return  device.createRfcommSocketToServiceRecord(MY_UUID);
	  }
	   
	  @Override
	  public void onResume() {
	    super.onResume();
	 
	    Log.d(TAG, "...onResume - try connect...");
	   
	    // Set up a pointer to the remote node using it's address.
	    BluetoothDevice device = btAdapter.getRemoteDevice(address);
	   
	    
		try {
			btSocket = createBluetoothSocket(device);
		} catch (IOException e) {
			errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
		}
	    
	   
	    btAdapter.cancelDiscovery();
	   
	    // Establish the connection.  This will block until it connects.
	    Log.d(TAG, "...Connecting...");
	    try {
	      btSocket.connect();
	      Log.d(TAG, "....Connection ok...");
	    } catch (IOException e) {
	      try {
	        btSocket.close();
	      } catch (IOException e2) {
	        errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
	      }
	    }
	     
	    // Create a data stream so we can talk to server.
	    Log.d(TAG, "...Create Socket...");
	   
	    mConnectedThread = new ConnectedThread(btSocket);
	    mConnectedThread.start();
	  }
	 
	  @Override
	  public void onPause() {
	    super.onPause();
	 
	    Log.d(TAG, "...In onPause()...");
	  
	    try     {
	      btSocket.close();
	    } catch (IOException e2) {
	      errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
	    }
	  }
	   
	  private void checkBTState() {
	    // Check for Bluetooth support and then check to make sure it is turned on
	    // Emulator doesn't support Bluetooth and will return null
	    if(btAdapter==null) { 
	      errorExit("Fatal Error", "Bluetooth not support");
	    } else {
	      if (btAdapter.isEnabled()) {
	        Log.d(TAG, "...Bluetooth ON...");
	      } else {
	        //Prompt user to turn on Bluetooth
	        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	        startActivityForResult(enableBtIntent, 1);
	      }
	    }
	  }
	 
	  private void errorExit(String title, String message){
	    Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
	    finish();
	  }
	 
	  private class ConnectedThread extends Thread {
		    private final InputStream mmInStream;
		    private final OutputStream mmOutStream;
		 
		    public ConnectedThread(BluetoothSocket socket) {
		        InputStream tmpIn = null;
		        OutputStream tmpOut = null;
		 
		        // Get the input and output streams, using temp objects because
		        // member streams are final
		        try {
		            tmpIn = socket.getInputStream();
		            tmpOut = socket.getOutputStream();
		        } catch (IOException e) { }
		 
		        mmInStream = tmpIn;
		        mmOutStream = tmpOut;
		    }
		 
		    public void run() {
		        byte[] buffer = new byte[256];  // buffer store for the stream
		        int bytes; // bytes returned from read()

		        // Keep listening to the InputStream until an exception occurs
		        while (true) {
		        	try {
		                // Read from the InputStream
		                bytes = mmInStream.read(buffer);		// Get number of bytes and message in "buffer"
	                   
		              h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();		// Send to message queue Handler
		             
		        	} catch (IOException e) {
		                break;
		            }
		        }
		    }
		 
		    /* Call this from the main activity to send data to the remote device */
		    public void write(String message) {
		    	Log.d(TAG, "...Data to send: " + message + "...");
		    	byte[] msgBuffer = message.getBytes();
		    	try {
		            mmOutStream.write(msgBuffer);
		        } catch (IOException e) {
		            Log.d(TAG, "...Error data send: " + e.getMessage() + "...");     
		          }
		    }
		}
}
