package com.example.khulood.nmlibrary.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khulood.nmlibrary.BookIssued;
import com.example.khulood.nmlibrary.R;


import java.util.ArrayList;


public class RVA extends RecyclerView.Adapter<RVA.MyViewHolder> {

    private Context mContext ;
    private ArrayList<BookIssued> mData;

    public RVA(Context mContext, ArrayList<BookIssued> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.notifyDataSetChanged();
    }


    @Override
    public RVA.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_book_history,parent,false);
        return new RVA.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RVA.MyViewHolder holder, final int position) {

        holder.bookname.setText(mData.get(position).getBookTitle());
        holder.bookauthor.setText(mData.get(position).getAuthor());
        holder.issuedate.setText(mData.get(position).getIssueDate());
        holder.returndate.setText(mData.get(position).getReturnedAt());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookname, bookauthor, issuedate, returndate; CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);

            bookname = (TextView) itemView.findViewById(R.id.Book_name);
            bookauthor = (TextView) itemView.findViewById(R.id.Author_Name_id);
            issuedate = (TextView) itemView.findViewById(R.id.IssuedAt);
            returndate = (TextView) itemView.findViewById(R.id.ReturnedAt);
            cardView = (CardView) itemView.findViewById(R.id.book_cardview);
        }
    }
}

