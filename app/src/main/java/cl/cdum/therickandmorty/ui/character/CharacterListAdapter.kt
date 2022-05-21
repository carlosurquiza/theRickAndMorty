package cl.cdum.therickandmorty.ui.character

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cl.cdum.therickandmorty.R
import cl.cdum.therickandmorty.api.character.Result
import cl.cdum.therickandmorty.databinding.ItemCharacterBinding
import com.bumptech.glide.Glide

class CharacterListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Result, CharacterListAdapter.ViewHolder>(ListComparator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class ViewHolder(
        private val binding: ItemCharacterBinding,
        private val context: Context,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            characterDetailData: Result,
            listener: OnItemClickListener
        ) {
            binding.apply {
                root.setOnClickListener {
                    listener.onItemClick(characterDetailData)
                }

                characterDetailData.apply {
                    Glide.with(itemView)
                        .load(image)
                        .into(ivImage)

                    val status = context.getString(R.string.status_item, status)
                    val species = context.getString(R.string.species_item, species)
                    val gender = context.getString(R.string.gender_item, gender)

                    tvName.text = name
                    tvStatus.text = status
                    tvSpecies.text = species
                    tvGender.text = gender
                }
            }
        }
    }

    class ListComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Result, newItem: Result) =
            oldItem == newItem

    }

    interface OnItemClickListener {
        fun onItemClick(characterDetailData: Result)
    }
}