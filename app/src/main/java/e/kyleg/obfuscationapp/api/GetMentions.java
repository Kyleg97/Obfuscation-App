package e.kyleg.obfuscationapp.api;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import e.kyleg.obfuscationapp.data.Mention;

public class GetMentions extends AsyncTask<Void, Void, List> {

    public interface AsyncResponse {
        void processFinish(List output);
    }

    public AsyncResponse delegate = null;
    private List values;
    private ArrayList<Mention> mentionsData;

    @Override
    protected ArrayList<Mention> doInBackground(Void... voids) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("reddit-mentions");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                values = (List) dataSnapshot.getValue();
                // System.out.println("Values:");
                // System.out.println(values);
                mentionsData = new ArrayList<>();
                for (int i = 0; i < values.size(); i++) {
                    HashMap map = (HashMap) values.get(i);
                    Mention mention = new Mention(map.get("ticker").toString(), map.get("company").toString(), Integer.parseInt(map.get("count").toString()));
                    mentionsData.add(mention);
                }
                delegate.processFinish(mentionsData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Failed to read value." + error.toException());
            }
        });
        return mentionsData;
    }
}
