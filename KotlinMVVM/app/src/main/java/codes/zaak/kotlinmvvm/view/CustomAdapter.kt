package codes.zaak.kotlinmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import codes.zaak.kotlinmvvm.R
import codes.zaak.kotlinmvvm.repository.model.Movie

open class CustomAdapter(var ctx: Context?, var resource: Int, var list: List<Movie>) :
        ArrayAdapter<Movie>(ctx, resource, list) {

    private var layoutInflater: LayoutInflater = ctx?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val holder: ViewHolder

        if (convertView == null) {
            view = layoutInflater.inflate(resource, null) //error in this line
            holder = ViewHolder(view)
            view.tag = holder

        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val movie = list[position]
        holder.title?.text = movie.title
        holder.year?.text = movie.year.toString()
        holder.rating?.text = ctx?.getString(R.string.rating, movie.rating)

        return view
    }

    internal class ViewHolder(view: View?) {
        var title: TextView? = view?.findViewById(R.id.title)
        var year: TextView? = view?.findViewById(R.id.year)
        var rating: TextView? = view?.findViewById(R.id.rating)
    }

}