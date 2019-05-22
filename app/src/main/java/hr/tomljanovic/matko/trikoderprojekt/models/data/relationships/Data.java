package hr.tomljanovic.matko.trikoderprojekt.models.data.relationships;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("id")
    @Expose
    private int id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Data{" +
                "type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
