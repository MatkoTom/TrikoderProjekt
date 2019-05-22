package hr.tomljanovic.matko.trikoderprojekt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.tomljanovic.matko.trikoderprojekt.adapters.Spendings;
import hr.tomljanovic.matko.trikoderprojekt.adapters.SpendingsAdapter;
import hr.tomljanovic.matko.trikoderprojekt.models.Feed;
import hr.tomljanovic.matko.trikoderprojekt.models.SingleCategory;
import hr.tomljanovic.matko.trikoderprojekt.models.SingleFeed;
import hr.tomljanovic.matko.trikoderprojekt.models.data.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpendingActivity extends AppCompatActivity {

    @BindView(R.id.lvItems)
    ListView lvItems;

    @BindView(R.id.relative)
    RelativeLayout relative;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tvLogin)
    TextView tvLogin;

    private Retrofit retrofit;
    private TrikoderAPI trikoderAPI;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String CATEGORY_NAME = "CATEGORY_NAME";
    public static final String TAG = "WhatHappened";
    public static final String URL = "http://mobile-job-test.trikoder.net/";

    private ArrayList<Spendings> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        trikoderAPI = retrofit.create(TrikoderAPI.class);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sp.edit();
        populateList();
        listViewFunction();
    }

    @OnClick(R.id.btnEntry)
    public void entryButton() {

        Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tvLogin)
    public void onTvClick() {
        Intent i = new Intent(SpendingActivity.this, LoginActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        Intent i = new Intent(SpendingActivity.this, LoginActivity.class);
        startActivity(i);
    }

    private void popUpWindow(String text) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.setAnimationStyle(R.style.Animation);
        popupWindow.showAtLocation(relative, Gravity.BOTTOM, 0, 0);

        ((TextView) popupWindow.getContentView().findViewById(R.id.tvPopup)).setText(text);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void populateList() {
        Call<Feed> call = trikoderAPI.getFeed();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                ArrayList<Data> data = response.body().getData();

                for (int i = 0; i < data.size(); i++) {

                    String name = "";
                    if (data.get(i).getAttributes().getName() == null) {
                        name = getString(R.string.noData);
                    } else {
                        name = data.get(i).getAttributes().getName();
                    }

                    int idUser = data.get(i).getId();
                    String amount = String.valueOf(data.get(i).getAttributes().getAmount());

                    arrayList.add(new Spendings(name, amount + " HRK", idUser));
                }
                SpendingsAdapter adapter = new SpendingsAdapter(getApplicationContext(), arrayList);

                lvItems.setAdapter(adapter);
                View footerView = ((LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer, null, false);

                Spendings user = new Spendings("", "", 0);
                lvItems.addFooterView(footerView, user, false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Toast.makeText(SpendingActivity.this, getString(R.string.somethingWrong) + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listViewFunction() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editor.clear();
                editor.commit();
                final int userId = arrayList.get(position).idUser;
                Call<SingleFeed> call = trikoderAPI.getSingleFeed(userId);
                call.enqueue(new Callback<SingleFeed>() {
                    @Override
                    public void onResponse(Call<SingleFeed> call, Response<SingleFeed> response) {
                        SingleFeed data = response.body();
                        final int categoryId = data.getData().getRelationships().getSpendingCategory().getData().getId();

                        Call<SingleCategory> callSingle = trikoderAPI.getCategoryFeed(categoryId);
                        callSingle.enqueue(new Callback<SingleCategory>() {
                            @Override
                            public void onResponse(Call<SingleCategory> call, Response<SingleCategory> response) {
                                SingleCategory data = response.body();

                                final String result = data.getData().getAttributes().getName();
                                sp.edit().putString(CATEGORY_NAME, result).apply();

                                listEverything(userId);
                            }

                            @Override
                            public void onFailure(Call<SingleCategory> call, Throwable t) {
                                Toast.makeText(SpendingActivity.this, getString(R.string.somethingWrong), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<SingleFeed> call, Throwable t) {
                        Toast.makeText(SpendingActivity.this, getString(R.string.somethingWrong) + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void listEverything(int userId) {
        Call<SingleFeed> call = trikoderAPI.getSingleFeed(userId);
        call.enqueue(new Callback<SingleFeed>() {
            @Override
            public void onResponse(Call<SingleFeed> call, Response<SingleFeed> response) {
                SingleFeed data = response.body();

                String info = sp.getString(CATEGORY_NAME, "");
                info = info.substring(0, 1).toUpperCase() + info.substring(1);

                String result = getString(R.string.type) + " " + data.getData().getType() + "\n"
                        + getString(R.string.id) + " " + data.getData().getId() + "\n"
                        + getString(R.string.amount) + " " + data.getData().getAttributes().getAmount() + "\n"
                        + getString(R.string.remark) + " " + data.getData().getAttributes().getRemark() + "\n"
                        + getString(R.string.name) + " " + data.getData().getAttributes().getName() + "\n"
                        + getString(R.string.date) + " " + data.getData().getAttributes().getDate() + "\n"
                        + getString(R.string.category) + " " + info;

                result = result.replace("null", "*No data*");
                popUpWindow(result);
            }

            @Override
            public void onFailure(Call<SingleFeed> call, Throwable t) {
            }
        });
    }
}
