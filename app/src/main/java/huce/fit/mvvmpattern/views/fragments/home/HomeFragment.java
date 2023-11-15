package huce.fit.mvvmpattern.views.fragments.home;

import android.os.Bundle;
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
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.Item;
import huce.fit.mvvmpattern.views.fragments.home.section.Section;
import huce.fit.mvvmpattern.views.fragments.home.section.SectionAdapter;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewHome;
    private SectionAdapter section_adapter;
    private MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();

        recyclerViewHome = view.findViewById(R.id.rcvHome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false);
        recyclerViewHome.setLayoutManager(linearLayoutManager);
        section_adapter = new SectionAdapter(mainActivity);
        section_adapter.setSections(getListSection());
        recyclerViewHome.setAdapter(section_adapter);


        return view;
    }

    private List<Section> getListSection() {
        List<Section> sections = new ArrayList<>();

        List<Item> listItem = new ArrayList<>();
        listItem.add(new Item(R.drawable.img_1, "Item 1"));
        listItem.add(new Item(R.drawable.img_2, "Item 2"));
        listItem.add(new Item(R.drawable.img_3, "Item 3"));
        listItem.add(new Item(R.drawable.img_4, "Item 4"));
        listItem.add(new Item(R.drawable.img_1, "Item 5"));
        listItem.add(new Item(R.drawable.img_2, "Item 6"));

        List listURL = new ArrayList<>();
        listURL.add("https://i.pinimg.com/170x/47/df/7f/47df7f619a9b9ceba2f3d948e41fb450.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg");

        sections.add(new Section("History", listItem));
        sections.add(new Section("Big Hits", listURL ));
//        sections.add(new Section("Popular", listItem));
//        sections.add(new Section("Artist", listItem));
//        sections.add(new Section("Continue your journey", listItem));
//        sections.add(new Section("Have you try this?", listItem));


        return sections;
    }
}
