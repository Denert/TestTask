package com.example.varia.testtask.model.view;

import android.view.View;
import android.widget.TextView;

import com.example.varia.testtask.R;
import com.example.varia.testtask.model.Shop;
import com.example.varia.testtask.ui.holder.BaseViewHolder;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopViewModel extends BaseViewModel {

    private String mName;
    private String mAddress;
    private String mOpeningHours;
    private double mDistance;

    public ShopViewModel(Shop shop) {
        this.mName = shop.getName();
        this.mAddress = shop.getAddress();
        this.mOpeningHours = shop.getOpeningHours();
        this.mDistance = shop.getDistance();
    }

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getOpeningHours() {
        return mOpeningHours;
    }

    public double getDistance() {
        return mDistance;
    }



    @Override
    public LayoutTypes getType() {
        return LayoutTypes.MainItem;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new ShopViewHolder(view);
    }

    public static class ShopViewHolder extends BaseViewHolder<ShopViewModel> {

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_address)
        TextView tvAddress;

        @BindView(R.id.tv_opening_hours)
        TextView tvOpen;

        @BindView(R.id.tv_distance)
        TextView tvDistance;

        public ShopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(ShopViewModel shopViewModel) {
            tvName.setText(shopViewModel.getName());
            tvAddress.setText(shopViewModel.getAddress());
            tvOpen.setText(shopViewModel.getOpeningHours());
            DecimalFormat df = new DecimalFormat(".##");
            String distance = "";
            if (shopViewModel.getDistance() >= 1000){
                distance = df.format((shopViewModel.getDistance()/1000)) + "км.";
            } else {
                distance = df.format(shopViewModel.getDistance()) + "м.";
            }
            tvDistance.setText(distance);
        }
    }
}
