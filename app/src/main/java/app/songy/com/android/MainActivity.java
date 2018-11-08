package app.songy.com.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import app.songy.com.lib_rn.ReactContainerActivity;
import app.songy.com.lib_rn.bridge.RNConstants;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, ReactContainerActivity.class);
                intent.putExtra(RNConstants.RN_PARAM_MODULE_NAME,"hybridapp");
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data!=null){
            Log.d("activityResult",data.getStringExtra("data"));
        }


    }
}
