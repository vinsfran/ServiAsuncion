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
    private ImageButton imageButtonConsultaExp;
    private ImageButton imageButtonReclamosOnline;
    private TextView textViewConsultaExp;
    private TextView textViewReclamosOnline;
    private View.OnClickListener listenerConsultaExp;
    private View.OnClickListener listenerReclamosOnline;


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

        imageButtonConsultaExp = (ImageButton) findViewById(R.id.image_button_consulta_exp);
        imageButtonReclamosOnline = (ImageButton) findViewById(R.id.image_button_reclamos_online);
        textViewConsultaExp = (TextView) findViewById(R.id.text_view_consulta_exp);
        textViewReclamosOnline = (TextView) findViewById(R.id.text_view_reclamos_online);

        asignacionConsultaExpListener();
        asignacionReclamosOnlineListener();

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

    private void asignacionConsultaExpListener() {
        listenerConsultaExp = new View.OnClickListener() {
            public void onClick(View v) {
                consultaExpClicado(v);
            }
        };
        imageButtonConsultaExp.setOnClickListener(listenerConsultaExp);

        textViewConsultaExp.setOnClickListener(listenerConsultaExp);
    }

    protected void consultaExpClicado(View v) {
        Intent intent = new Intent(MainActivity.this, MenuExpedientesActivity.class);
        startActivity(intent);
    }

    private void asignacionReclamosOnlineListener() {
        listenerReclamosOnline = new View.OnClickListener() {
            public void onClick(View v) {
                reclamosOnlineClicado(v);
            }
        };
        imageButtonReclamosOnline.setOnClickListener(listenerReclamosOnline);

        textViewReclamosOnline.setOnClickListener(listenerReclamosOnline);
    }

    protected void reclamosOnlineClicado(View v) {
        Toast.makeText(getApplicationContext(), R.string.txt_no_disponible, Toast.LENGTH_LONG).show();
    }
}