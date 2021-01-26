package com.example.kotlinmessagingapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val textView: TextView
        init {
            // Define click listener for the ViewHolder's View.
            //textView = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
        TODO("Not yet implemented")
        // Create a new view, which defines the UI of the list item
/*        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)*/
    }

    override fun onBindViewHolder(holder: MessageAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
        /*    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position]*/
    }

    //Return size of data set
    override fun getItemCount() = dataSet.size
}