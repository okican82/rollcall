package okayyildirim.com.rollcall.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import okayyildirim.com.rollcall.R;

public class addFragment extends Dialog {

    private Context context;
    OnMyDialogResult mDialogResult;

    private EditText weigth_txt;
    private Button ok_btn;
    private Button cancel_btn;

    public addFragment(Context _context) {
        super(_context, R.style.FullScreenDialogTheme);
        context = _context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_dialog);

        ok_btn = findViewById(R.id.ok_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        weigth_txt = findViewById(R.id.weigth_txt);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( mDialogResult != null ){
                    mDialogResult.finish(String.valueOf(weigth_txt.getText()));
                }
                addFragment.this.dismiss();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addFragment.this.dismiss();
            }
        });

    }

    public void setDialogResult(OnMyDialogResult dialogResult){
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult{
        void finish(String result);
    }



}