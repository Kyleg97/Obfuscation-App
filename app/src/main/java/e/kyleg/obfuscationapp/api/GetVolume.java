package e.kyleg.obfuscationapp.api;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class GetVolume extends AsyncTask<Void, Void, List> {

    public interface AsyncResponse {
        void processFinish(List output);
    }

    public AsyncResponse delegate = null;
    private List values;

    @Override
    protected List doInBackground(Void... voids) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("reddit-mentions");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                values = (List) dataSnapshot.getValue();
                //System.out.println("Value is: " + values);
                delegate.processFinish(values);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value." + error.toException());
            }
        });
        return values;
    }

    /*@Override
    protected void onPostExecute(ArrayList result) {
        System.out.println("Whaddup here's the data");
        System.out.println(result);
        delegate.processFinish(result);
    }*/
}
