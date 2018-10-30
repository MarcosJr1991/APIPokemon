package marcosjr.com.pokemonlist.Retrofit;





import io.reactivex.Observable;
import marcosjr.com.pokemonlist.Model.Pokedex;
import retrofit2.http.GET;

public interface IPokemonDex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon ();


}
