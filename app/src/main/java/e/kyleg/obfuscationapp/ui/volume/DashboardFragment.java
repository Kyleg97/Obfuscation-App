package e.kyleg.obfuscationapp.ui.volume;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.ExecutionException;

import e.kyleg.obfuscationapp.R;

public class DashboardFragment extends Fragment {

    // private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    /*@Override
    public void processFinish(List output) {
        System.out.println("Hello, here is the data");
        VolumeRecyclerView adapter = new VolumeRecyclerView(getActivity(), output);
        mentionsRecyclerView.setAdapter(adapter);
    }*/
}
