package app.com.tezz.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.com.tezz.R;
import app.com.tezz.models.Celebrity;

import static com.android.volley.VolleyLog.TAG;

public class CelebrityAdapter extends RecyclerView.Adapter <CelebrityAdapter.CelebrityViewHolder>{

List<Celebrity> list;
Context context;


    public CelebrityAdapter(List<Celebrity> list, Context context) {
        this.list = list;

        Log.d(TAG, "CelebrityAdapter: +"+list.size());
        this.context = context;
    }




    @NonNull
    @Override
    public CelebrityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.celebrity_row_item,parent,false);

    return  new CelebrityViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CelebrityViewHolder holder, int position) {


        Celebrity celebrity=list.get(position);



        holder.tvPhone.setText(celebrity.getPhone());
        holder.tvName.setText(celebrity.getName());


        Glide
                .with(context)
                .load(celebrity.getImage())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivPhoto);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CelebrityViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public TextView tvPhone;

        ImageView ivPhoto;
        public CelebrityViewHolder(@NonNull View itemView) {
            super(itemView);


            tvName=itemView.findViewById(R.id.tvNameCelebrity);
            tvPhone=itemView.findViewById(R.id.tvPhoneCelebrity);
            ivPhoto=itemView.findViewById(R.id.ivphotoCelbrity);


        }
    }
}
