package marcosjr.com.pokemonlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import marcosjr.com.pokemonlist.Adpter.PokemonListAdapter;
import marcosjr.com.pokemonlist.Common.Common;
import marcosjr.com.pokemonlist.Common.ItemOffsetDecoration;
import marcosjr.com.pokemonlist.Model.Pokedex;
import marcosjr.com.pokemonlist.Retrofit.IPokemonDex;
import marcosjr.com.pokemonlist.Retrofit.RetrofitClient;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonList extends Fragment {

    IPokemonDex iPokemonDex;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_List_recyclerview;

   static PokemonList instance;

    public static PokemonList getInstance() {
        if (instance == null)
            instance = new PokemonList();
       return instance;
   }


    public PokemonList() {
        // Required empty public constructor
        Retrofit retrofit = RetrofitClient.getInstace();
        iPokemonDex = retrofit.create(IPokemonDex.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

      pokemon_List_recyclerview = (RecyclerView)view.findViewById(R.id.pokemon_list_recyclerview);
      pokemon_List_recyclerview.setHasFixedSize(true);
      pokemon_List_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(),R.dimen.spacing);
        pokemon_List_recyclerview.addItemDecoration(itemOffsetDecoration);

        fetchData();

       return view;
    }

    private void fetchData() {
        compositeDisposable.add(iPokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.commonPokemonList = pokedex.getPokemon();
                        PokemonListAdapter adapter = new PokemonListAdapter(getActivity(),Common.commonPokemonList);

                        pokemon_List_recyclerview.setAdapter(adapter);
                    }
                })
        );
    }

}
