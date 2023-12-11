package huce.fit.mvvmpattern.views.fragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.api.ArtistService;
import huce.fit.mvvmpattern.api.PopularService;
import huce.fit.mvvmpattern.model.Artist;
import huce.fit.mvvmpattern.model.DataJson;
import huce.fit.mvvmpattern.model.Popular;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.fragments.home.itemCategories.Category;
import huce.fit.mvvmpattern.views.fragments.home.section.Section;
import huce.fit.mvvmpattern.views.fragments.home.section.SectionAdapter;
import huce.fit.mvvmpattern.views.appInterface.IClickSongOption;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewHome;
    private SectionAdapter section_adapter;
    private MainActivity mainActivity;
    private List<Section> sections;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();

        recyclerViewHome = view.findViewById(R.id.rcvHome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false);
        recyclerViewHome.setLayoutManager(linearLayoutManager);

        sections = new ArrayList<>();
        section_adapter = new SectionAdapter(mainActivity);
        section_adapter.setSections(getListSection(),new IClickSongOption() {
            @Override
            public void onClickSongOption(Song song) {
                mainActivity.openSongBottomSheet();
            }
            @Override
            public void onClickSong(Song song) {
                mainActivity.openMusicPlayer();
                MainActivity.song = song;
            }
        });
        recyclerViewHome.setAdapter(section_adapter);

        return view;
    }

    private List<Section> getListSection() {
        List<Song> history = new ArrayList<>();
        history.add(new Song("1","https://tongdangtu.000webhostapp.com/img/img_1.png", "Song 1", "Artist 1", ""));
        history.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_5.jpg", "Song 2", "Artist 2", ""));
        history.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "Song 3", "Artist 3", ""));
        history.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_1.jpg", "Song 4", "Artist 4", ""));
        history.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg", "Song 5", "Artist 5", ""));

        List listURL = new ArrayList<>();
        listURL.add("https://i.pinimg.com/170x/47/df/7f/47df7f619a9b9ceba2f3d948e41fb450.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg");

        List<Song> popular = new ArrayList<>();
//        popular.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg", "Song 1", "Artist 1"));
//        popular.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_1.jpg", "Song 2", "Artist 2"));
//        popular.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "Song 3", "Artist 3"));

        List<Artist> artists = new ArrayList<>();
//        artists.add(new Artist("https://tongdangtu.000webhostapp.com/img/img_1.png", "Artist 1"));
//        artists.add(new Artist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_5.jpg", "Artist 2"));
//        artists.add(new Artist("https://tongdangtu.000webhostapp.com/img/img_1.png", "Artist 3"));

        List<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.img_4, "Category 1"));
        categories.add(new Category(R.drawable.img_2, "Category 2"));
        categories.add(new Category(R.drawable.img_1, "Category 3"));

        List<Song> randomTracks = new ArrayList<>();
        randomTracks.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_1.jpg", "Song 1", "Artist 1", ""));
        randomTracks.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "Song 2", "Artist 2", ""));
        randomTracks.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg", "Song 3", "Artist 3", ""));

        sections.add(new Section("History", history));
        sections.add(new Section("Big Hits", listURL ));
        getPopularList();
        sections.add(new Section("Popular", popular));
        getArtistList();
        sections.add(new Section("Artist", artists));
        sections.add(new Section("Continue your journey", categories));
        sections.add(new Section("Have you try this?", randomTracks));


        return sections;
    }

    public void updateSectionAdapter () {
        section_adapter.setSections(sections,new IClickSongOption() {
            @Override
            public void onClickSongOption(Song song) {
                mainActivity.openSongBottomSheet();
            }
            @Override
            public void onClickSong(Song song) {
                mainActivity.openMusicPlayer();
                MainActivity.song = song;
            }
        });
    }

    private void getPopularList() {
        PopularService.popularService.getPopularList()
                .enqueue(new Callback<DataJson<Popular>>() {
                    @Override
                    public void onResponse(Call<DataJson<Popular>> call, Response<DataJson<Popular>> response) {
                        DataJson<Popular> dataJson = response.body();
                        if (dataJson != null) {
                            if (dataJson.isStatus() == true) {
                                List<Popular> popularList = dataJson.getData();
                                List<Song> songList = new ArrayList<>();
                                for (int i = 0; i < popularList.size(); i++) {
                                    Popular popular = popularList.get(i);
                                    songList.add(new Song(popular.getId_song(),popular.getLinkPicture(), popular.getName_song(), popular.getName_artist(), popular.getLinkSong()));
                                }
                                sections.get(2).setItems(songList);
                                updateSectionAdapter();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DataJson<Popular>> call, Throwable t) {

                    }
                });
    }

    public void getArtistList () {
        ArtistService.artistService.getArtistList()
                .enqueue(new Callback<DataJson<huce.fit.mvvmpattern.model.Artist>>() {
                    @Override
                    public void onResponse(Call<DataJson<huce.fit.mvvmpattern.model.Artist>> call, Response<DataJson<huce.fit.mvvmpattern.model.Artist>> response) {
                        DataJson<huce.fit.mvvmpattern.model.Artist> dataJson = response.body();
                        if (dataJson != null) {
                            if (dataJson.isStatus() == true) {
                                List<Artist> artistList = dataJson.getData();
                                sections.get(3).setItems(artistList);
                                updateSectionAdapter();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DataJson<huce.fit.mvvmpattern.model.Artist>> call, Throwable t) {
                        Log.e("ERROR", this.getClass().getName()+"onClickLogin()->onFailure: "+t.getMessage());
                    }
                });
    }
}
