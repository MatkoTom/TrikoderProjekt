package hr.tomljanovic.matko.trikoderprojekt.models.data.relationships;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpendingCategory {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SpendingCategory{" +
                "data=" + data +
                '}';
    }
}
