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
import com.github.randombear.allstatdota.dataaccessobject.entities.Match;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchHistory;
import com.github.randombear.allstatdota.dataaccessobject.entities.Player;

/**
 * =================================
 * Created by randomBEAR on 26/09/2017.
 * =================================
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private MatchHistory mMatchHistory;
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

    public MatchAdapter(MatchHistory matchHistory, Context context) {
        this.mContext = context;
        this.mMatchHistory = matchHistory;
    }

    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_card_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Match m = mMatchHistory.getMatches().get(position);
        Player playerSelf = getPlayerSelf(m);
        String info = "";
        holder.mTextView.setText("" + m.getMatchId() + " " + info);
        holder.mImageView.setImageResource(R.drawable.radiant3);
    }

    /**
     * Helper method used to get information about the user within the match.
     * @param match     Object match.
     * @return          returns the user corresponding player object.
     */
    private Player getPlayerSelf(Match match) {
        for (Player p : match.getPlayers()) {
            if (p.getAccountId() == Float.parseFloat(mContext.
                    getString(R.string.local_steam_user_id_32_bit))) {
                return p;
            }
        }
        return null;
    }

    /**
     * Helper method used to get information about the team the player belonged to.
     * @param p         Object player.
     * @return          true if Radiant, false otherwise.
     */
    private boolean isRadiant(Player p) {
            return ((p.getPlayerSlot() >> 7) & 1) == 0;
    }

    @Override
    public int getItemCount() {
        return mMatchHistory.getMatches().size();
    }
}
