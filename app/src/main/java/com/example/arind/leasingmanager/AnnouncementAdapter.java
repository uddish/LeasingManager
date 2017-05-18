package com.example.arind.leasingmanager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.arind.leasingmanager.POJO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uddishverma22 on 03/05/17.
 */

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.detailsViewHolder> {

    ArrayList<POJO> list = new ArrayList<>();
    public static final String TAG = "AnnouncementAdapter";

    public AnnouncementAdapter(ArrayList<POJO> list) {
        this.list = list;
    }

    public class detailsViewHolder extends RecyclerView.ViewHolder {

        TextView name, message, time, date, imageLetter;

        public detailsViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.teacher_name);
            message = (TextView) itemView.findViewById(R.id.announcement);
            time = (TextView) itemView.findViewById(R.id.time);
            date = (TextView) itemView.findViewById(R.id.date);
            imageLetter = (TextView) itemView.findViewById(R.id.img_letter);

        }
    }

    @Override
    public AnnouncementAdapter.detailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list, parent, false);
        return new detailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnnouncementAdapter.detailsViewHolder holder, int position) {
        POJO model = list.get(position);
        holder.name.setText(model.name);
        holder.message.setText(model.message);
        holder.imageLetter.setText(model.name.substring(0,1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
