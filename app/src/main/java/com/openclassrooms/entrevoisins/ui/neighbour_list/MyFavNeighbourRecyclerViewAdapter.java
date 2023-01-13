package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavNeighbourRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Neighbour> mFavList;
    private final RecyclerViewInterface mRecyclerViewInterface; //

    public MyFavNeighbourRecyclerViewAdapter(ArrayList<Neighbour> items, RecyclerViewInterface mRecyclerViewInterface) {
        mFavList = items; //
        this.mRecyclerViewInterface = mRecyclerViewInterface; //
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favoris, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mFavList.get(position);
        holder.mFavNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mFavNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mFavNeighbourAvatar);

        holder.mFavDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteFavNeighbourEvent(neighbour));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFavList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mFavNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mFavNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mFavDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            //
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            mRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}

