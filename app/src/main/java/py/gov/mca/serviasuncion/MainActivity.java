package py.gov.mca.serviasuncion;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Drawer navigationDrawerLeft;

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

        final IProfile profile = new ProfileDrawerItem()
                .withName("Servicios ASU")
                .withIcon(R.drawable.ic_asu_44)
                .withIdentifier(100);

        AccountHeader headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.color.accent)
                .addProfiles(profile)
                .withHeightDp(120)
                .build();

        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch (position) {
                            case 1:
                                consultaExpedientesClicado(view);
                                break;
                            case 2:
                                reclamosEnLineaClicado(view);
                                break;
                        }

                        return false;
                    }
                })
                .build();
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName(R.string.txt_consulta_expediente).withIcon(R.drawable.ic_logo_24));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName(R.string.txt_reclamos_en_linea).withIcon(R.drawable.ic_logo_24));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    protected void consultaExpedientesClicado(View v) {
        // Intent intent = new Intent(MainActivity.this, BancasActivity.class);
        // startActivity(intent);
    }

    protected void reclamosEnLineaClicado(View v) {
        // Intent intent = new Intent(MainActivity.this, BancasActivity.class);
        // startActivity(intent);
    }
}