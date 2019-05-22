package hr.tomljanovic.matko.trikoderprojekt.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import hr.tomljanovic.matko.trikoderprojekt.models.data.relationships.SpendingCategory;


public class Relationships {

    @SerializedName("spendingCategory")
    @Expose
    private SpendingCategory spendingCategory;

    public SpendingCategory getSpendingCategory() {
        return spendingCategory;
    }

    public void setSpendingCategory(SpendingCategory spendingCategory) {
        this.spendingCategory = spendingCategory;
    }

    @Override
    public String toString() {
        return "Relationships{" +
                "spendingCategory=" + spendingCategory +
                '}';
    }
}
