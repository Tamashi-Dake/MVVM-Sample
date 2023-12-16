package huce.fit.mvvmpattern.views.fragments.nowPlaying;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.MusicPlayerActivity;


public class TrackInfoFragment extends Fragment {
    private ImageButton btnGoBack;
    private ImageView ivSong;
    private TextView tvArtist;
    private TextView tvCategory;
    private TextView tvSongName;
    private TextView tvArtistName;

    private MusicPlayerActivity musicPlayerActivity;
    public TrackInfoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track_info, container, false);
        btnGoBack = view.findViewById(R.id.btnBack);
        ivSong = view.findViewById(R.id.iv_song_info);
        tvArtist = view.findViewById(R.id.tvArtistInfo);
        tvCategory = view.findViewById(R.id.tvCategoryInfo);
        tvSongName = view.findViewById(R.id.tvSongName);
        tvArtistName = view.findViewById(R.id.tvArtistName);

        musicPlayerActivity = (MusicPlayerActivity) getActivity();
        musicPlayerActivity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(ivSong.getContext()).load(MainActivity.song.getImage()).into(ivSong);
                        tvArtist.setText(MainActivity.song.getArtistName());
                        tvCategory.setText(MainActivity.song.getCategoryName());
                        tvSongName.setText(MainActivity.song.getTrackName());
                        tvArtistName.setText(MainActivity.song.getArtistName());
                    }
                }
        );

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });
        return view;
    }
}