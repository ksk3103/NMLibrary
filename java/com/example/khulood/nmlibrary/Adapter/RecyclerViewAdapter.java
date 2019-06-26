package com.example.khulood.nmlibrary.Adapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khulood.nmlibrary.Book_Activity;
import com.example.khulood.nmlibrary.R;
import com.example.khulood.nmlibrary.book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.khulood.nmlibrary.Navigation.toBitmap;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    private Context mContext ;
    private List<book> mData;
    private List<book> mDataFilterable;
    SwipeRefreshLayout swipeRefreshLayout;
    private BookListener listener;
    private static RecyclerViewAdapter instance;

    public RecyclerViewAdapter(Context mContext, ArrayList<book> mData, SwipeRefreshLayout swipeRefreshLayout, BookListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFilterable = mData;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.listener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_book,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final book Book = mDataFilterable.get(position);
        holder.bookname.setText(Book.getTitle());
        holder.bookauthor.setText(Book.getAuthor());
        byte[] data = Book.getImage(); Bitmap image = toBitmap(data);
        holder.bookimg.setImageBitmap(image);
        holder.issuestatus.setText(Book.getIssue());

        //set click listener

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, Book_Activity.class);
                //passing data to the book activity
                i.putExtra("title", Book.getTitle());
                i.putExtra("author", Book.getAuthor());
                i.putExtra("image", Book.getImage());
                i.putExtra("publisher", Book.getPublisher());
                i.putExtra("publish_date", Book.getPublish_date());
                i.putExtra("isbn", Book.getISBN());
                i.putExtra("description", Book.getDescription());
                i.putExtra("issue", Book.getIssue());
                i.putExtra("list", (Serializable) mDataFilterable);
                //starting activity
                mContext.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataFilterable.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mDataFilterable = mData;
                } else {
                    List<book> filteredList = new ArrayList<book>();
                    for (book row : mData) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getAuthor().toLowerCase().contains(charString.toLowerCase()) || row.getPublisher().toLowerCase().contains(charString.toLowerCase()) || row.getPublish_date().contains(charString) || row.getISBN().contains(charString)) {
                            filteredList.add(row);
                        }
                    }

                    mDataFilterable = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFilterable;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataFilterable = (ArrayList<book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface BookListener {
        void onBookSelected(book Book);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView bookimg; TextView bookname, bookauthor, issuestatus; CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);

            bookimg = (ImageView) itemView.findViewById(R.id.book_img);
            bookname = (TextView) itemView.findViewById(R.id.book_title);
            bookauthor = (TextView) itemView.findViewById(R.id.author_name);
            issuestatus = (TextView) itemView.findViewById(R.id.issue_status_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    // send selected contact in callback
                    listener.onBookSelected(mDataFilterable.get(getAdapterPosition()));
                }
            });
        }
    }
}
