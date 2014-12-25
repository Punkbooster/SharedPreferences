package com.example.p0331_sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	  
	  EditText etText;
	  Button btnSave, btnLoad;
	  
	  SharedPreferences sPref;
	  
	  final String SAVED_TEXT = "saved_text";
	  
	  
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        
	        etText = (EditText) findViewById(R.id.etText);
	        
	        btnSave = (Button) findViewById(R.id.btnSave);
	        btnSave.setOnClickListener(this);
	        
	        btnLoad = (Button) findViewById(R.id.btnLoad);
	        btnLoad.setOnClickListener(this);
	    
	        loadText();
	    }

	  @Override
	  public void onClick(View v) {
	    switch (v.getId()) {
	    case R.id.btnSave:
	      saveText();
	      break;
	    case R.id.btnLoad:
	      loadText();
	      break;
	    default:
	      break;
	    }
	  }
	  
	  void saveText() {
		  // spochatky z dopomogojy metoda getPreferences polychajemo objekt sPref
		  // klasy SharedPreferences, kotruj dozwolyaje pracywatu z danumu(chutatu j pusatu)
		  // konstanta MODE_PRIVATE oznachaje scho dani bydyt dostypni tilku ciomy dodatky
	    sPref = getPreferences(MODE_PRIVATE);
	    
	    // schob redaktywatu dani neobhidnuj objekt Editor, otrumyjemo jogo z sPref
	    Editor ed = sPref.edit();
	    
	    // w metod putString wkazyjemo nazwy zminnoji (SAVED_TEXT) 
	    // i nadajemo znachennya polya etText 
	    ed.putString(SAVED_TEXT, etText.getText().toString());
	    
	    //schob dani zbereglusya wukonyjemo commit
	    ed.commit();
	    
	    // wuwodumo powidomlennya scho dani zbereglusya
	    Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
	  }
	  
	  void loadText() {
	    sPref = getPreferences(MODE_PRIVATE);
	    String savedText = sPref.getString(SAVED_TEXT, "");
	    
	    // wpusyjemo znachennya w pole etText
	    etText.setText(savedText);
	    Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
	  }
	  
	  @Override
	  protected void onDestroy(){
		  saveText();
		  super.onDestroy();
	  }
	}
