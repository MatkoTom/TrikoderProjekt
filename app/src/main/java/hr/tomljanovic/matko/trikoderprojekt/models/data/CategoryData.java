package hr.tomljanovic.matko.trikoderprojekt.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryData {

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("attributes")
    @Expose
    private CategoryAttributes attributes;

    @SerializedName("links")
    @Expose
    private CategoryLinks links;

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

    public CategoryAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(CategoryAttributes attributes) {
        this.attributes = attributes;
    }

    public CategoryLinks getLinks() {
        return links;
    }

    public void setLinks(CategoryLinks links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "CategoryData{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", attributes=" + attributes +
                ", links=" + links +
                '}';
    }
}
