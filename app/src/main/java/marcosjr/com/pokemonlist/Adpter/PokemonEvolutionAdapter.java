package marcosjr.com.pokemonlist.Adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.List;

import marcosjr.com.pokemonlist.Common.Common;
import marcosjr.com.pokemonlist.Interface.IItemClickListener;
import marcosjr.com.pokemonlist.R;

public class PokemonEvolutionAdapter  extends RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder> {

    Context context;
    List<String> typeList;

    public PokemonEvolutionAdapter(Context context, List<String> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @Override
    public PokemonEvolutionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false);
        return new PokemonEvolutionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonEvolutionAdapter.MyViewHolder holder, int position) {

        holder.chip.setChipText(typeList.get(position));
        holder.chip.changeBackgroundColor(Common.getColorByType(typeList.get(position)));

    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Chip chip;

        public MyViewHolder(View itemView) {
            super(itemView);
            chip = (Chip) itemView.findViewById(R.id.chip);
            chip.setOnChipClickListener(new OnChipClickListener() {
                @Override
                public void onChipClick(View v) {
                    //faltouitemclick
                    // IItemClickListener.onClick(v,getAdapterPosition());

                }
            });
        }
    }

    {
    }
}
