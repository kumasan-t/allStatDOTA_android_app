package com.github.randombear.allstatdota.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;
import com.github.randombear.allstatdota.dataaccessobject.entities.PlayerDetails;
import com.github.randombear.allstatdota.event.MessageEvent;
import com.github.randombear.allstatdota.markers.CustomForFloatMarkerView;
import com.github.randombear.allstatdota.markers.CustomForIntegerMarkerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class GameStatFragment extends Fragment {

    private static final String TAG = "GameStat Fragment";

    private MatchDetails[] mDetailedMatchList;

    public GameStatFragment() {
        // Required empty public constructor
    }

    public static GameStatFragment newInstance() {
        GameStatFragment fragment = new GameStatFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_stat, container, false);
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMessageEvent(MessageEvent event) {
        mDetailedMatchList = event.message;
        LineChart performanceChart = getView().findViewById(R.id.content_game_stat_lineChart_XPMGPM);
        LineChart lasthitsChart = getView().findViewById(R.id.content_game_stat_lineChart_LH);
        LineChart kdaChart = getView().findViewById(R.id.content_game_stat_lineChart_KDA);

        List<Entry> entriesGPM = new ArrayList<>();
        List<Entry> entriesXPM = new ArrayList<>();
        List<Entry> entriesKDA = new ArrayList<>();
        List<Entry> entriesLH = new ArrayList<>();
        int matchNumber = 0;
        for (int i = mDetailedMatchList.length - 1; i >= 0; i--) {
            for (PlayerDetails p : mDetailedMatchList[i].getPlayers()) {
                if (p.getAccountId() == Float.parseFloat(getContext().
                        getString(R.string.local_steam_user_id_32_bit))) {
                    entriesGPM.add(new Entry(matchNumber, p.getGoldPerMin()));
                    entriesXPM.add(new Entry(matchNumber, p.getXpPerMin()));
                    entriesLH.add(new Entry(matchNumber, p.getLastHits()));
                    float kills = p.getKills();
                    float deaths = p.getDeaths();
                    float assists = p.getAssists();
                    if (deaths == 0)
                        deaths++;
                    float kda = (kills + assists) / deaths;
                    entriesKDA.add(new Entry(matchNumber, kda));
                }
            }
            matchNumber++;
        }
        LineDataSet dataSetGPM = new LineDataSet(entriesGPM,"GPM");
        LineDataSet dataSetXPM = new LineDataSet(entriesXPM,"XPM");
        LineDataSet dataSetLH = new LineDataSet(entriesLH,"Last Hits");
        LineDataSet dataSetKDA = new LineDataSet(entriesKDA,"KDA");

        dataSetGPM.setColor(getResources().getColor(R.color.palette_yellow));
        dataSetGPM.setCircleColor(getResources().getColor(R.color.palette_yellow));
        dataSetGPM.setCircleColorHole(getResources().getColor(R.color.palette_yellow));
        dataSetGPM.setDrawHighlightIndicators(false);
        dataSetGPM.setValueTextSize(0);

        dataSetXPM.setColor(getResources().getColor(R.color.palette_lightRed));
        dataSetXPM.setCircleColor(getResources().getColor(R.color.palette_lightRed));
        dataSetXPM.setCircleColorHole(getResources().getColor(R.color.palette_lightRed));
        dataSetXPM.setDrawHighlightIndicators(false);
        dataSetXPM.setValueTextColor(0);

        dataSetLH.setColor(getResources().getColor(R.color.palette_yellow));
        dataSetLH.setCircleColor(getResources().getColor(R.color.palette_yellow));
        dataSetLH.setCircleColorHole(getResources().getColor(R.color.palette_yellow));
        dataSetLH.setDrawHighlightIndicators(false);
        dataSetLH.setValueTextSize(0);

        dataSetKDA.setColor(getResources().getColor(R.color.palette_lightRed));
        dataSetKDA.setCircleColor(getResources().getColor(R.color.palette_lightRed));
        dataSetKDA.setCircleColorHole(getResources().getColor(R.color.palette_lightRed));
        dataSetKDA.setDrawHighlightIndicators(false);
        dataSetKDA.setValueTextColor(0);

        //GPM and XPM charts
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSetGPM);
        dataSets.add(dataSetXPM);

        LineData lineData = new LineData(dataSets);
        CustomForIntegerMarkerView customForIntegerMarkerView =
                new CustomForIntegerMarkerView(getContext(),R.layout.marker_view);
        performanceChart.setMarker(customForIntegerMarkerView);
        performanceChart.getDescription().setTextSize(0f);
        performanceChart.getLegend().setTextColor(Color.WHITE);
        performanceChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        performanceChart.getXAxis().setDrawGridLines(false);
        performanceChart.getXAxis().setTextColor(Color.LTGRAY);
        performanceChart.getAxisLeft().setDrawGridLines(false);
        performanceChart.getAxisLeft().setTextColor(Color.LTGRAY);
        performanceChart.getAxisRight().setDrawLabels(false);
        performanceChart.getAxisRight().setDrawAxisLine(false);
        performanceChart.setData(lineData);
        performanceChart.invalidate();

        //LH chart
        lineData = new LineData(dataSetLH);
        lasthitsChart.setMarker(customForIntegerMarkerView);
        lasthitsChart.getDescription().setTextSize(0f);
        lasthitsChart.getLegend().setTextColor(Color.WHITE);
        lasthitsChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lasthitsChart.getXAxis().setDrawGridLines(false);
        lasthitsChart.getXAxis().setTextColor(Color.LTGRAY);
        lasthitsChart.getAxisLeft().setDrawGridLines(false);
        lasthitsChart.getAxisLeft().setTextColor(Color.LTGRAY);
        lasthitsChart.getAxisRight().setDrawLabels(false);
        lasthitsChart.getAxisRight().setDrawAxisLine(false);
        lasthitsChart.setData(lineData);
        lasthitsChart.invalidate();

        lineData = new LineData(dataSetKDA);
        CustomForFloatMarkerView customForFloatMarkerView =
                new CustomForFloatMarkerView(getContext(),R.layout.marker_view);
        kdaChart.setMarker(customForFloatMarkerView);
        kdaChart.getDescription().setTextSize(0f);
        kdaChart.getLegend().setTextColor(Color.WHITE);
        kdaChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        kdaChart.getXAxis().setDrawGridLines(false);
        kdaChart.getXAxis().setTextColor(Color.LTGRAY);
        kdaChart.getAxisLeft().setDrawGridLines(false);
        kdaChart.getAxisLeft().setTextColor(Color.LTGRAY);
        kdaChart.getAxisRight().setDrawLabels(false);
        kdaChart.getAxisRight().setDrawAxisLine(false);
        kdaChart.setData(lineData);
        kdaChart.invalidate();
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
