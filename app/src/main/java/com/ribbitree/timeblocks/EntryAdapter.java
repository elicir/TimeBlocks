package com.ribbitree.timeblocks;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder>{



    RecyclerView recyclerView;
    private ArrayList<BlockEntry> blockEntries;
    private final OnItemClickListener listener;

    public EntryAdapter(ArrayList<BlockEntry> blockEntries, OnItemClickListener onTimeListener) {
        this.blockEntries = blockEntries;
        this.listener = onTimeListener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new EntryViewHolder(listItem, listener, recyclerView, this.blockEntries);
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        BlockEntry block = blockEntries.get(position);
        holder.timeView.setText(block.getTime());
        holder.entry.setText("");
        holder.entry.setVisibility(View.GONE);
        if (block.getHasEntry()) {
            holder.entry.setVisibility(View.VISIBLE);
            if (block.getHasText()) {
                holder.entry.setText(block.getEntry());
                holder.entry.setFocusable(false);
            }
        }
    }


    @Override
    public int getItemCount() {
        return blockEntries.size();
    }


    public void addEntries(ArrayList<BlockEntry> entries) {
        this.blockEntries = entries;
        notifyDataSetChanged();
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView timeView;
        public TextView blockView;
        public EditText entry;
        public OnItemClickListener listener;
        public RecyclerView recyclerView;
        private final ArrayList<BlockEntry> blockEntries;
        public EntryViewHolder(View itemView, OnItemClickListener onTimeListener, RecyclerView recyclerView, ArrayList<BlockEntry> blockEntries) {
            super(itemView);
            this.listener = onTimeListener;
            this.timeView = itemView.findViewById(R.id.time);
            this.blockView = itemView.findViewById(R.id.lineBg);
            this.entry = itemView.findViewById(R.id.entry);
            this.recyclerView = recyclerView;
            this.blockEntries = blockEntries;
            blockView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                listener.onTimeClick(this.entry, this.blockEntries.get(pos));
            }
        }
    }

    public interface OnItemClickListener{
        void onTimeClick(EditText entry, BlockEntry blockEntry);
    }
}