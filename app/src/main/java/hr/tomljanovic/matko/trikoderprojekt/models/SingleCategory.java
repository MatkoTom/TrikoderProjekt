package hr.tomljanovic.matko.trikoderprojekt.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import hr.tomljanovic.matko.trikoderprojekt.models.data.CategoryData;


public class SingleCategory {

    @SerializedName("jsonapi")
    @Expose
    private Jsonapi jsonapi;

    @SerializedName("links")
    @Expose
    private Links links;

    @SerializedName("data")
    @Expose
    private CategoryData data;

    public Jsonapi getJsonapi() {
        return jsonapi;
    }

    public void setJsonapi(Jsonapi jsonapi) {
        this.jsonapi = jsonapi;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public CategoryData getData() {
        return data;
    }

    public void setData(CategoryData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "jsonapi=" + jsonapi +
                ", links=" + links +
                ", data=" + data +
                '}';
    }
}
