package com.github.randombear.allstatdota.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;
import com.github.randombear.allstatdota.dataaccessobject.entities.PlayerDetails;

/**
 * =================================
 * Created by randomBEAR on 07/11/2017.
 * =================================
 */

public class PlayerDetailsAdapter extends RecyclerView.Adapter<PlayerDetailsAdapter.ViewHolder> {

    private Context mContext;
    private MatchDetails mMatchDetails;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item0;
        private ImageView item1;
        private ImageView item2;
        private ImageView item3;
        private ImageView item4;
        private ImageView item5;
        private ImageView hero;
        private TextView kills;
        private TextView deaths;
        private TextView assists;
        private TextView side;
        private TextView lastHits;
        private TextView denies;
        private TextView level;
        private TextView netWorth;
        private TextView damage;


        public ViewHolder(View v) {
            super(v);
            hero = v.findViewById(R.id.details_hero);
            item0 = v.findViewById(R.id.details_item0);
            item1 = v.findViewById(R.id.details_item1);
            item2 = v.findViewById(R.id.details_item2);
            item3 = v.findViewById(R.id.details_item3);
            item4 = v.findViewById(R.id.details_item4);
            item5 = v.findViewById(R.id.details_item5);
            kills = v.findViewById(R.id.details_kills);
            deaths = v.findViewById(R.id.details_deaths);
            assists = v.findViewById(R.id.details_assists);
            side = v.findViewById(R.id.details_side);
            lastHits = v.findViewById(R.id.details_lasthits);
            denies = v.findViewById(R.id.details_denies);
            level = v.findViewById(R.id.details_level);
            netWorth = v.findViewById(R.id.details_networth);
            damage = v.findViewById(R.id.details_damage);

        }
    }

    public PlayerDetailsAdapter(MatchDetails matchDetails, Context context) {
        this.mMatchDetails = matchDetails;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout r = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_details_element,parent,false);
        return new ViewHolder(r);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlayerDetails playerDetails = mMatchDetails.getPlayers().get(position);
        String mDrawableName = "a" + playerDetails.getHeroId() + "_vert";
        int resID = mContext.getResources().getIdentifier(mDrawableName , "drawable", mContext.getPackageName());
        holder.hero.setImageResource(resID);
        setItemSource(playerDetails,holder);

        holder.kills.setText("Kills: " + playerDetails.getKills());
        holder.deaths.setText("Deaths: " + playerDetails.getDeaths());
        holder.assists.setText("Assists: " + playerDetails.getAssists());

        setDetails(playerDetails,holder);
    }

    private void setItemSource(PlayerDetails playerDetails, ViewHolder holder) {
        String resourceName = "";
        int resId = 0;
        if (playerDetails.getItem0() != 0) {
            resourceName = "a" + playerDetails.getItem0() + "_lg";
            resId = mContext.getResources().getIdentifier(resourceName,"drawable",mContext.getPackageName());
            holder.item0.setImageResource(resId);
        }
        if (playerDetails.getItem1() != 0) {
            resourceName = "a" + playerDetails.getItem1() + "_lg";
            resId = mContext.getResources().getIdentifier(resourceName,"drawable",mContext.getPackageName());
            holder.item1.setImageResource(resId);
        }
        if (playerDetails.getItem2() != 0) {
            resourceName = "a" + playerDetails.getItem2() + "_lg";
            resId = mContext.getResources().getIdentifier(resourceName,"drawable",mContext.getPackageName());
            holder.item2.setImageResource(resId);
        }
        if (playerDetails.getItem3() != 0) {
            resourceName = "a" + playerDetails.getItem3() + "_lg";
            resId = mContext.getResources().getIdentifier(resourceName,"drawable",mContext.getPackageName());
            holder.item3.setImageResource(resId);
        }
        if (playerDetails.getItem4() != 0) {
            resourceName = "a" + playerDetails.getItem4() + "_lg";
            resId = mContext.getResources().getIdentifier(resourceName,"drawable",mContext.getPackageName());
            holder.item4.setImageResource(resId);
        }
        if (playerDetails.getItem5() != 0) {
            resourceName = "a" + playerDetails.getItem5() + "_lg";
            resId = mContext.getResources().getIdentifier(resourceName,"drawable",mContext.getPackageName());
            holder.item5.setImageResource(resId);
        }
    }

    private void setDetails(PlayerDetails playerDetails, ViewHolder holder) {
        holder.netWorth.setText("Net Worth: " + playerDetails.getGoldSpent());
        holder.lastHits.setText("Last Hits: " + playerDetails.getLastHits());
        holder.denies.setText("Denies: " + playerDetails.getDenies());
        holder.damage.setText("Damage: " + playerDetails.getScaledHeroDamage());
        holder.level.setText("Level: " + playerDetails.getLevel());
        if ((playerDetails.getPlayerSlot() >> 7 & 1) == 0)
            holder.side.setText("Side: Radiant");
        else
            holder.side.setText("Side: Dire");
    }

    @Override
    public int getItemCount() {
        return mMatchDetails.getPlayers().size();
    }
}
