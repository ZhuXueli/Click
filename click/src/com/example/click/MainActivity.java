package com.example.click;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	static private int openfileDialogId = 0;
	private Range m_range;
	private DrawLine m_draw;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ImageView iv_1 = (ImageView)findViewById(R.id.iv_1);
		final TextView tv = (TextView)findViewById(R.id.tv_1);
		final ImageView iv_2 = (ImageView)findViewById(R.id.iv_2);		
		iv_1.setScaleType(ImageView.ScaleType.CENTER_CROP);
		
		iv_1.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				iv_2.setImageBitmap(null);
				Point p = new Point((int)event.getX(),(int)event.getY());
				List<Point> lp = null;
				String str = null;
				if(m_range != null)
				{
					str = m_range.RangeJudgeToString(p);
					lp = m_range.RangeJudgeToList(p);
				}
				
				tv.setText("X："+ p.x + " Y：" + p.y + " " + str);
				
				m_draw = new DrawLine(iv_1);
//				Point p1 = new Point(0,0);
//				Point p2 = new Point(300,300);
				if(lp != null)
				{
					Bitmap bitmap = m_draw.DrawRange(lp);
//					m_Process = new ImageProcess(bitmap);
//					bitmap = m_Process.highLight();
					iv_2.setImageBitmap(bitmap);
				}
				return false;
			}
		});
//		try {
//			InputStream in = getResources().getAssets().open("peopleinfo.json");
//			m_range = new Range(in);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			showDialog(openfileDialogId);
			return true;
		}
		else if(id == R.id.action_clear)
		{
			ImageView iv_1 = (ImageView)findViewById(R.id.iv_1);
			TextView tv = (TextView)findViewById(R.id.tv_1);
			m_range = null;
			tv.setText("");
			iv_1.setImageBitmap(null);
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return false;
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		if(id==openfileDialogId){
			Map<String, Integer> images = new HashMap<String, Integer>();

			images.put(OpenFileDialog.sRoot, R.drawable.filedialog_file);	
			images.put(OpenFileDialog.sParent, R.drawable.filedialog_folder_up);	
			images.put(OpenFileDialog.sFolder, R.drawable.filedialog_folder);	
			images.put("wav", R.drawable.filedialog_wavfile);	
			images.put(OpenFileDialog.sEmpty, R.drawable.filedialog_file);
			Dialog dialog = OpenFileDialog.createDialog(id, this, "openimage", new CallbackBundle() {
				@Override
				public void callback(Bundle bundle) {
					String filepath = bundle.getString("path");
					setImage(filepath); 
				}
			}, 
			".jpg;",
			images);
			return dialog;
		}
		return null;
	}
	
	private void setImage(String filepath)
	{
		String jsonpath = filepath.substring(0, filepath.length() - 3);
		jsonpath += "json";
		ImageView iv_1 = (ImageView)findViewById(R.id.iv_1);
		try {
			Bitmap bitmap = BitmapFactory.decodeFile(filepath);
			iv_1.setImageBitmap(bitmap);
			m_range = null;
			InputStream injson = new FileInputStream(jsonpath);
			m_range = new Range(injson);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
