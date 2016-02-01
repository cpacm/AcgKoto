package net.cpacm.acgkoto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.cpacm.core.bean.Test;
import net.cpacm.core.http.RetrofitManager;
import net.cpacm.core.service.Github;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Github git = RetrofitManager.getInstance().getRetrofit().create(Github.class);
                //Call<List<Test>> call = git.contributors("square", "retrofit");

                Observable<List<Test>> observable = git.contributors("square", "retrofit");

                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                    }
                });

                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Subscriber<List<Test>>() {
                            @Override
                            public void onCompleted() {
                                Toast.makeText(getApplicationContext(),
                                        "Completed",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getApplicationContext(),
                                        e.getMessage(),
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onNext(List<Test> tests) {
                                String s = "geggee";
                                for (int i = 0; i < tests.size(); i++) {
                                    s += tests.get(i).login + "\n";
                                }
                                Toast.makeText(getApplicationContext(),
                                        s,
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });


                /*call.enqueue(new Callback<List<Test>>() {
                    @Override
                    public void onResponse(Response<List<Test>> response) {
                        List<Test> tests = response.body();
                        String s ="";
                        for(int i=0;i<tests.size();i++){
                            s+=tests.get(i).login+"\n";
                        }
                        Snackbar.make(getWindow().getDecorView(),s,Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Snackbar.make(getWindow().getDecorView(),"failure",Snackbar.LENGTH_SHORT).show();
                    }
                });*/
            }
        });
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
}
