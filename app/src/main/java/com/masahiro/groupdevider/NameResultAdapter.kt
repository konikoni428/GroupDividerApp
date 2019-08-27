package com.masahiro.groupdevider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.name_card_layout.view.*

class NameResultAdapter(private val myDataset: List<String>):
        RecyclerView.Adapter<NameResultAdapter.NameViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class NameViewHolder(val cardView: View) : RecyclerView.ViewHolder(cardView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NameResultAdapter.NameViewHolder {
        // create a new view
        val nameCardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.name_card_result, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return NameViewHolder(nameCardView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cardView.name_text_view.text = myDataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}