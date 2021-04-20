package e.kyleg.obfuscationapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;
import java.util.concurrent.ExecutionException;

import e.kyleg.obfuscationapp.R;
import e.kyleg.obfuscationapp.api.GetMentions;

public class HomeFragment extends Fragment implements GetMentions.AsyncResponse {

    // private HomeViewModel homeViewModel;
    private GetMentions getMentionsAsync = new GetMentions();

    private RecyclerView mentionsRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        try {
            getMentionsAsync.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getMentionsAsync.delegate = this;

        mentionsRecyclerView = root.findViewById(R.id.list_view);
        mentionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    @Override
    public void processFinish(List output) {
        System.out.println("Hello, here is the data");
        MentionsRecyclerView adapter = new MentionsRecyclerView(getActivity(), output);
        mentionsRecyclerView.setAdapter(adapter);
    }
}
