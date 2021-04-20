package e.kyleg.obfuscationapp.api;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class GetMentions extends AsyncTask<Void, Void, List> {

    public interface AsyncResponse {
        void processFinish(List output);
    }

    public AsyncResponse delegate = null;
    private List values;

    @Override
    protected List doInBackground(Void... voids) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mentions");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                values = (List) dataSnapshot.getValue();
                delegate.processFinish(values);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Failed to read value." + error.toException());
            }
        });
        return values;
    }
}
