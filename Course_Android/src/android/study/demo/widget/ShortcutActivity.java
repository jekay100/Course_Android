package android.study.demo.widget;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.study.demo.R;
import android.widget.Button;

public class ShortcutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		if(getIntent().getAction().equals(Intent.ACTION_CREATE_SHORTCUT))
		{
			Intent _addShortcutIntent = new Intent()  ;
			_addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "yufengDEMO") ;
			Intent _mailtoIntent = new Intent(this,android.study.demo.LauncherActivity.class)  ;
			_addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, _mailtoIntent)  ;
			ShortcutIconResource _iconParcelable = Intent.ShortcutIconResource.fromContext(this, R.drawable.icon)  ;
			_addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, _iconParcelable)  ;
			
			setResult(RESULT_OK, _addShortcutIntent) ;
		}else {
			setResult(RESULT_CANCELED)  ;
		}
		finish() ;
	}

}
