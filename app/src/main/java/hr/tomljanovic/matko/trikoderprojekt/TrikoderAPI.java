package hr.tomljanovic.matko.trikoderprojekt;

import hr.tomljanovic.matko.trikoderprojekt.models.Feed;
import hr.tomljanovic.matko.trikoderprojekt.models.SingleCategory;
import hr.tomljanovic.matko.trikoderprojekt.models.SingleFeed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrikoderAPI {

    @GET("api/public/spendings")   // All spending entities
    Call<Feed> getFeed();

    @GET("api/public/spendings/{id}")  // Single spending entity
    Call<SingleFeed> getSingleFeed(@Path("id") int idUser);

    @GET("api/public/spending-categories/{id}")  // Categories
    Call<SingleCategory> getCategoryFeed(@Path("id") int idCategory);
}
