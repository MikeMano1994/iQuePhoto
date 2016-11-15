package net.iquesoft.iquephoto.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.iquesoft.iquephoto.R;
import net.iquesoft.iquephoto.model.Adjust;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdjustAdapter extends RecyclerView.Adapter<AdjustAdapter.ViewHolder> {

    private Context mContext;

    private List<Adjust> mAdjustList;

    private OnAdjustClickListener mListener;

    public interface OnAdjustClickListener {
        void onAdjustClick(Adjust adjust);
    }

    public void setOnAdjustClickListener(OnAdjustClickListener listener) {
        mListener = listener;
    }

    public AdjustAdapter(List<Adjust> adjustList) {
        mAdjustList = adjustList;
    }

    @Override
    public AdjustAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(R.layout.item_adjust, parent, false);

        return new AdjustAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdjustAdapter.ViewHolder holder, int position) {
        final Adjust adjust = mAdjustList.get(position);

        holder.adjustButton.setText(adjust.getValue() + "\n" + mContext.getText(adjust.getTitle()));
        holder.adjustButton.setCompoundDrawablesWithIntrinsicBounds(null,
                mContext.getResources().getDrawable(adjust.getIcon()),
                null, null);

        holder.adjustButton.setOnClickListener(view -> {
            mListener.onAdjustClick(adjust);
        });
    }

    @Override
    public int getItemCount() {
        return mAdjustList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adjustButton)
        Button adjustButton;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}