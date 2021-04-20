package e.kyleg.obfuscationapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import e.kyleg.obfuscationapp.R;

public class MentionsRecyclerView extends RecyclerView.Adapter<MentionsRecyclerView.ViewHolder> {

    private List mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MentionsRecyclerView(Context context, List data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap map = (HashMap) mData.get(position);
        holder.ticker.setText(map.get("ticker").toString());
        holder.company.setText(map.get("company").toString());
        holder.count.setText(map.get("count").toString());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ticker;
        TextView company;
        TextView count;

        ViewHolder(View itemView) {
            super(itemView);
            ticker = itemView.findViewById(R.id.ticker);
            company = itemView.findViewById(R.id.company);
            count = itemView.findViewById(R.id.count);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    HashMap getItem(int id) {
        return (HashMap) mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}