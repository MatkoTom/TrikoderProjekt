package hr.tomljanovic.matko.trikoderprojekt.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("attributes")
    @Expose
    private Attributes attributes;

    @SerializedName("relationships")
    @Expose
    private Relationships relationships;

    @SerializedName("links")
    @Expose
    private Links links;

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

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Relationships getRelationships() {
        return relationships;
    }

    public void setRelationships(Relationships relationships) {
        this.relationships = relationships;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Data{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", attributes=" + attributes +
                ", relationships=" + relationships +
                ", links=" + links +
                '}';
    }
}
