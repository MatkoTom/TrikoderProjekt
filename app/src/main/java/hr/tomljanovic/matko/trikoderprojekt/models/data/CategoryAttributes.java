package hr.tomljanovic.matko.trikoderprojekt.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryAttributes {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryAttributes{" +
                "name='" + name + '\'' +
                '}';
    }
}
