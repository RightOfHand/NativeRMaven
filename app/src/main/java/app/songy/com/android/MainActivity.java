package app.songy.com.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                startActivity(intent);
            }
        });
    }
}
