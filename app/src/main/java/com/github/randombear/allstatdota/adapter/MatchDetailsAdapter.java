package com.github.randombear.allstatdota.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.activities.MainActivity;
import com.github.randombear.allstatdota.activities.MatchDetailsActivity;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;
import com.github.randombear.allstatdota.dataaccessobject.entities.PlayerDetails;

import java.util.List;

/**
 * =================================
 * Created by randomBEAR on 20/10/2017.
 * =================================
 */

public class MatchDetailsAdapter extends RecyclerView.Adapter<MatchDetailsAdapter.ViewHolder> {
    private static String TAG = "MATCH_DETAILS";
    private static String BUNDLE_KEY = "MATCH_DETAILS_BUNDLE";
    private static String INTENT_EXTRA = "MATCH_DETAILS_EXTRA";
    private List<MatchDetails> mMatchList;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private ImageView mImageView;
        private TextView mTextViewResult;
        private TextView mTextViewMatchID;
        private TextView mTextViewSide;
        private TextView mTextViewKDA;
        private TextView mTextViewGPM;
        private TextView mTextViewXPM;
        private TextView mTextViewDuration;
        public ViewHolder(View v) {
            super(v);
            mCardView = v.findViewById(R.id.cardView_card);
            mTextViewMatchID = v.findViewById(R.id.textView_matchID);
            mImageView = v.findViewById(R.id.imageView_hero);
            mTextViewGPM = v.findViewById(R.id.textView_GPM);
            mTextViewKDA = v.findViewById(R.id.textView_KDA);
            mTextViewResult = v.findViewById(R.id.textView_Result);
            mTextViewSide = v.findViewById(R.id.textView_Side);
            mTextViewXPM = v.findViewById(R.id.textView_XPM);
            mTextViewDuration = v.findViewById(R.id.textView_Duration);
        }
    }

    public MatchDetailsAdapter(List<MatchDetails> matchList, Context context) {
        this.mContext = context;
        this.mMatchList = matchList;
    }

    @Override
    public MatchDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_card_layout, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MatchDetails matchDetails = mMatchList.get(position);
        PlayerDetails playerDetails = getPlayerSelf(matchDetails);

        holder.mCardView.setClickable(true);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"event onClick on element " + position);
                Intent intent = new Intent(mContext, MatchDetailsActivity.class);
                intent.putExtra(INTENT_EXTRA,matchDetails);
                mContext.startActivity(intent);
            }
        });

        String mDrawableName = "a" + playerDetails.getHeroId() + "_vert";
        int resID = mContext.getResources().getIdentifier(mDrawableName , "drawable", mContext.getPackageName());
        holder.mImageView.setImageResource(resID);
        if ((playerDetails.getPlayerSlot() >> 7 & 1 ) == 0) {
            holder.mTextViewSide.setText(mContext.getResources().getString(R.string.card_Side) + " Radiant");
        } else {
            holder.mTextViewSide.setText(mContext.getResources().getString(R.string.card_Side) + " Dire");
        }
        if (matchDetails.isRadiantWin() & (playerDetails.getPlayerSlot() >> 7 & 1 ) == 0) {
            holder.mTextViewResult.setText("WIN");
            holder.mTextViewResult.setTextColor(mContext.getResources().getColor(R.color.palette_yellow));
        } else if (!matchDetails.isRadiantWin() & (playerDetails.getPlayerSlot() >> 7 & 1 ) != 0) {
            holder.mTextViewResult.setText("WIN");
            holder.mTextViewResult.setTextColor(mContext.getResources().getColor(R.color.palette_yellow));
        } else {
            holder.mTextViewResult.setText("LOSS");
            holder.mTextViewResult.setTextColor(mContext.getResources().getColor(R.color.palette_lightRed));
        }
        holder.mTextViewMatchID.setText(mContext.getResources().getString(R.string.card_matchID) + " " + matchDetails.getMatchId());
        holder.mTextViewXPM.setText(mContext.getResources().getString(R.string.card_XPM) + " " + playerDetails.getXpPerMin());
        holder.mTextViewGPM.setText(mContext.getResources().getString(R.string.card_GPM) + " " + playerDetails.getGoldPerMin());
        holder.mTextViewKDA.setText(mContext.getResources().getString(R.string.card_KDA) + " " + playerDetails.getKills() +
        "/" + playerDetails.getDeaths() + "/" + playerDetails.getAssists());
        holder.mTextViewDuration.setText(mContext.getResources().getString(R.string.card_Duration) +
               " " + matchDetails.getDuration() / 60 + ":" + matchDetails.getDuration() % 60);
    }

    /**
     * Helper method used to get information about the user within the match.
     * @param match     Object match.
     * @return          returns the user corresponding player object.
     */
    private PlayerDetails getPlayerSelf(MatchDetails match) {
        for (PlayerDetails p : match.getPlayers()) {
            if (p.getAccountId() == Float.parseFloat(mContext.
                    getString(R.string.local_steam_user_id_32_bit))) {
                return p;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mMatchList.size();
    }

}
