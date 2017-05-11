/**
 *  You can modify and use this source freely
 *  only for the development of application related Live2D.
 *
 *  (c) Live2D Inc. All rights reserved.
 */

package jp.live2d.Niu;

import jp.live2d.MyLive2D.LAppDefine;
import jp.live2d.MyLive2D.LAppLive2DManager;
import jp.live2d.MyLive2D.LAppView;
import jp.live2d.Niu.R;
import jp.live2d.utils.android.FileManager;
import jp.live2d.utils.android.SoundManager;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * SampleApplicationはActivityを継承し、サンプル・アプリケーションのエントリポイント（メインのActivityクラス）となります。
 *
 *
 * Live2Dのサンプルをベースにしてます。
 */
public class MainActivity extends Activity
{
	//  Live2Dの管理
	private LAppLive2DManager live2DMgr ;
	static private Activity instance;

    // GUI部品系
    private TextView output_word;
    private EditText input_word;
    private Button talk_button;

    private InputMethodManager inputMethodManager;

    private Niu niu = null;

	public MainActivity( )
	{
		instance=this;
		if(LAppDefine.DEBUG_LOG)
		{
			Log.d( "", "==============================================\n" ) ;
			Log.d( "", "   Live2D Sample  \n" ) ;
			Log.d( "", "==============================================\n" ) ;
		}

		SoundManager.init(this);
		live2DMgr = new LAppLive2DManager() ;
	}


	 static public void exit()
    {
		SoundManager.release();
    	instance.finish();
    }


	/*
	 * Activityが作成されたときのイベント
	 */
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // GUIを初期化
      	setupGUI();
      	FileManager.init(this.getApplicationContext());

        niuInit();
    }

    void niuInit() {

        //TODO: SharedPreferenceをNiuに渡す
        //SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        niu = new Niu(instance);

        talk_button = (Button) findViewById(R.id.talk_button);
        talk_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                     niu.say();

                    //にうの意志をテキストベースで回収し、表示
                    output_word.setText(niu.getWill(input_word.getText().toString()));
                    input_word.getEditableText().clear();
            }
        });

        //文字列入力して返答するGUI
        output_word = (TextView) findViewById(R.id.output_word);
        output_word.setText("にう に なんでも はなして ね！");

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        input_word = (EditText) findViewById(R.id.input_word);
        input_word.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    inputMethodManager.hideSoftInputFromWindow(input_word.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    //にうとなく
                    //getNiuFragment().say();
                    niu.say();

                    //にうの意志をテキストベースで回収し、表示
                    output_word.setText(niu.getWill(input_word.getText().toString()));
                    input_word.getEditableText().clear();

                    return true;
                }
                return false;
            }
        });

        //フラグメント関連処理
        //NiuFragment niu = new NiuFragment();
        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(niu, "NIU");
        //transaction.commit();
    }


	/*
	 * GUIの初期化
	 * activity_main.xmlからViewを作成し、そこにLive2Dを配置する
	 */
	void setupGUI()
	{
    	setContentView(R.layout.activity_main);

        //  Viewの初期化
        LAppView view = live2DMgr.createView(this) ;

        // activity_main.xmlにLive2DのViewをレイアウトする
        FrameLayout layout=(FrameLayout) findViewById(R.id.live2DLayout);
		layout.addView(view, 0, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));


		// モデル切り替えボタン
		ImageButton iBtn = (ImageButton)findViewById(R.id.imageButton1);
		ClickListener listener = new ClickListener();
		iBtn.setOnClickListener(listener);
	}


	// ボタンを押した時のイベント
	class ClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "change model", Toast.LENGTH_SHORT).show();
			live2DMgr.changeModel();//Live2D Event
		}
	}


	/*
	 * Activityを再開したときのイベント。
	 */
	@Override
	protected void onResume()
	{
		//live2DMgr.onResume() ;
		super.onResume();
	}


	/*
	 * Activityを停止したときのイベント。
	 */
	@Override
	protected void onPause()
	{
		live2DMgr.onPause() ;
    	super.onPause();
	}
}
