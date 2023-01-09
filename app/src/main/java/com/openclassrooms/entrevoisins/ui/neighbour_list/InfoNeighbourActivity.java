package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoNeighbourActivity extends AppCompatActivity {

    @BindView(R.id.tv_name_neighbour)
    TextView nameNeighbour;
    @BindView(R.id.tv_address)
    TextView address;
    @BindView(R.id.tv_phone)
    TextView phone;
    @BindView(R.id.tv_net)
    TextView net;
    @BindView(R.id.tv_about_neighbour)
    TextView aboutNeighbour;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapseLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    Boolean favors = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);
        ButterKnife.bind(this); // Set up

        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String img = getIntent().getStringExtra("AVATAR");
        Glide.with(this)
                .load(img)
                .apply(RequestOptions.centerCropTransform())
                .into(avatar);

        String name = getIntent().getStringExtra("NAME");
        mCollapsingToolbarLayout.setTitle(name);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);

        nameNeighbour.setText(name);

        String addressNeighbour = getIntent().getStringExtra("ADDRESS");
        address.setText(addressNeighbour);

        String phoneNeighbour = getIntent().getStringExtra("PHONE");
        phone.setText(phoneNeighbour);

        net.setText("www.facebook.fr/" + name.toLowerCase(Locale.ROOT));

        String about = getIntent().getStringExtra("ABOUT");
        aboutNeighbour.setText(about);

        favors = getIntent().getBooleanExtra("FAVORIS", false);

        // Add favors
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            Context mContext = getApplicationContext();

            @Override
            public void onClick(View view) {
                if (!favors) {
                    mFloatingActionButton.setImageResource(R.drawable.ic_star_white_24dp);
                    Toast.makeText(mContext, "Add favoris", Toast.LENGTH_SHORT).show();
                    favors = true;
                } else {
                    mFloatingActionButton.setImageResource(R.drawable.ic_star_border_white_24dp);
                    Toast.makeText(mContext, "Remove favoris", Toast.LENGTH_SHORT).show();
                    favors = false;
                }
            }
        });


    }
}