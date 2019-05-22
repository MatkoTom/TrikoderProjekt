package hr.tomljanovic.matko.trikoderprojekt.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jsonapi {

    @SerializedName("version")
    @Expose
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Jsonapi{" +
                "version='" + version + '\'' +
                '}';
    }
}
