package com.github.randombear.allstatdota.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;
import com.github.randombear.allstatdota.dataaccessobject.entities.PlayerDetails;

import java.util.List;

/**
 * =================================
 * Created by randomBEAR on 20/10/2017.
 * =================================
 */

public class MatchDetailsAdapter extends RecyclerView.Adapter<MatchDetailsAdapter.ViewHolder> {
    private List<MatchDetails> mMatchList;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mCardView;
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View v) {
            super(v);
            mCardView = (LinearLayout) v;
            mTextView = v.findViewById(R.id.match_card_cardView_relativeLayout_textView_matchID);
            mImageView = v.findViewById(R.id.match_card_cardView_relativeLayout_imageView);
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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MatchDetails matchDetails = mMatchList.get(position);
        PlayerDetails playerDetails = getPlayerSelf(matchDetails);
        String mDrawableName = "a" + playerDetails.getHeroId() + "_sb";
        int resID = mContext.getResources().getIdentifier(mDrawableName , "drawable", mContext.getPackageName());
        holder.mImageView.setImageResource(resID);
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
