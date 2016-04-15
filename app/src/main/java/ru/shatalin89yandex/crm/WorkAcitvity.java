package ru.shatalin89yandex.crm;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ru.shatalin89yandex.crm.fragment.FClientInfo;
import ru.shatalin89yandex.crm.fragment.FClientList;


public class WorkAcitvity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private FragmentManager manager;
    private FragmentTransaction transaction;
    FClientList fclient;
    FClientInfo fckinfo;
    public DataBaseWork dbw = new DataBaseWork();
    ListView ClientVIew;
    public ResultSet localquery;
    public Long idquery;
    public String[] querydata=new String[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //connect to Database
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String user = intent.getStringExtra("user");
        String pass = intent.getStringExtra("pass");
        dbw.ConnectDB(url, user, pass);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.work_acitvity, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            FClientListview();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    void FClientListview(){

        fclient = new FClientList();
        fckinfo = new FClientInfo();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fclient, FClientList.TAG);
        transaction.commit();
        try {
            getClientList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

  public  void getClientList() throws SQLException {
      /*  int j=0;
        dbw.idlist = new Long[20];
        String table = "club.client";
        dbw.getData(table);
      final ResultSet[] loc = {dbw.resquery};
        ArrayList<String> infoclient = new ArrayList<String>();
        ArrayList<String> idcl = new ArrayList<String>();
        while (loc[0].next()){
            Long i= loc[0].getLong(1);
            dbw.idlist[j]=i;
            String s = loc[0].getString(2);
            String t = loc[0].getString(3);
            infoclient.add(s+" ("+t+")");
            idcl.add(i.toString());
            System.out.println(s+"___"+t);
            j++;
        }
        loc[0].close();
        dbw.resquery.close();
        final ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infoclient);
      dbw.dbadapter=adapter;
      ClientVIew  = (ListView)findViewById(R.id.clientview);
        ClientVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    dbw.getDataID(dbw.idlist[position], "client");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ResultSet local = dbw.resquery;
                try {
                    while (local.next()){
                        Long idc =local.getLong(1);
                        querydata[0]=""+idc;
                        querydata[1] = local.getString(2);
                        querydata[2] = local.getString(3);

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                transaction = manager.beginTransaction();
                transaction.replace(R.id.container, fckinfo, FClientInfo.TAG);
                transaction.commit();

                System.out.println(querydata[0] + querydata[1] + querydata[2]);
                TextView idcl = (TextView)findViewById(R.id.idClient);
                EditText nameView = (EditText) findViewById(R.id.nameView);
                EditText phoneView = (EditText) findViewById(R.id.PhoneView);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               // idcl.setText(querydata[0]);
               // nameView.setText(querydata[1]);
               // phoneView.setText(querydata[2]);
            }
        });*/

      int j=4;
      ArrayList<String> info=new ArrayList<>();

      while(j>0){
          j--;
          info.add("Строка="+j);
      }
      ArrayAdapter<String> adapter;

      adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);

      ClientVIew = (ListView)findViewById(R.id.clientview);
      ClientVIew.setAdapter(adapter);

  }



}
