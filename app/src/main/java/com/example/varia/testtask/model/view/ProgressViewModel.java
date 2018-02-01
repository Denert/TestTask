package com.example.varia.testtask.model.view;


import android.view.View;
import android.widget.ProgressBar;

import com.example.varia.testtask.R;
import com.example.varia.testtask.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressViewModel extends BaseViewModel {

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Loading;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new ProgressViewHolder(view);
    }

    public static class ProgressViewHolder extends BaseViewHolder<ProgressViewModel> {

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(ProgressViewModel progressViewModel) {

        }
    }
}
