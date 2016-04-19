package py.gov.mca.serviasuncion;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageButton imageButtonBuscarNroDoc;
    private ImageButton imageButtonBuscarNroExp;
    private TextView textViewBuscarNroDoc;
    private TextView textViewBuscarNroExp;
    private View.OnClickListener listenerBuscarNroDoc;
    private View.OnClickListener listenerBuscarNroExp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageButtonBuscarNroDoc = (ImageButton) findViewById(R.id.image_button_buscar_nro_doc);
        imageButtonBuscarNroExp = (ImageButton) findViewById(R.id.image_button_buscar_nro_exp);
        textViewBuscarNroDoc = (TextView) findViewById(R.id.text_view_buscar_nro_doc);
        textViewBuscarNroExp = (TextView) findViewById(R.id.text_view_buscar_nro_exp);

        asignacionBuscarNroDocListener();
        asignacionBuscarNroExpListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void asignacionBuscarNroDocListener() {
        listenerBuscarNroDoc = new View.OnClickListener() {
            public void onClick(View v) {
                buscarNroDocClicado(v);
            }
        };
        imageButtonBuscarNroDoc.setOnClickListener(listenerBuscarNroDoc);
        textViewBuscarNroDoc.setOnClickListener(listenerBuscarNroDoc);
    }

    protected void buscarNroDocClicado(View v) {
        Toast.makeText(getApplicationContext(), R.string.txt_no_disponible, Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(MainActivity.this, BuscarDocActivity.class);
        //startActivity(intent);
    }

    private void asignacionBuscarNroExpListener() {
        listenerBuscarNroExp = new View.OnClickListener() {
            public void onClick(View v) {
                buscarNroExpClicado(v);
            }
        };
        imageButtonBuscarNroExp.setOnClickListener(listenerBuscarNroExp);
        textViewBuscarNroExp.setOnClickListener(listenerBuscarNroExp);
    }

    protected void buscarNroExpClicado(View v) {
        Intent intent = new Intent(MainActivity.this, BuscarExpActivity.class);
        startActivity(intent);
    }
}