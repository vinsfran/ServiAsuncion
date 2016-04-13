package py.gov.mca.serviasuncion;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuExpedientesActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_menu_expedientes);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        //getMenuInflater().inflate(R.menu.menu_menu_expedientes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Vuelve a la pantalla anterior
               // NavUtils.navigateUpFromSameTask(this);
                super.onBackPressed();
                return true;
           /* case R.id.btnRenovarBanca:
                renovarBanca(this);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
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
        Intent intent = new Intent(MenuExpedientesActivity.this, BuscarDocActivity.class);
        startActivity(intent);
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
        Intent intent = new Intent(MenuExpedientesActivity.this, BuscarExpActivity.class);
        startActivity(intent);
    }
}
