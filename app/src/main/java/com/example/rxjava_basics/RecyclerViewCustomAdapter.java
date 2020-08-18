package com.example.rxjava_basics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewCustomAdapter extends RecyclerView.Adapter<RecyclerViewCustomAdapter.MyViewHolder> {

    private final List<Entry> entries = new ArrayList<>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // from parent layout i.e. main to custom layout
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_custom_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
  //      holder.txtName.setText(stringList.get(position));
        Entry entry = entries.get(position);
        holder.setTxtName(entry.getEntryName());
        holder.setTxtDate(entry.getEntryDate());
        holder.setTxtPrice(entry.getEntryPrice());
    }

    public void addEntryToList(Entry value){
        entries.add(value);
        notifyItemInserted(entries.size() - 1);//specify the last index
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName)
        TextView txtName;

        @BindView(R.id.txtDate)
        TextView txtDate;

        @BindView(R.id.txtPrice)
        TextView txtPrice;

        private final NumberFormat ENTRY_PRICE_FORMAT = new DecimalFormat("#0.00");
        public void setTxtName(String en) {
            this.txtName.setText(en);
        }

        public void setTxtDate(Date ed) {
            this.txtDate.setText(android.text.format.DateFormat.format("yyyy-MM-dd hh:mm",ed) );
        }

        public void setTxtPrice(BigDecimal ep) {
            this.txtPrice.setText(ENTRY_PRICE_FORMAT.format(ep.doubleValue()));
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }
}
