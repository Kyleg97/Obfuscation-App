package e.kyleg.obfuscationapp.data;

import androidx.annotation.NonNull;

public class Mention {
    private final String ticker;
    private final String company;
    private final int count;

    public Mention(String ticker, String company, int count) {
        this.ticker = ticker;
        this.company = company;
        this.count = count;
    }

    public String getTicker() {
        return ticker;
    }

    public String getCompany() {
        return company;
    }

    public int getCount() {
        return count;
    }

    public String getCountAsString() {
        return count + "";
    }

    @NonNull
    @Override
    public String toString() {
        return ticker + " " + company + " " + count;
    }
}
