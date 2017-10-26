package com.github.randombear.allstatdota.markers;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.randombear.allstatdota.R;

/**
 * =================================
 * Created by randomBEAR on 26/10/2017.
 * =================================
 */

public class CustomForIntegerMarkerView extends MarkerView implements IMarker {
    private TextView mTextView;
    private MPPointF mOffset;

    public CustomForIntegerMarkerView(Context context, int layoutResource) {
        super(context,layoutResource);
        mTextView = (TextView) findViewById(R.id.marker_view_textview);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        mTextView.setText("" + (int)e.getY());
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        if (mOffset == null) {
            mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
        }
        return mOffset;
    }
}
