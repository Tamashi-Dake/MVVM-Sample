
package huce.fit.mvvmpattern.views.fragments.home.itemPopular;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.views.appInterface.IClickSongOption;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private IClickSongOption iClickSongOption;
    private List<Song> items;
    private Context context;



    public void setItems(List<Song> list,IClickSongOption iClickSongOption) {
        this.iClickSongOption = iClickSongOption;
        this.items = list;
//      load và bind dữ liệu vào adapter
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);

        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        Song item = items.get(position);
        if (item == null) {
            return;
        }
        holder.imageView.setImageResource(item.getResourceId());
        holder.tvTitle.setText(item.getTrackName());
        holder.tvArtist.setText(item.getArtistName());
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (iClickSongOption != null) {
                    iClickSongOption.onClickSongOption(item);
                }
            }
        });
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iClickSongOption != null) {
                    iClickSongOption.onClickSong(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public void setClickSongOption(IClickSongOption listener) {
        this.iClickSongOption = listener;
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvTitle;
        private TextView tvArtist;

        private ImageButton btnMore;
        private ConstraintLayout layoutItem;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgSong);
            tvTitle = itemView.findViewById(R.id.tvSongTitle);
            tvArtist = itemView.findViewById(R.id.tvSongArtist);
            layoutItem = itemView.findViewById(R.id.layoutItem);
            btnMore = itemView.findViewById(R.id.btnMore);
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iClickSongOption != null) {
                        iClickSongOption.onClickSongOption(items.get(getAdapterPosition()));
                    }
                }
            });
            layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iClickSongOption != null) {
                        iClickSongOption.onClickSong(items.get(getAdapterPosition()));
                    }
                }
            });
        }
    }


}