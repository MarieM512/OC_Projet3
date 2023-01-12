package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FavorisFragment extends Fragment implements RecyclerViewInterface {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private ArrayList<Neighbour> mFavList;
    private RecyclerView mRecyclerView;
    @BindView(R.id.item_list_avatar)
    ImageView itemListAvatar;
    TextView mTextView;


    public static FavorisFragment newInstance() {
        return (new FavorisFragment());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mTextView = mRecyclerView.findViewById(R.id.item_list_name);
        return view;
    }

    private void initList() {
        mFavList = mApiService.getFavNeighbours();
        mRecyclerView.setAdapter(new MyFavNeighbourRecyclerViewAdapter(mFavList, this));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void deleteFavNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteFavNeighbour(event.neighbour);
        initList();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), InfoNeighbourActivity.class);

        Neighbour mNeighbour = mNeighbours.get(position);

        intent.putExtra("ID",mNeighbour.getId());
        intent.putExtra("AVATAR", mNeighbour.getAvatarUrl());
        intent.putExtra("NAME", mNeighbour.getName());
        intent.putExtra("ADDRESS", mNeighbour.getAddress());
        intent.putExtra("PHONE", mNeighbour.getPhoneNumber());
        intent.putExtra("ABOUT", mNeighbour.getAboutMe());

        startActivity(intent);
    }
}
