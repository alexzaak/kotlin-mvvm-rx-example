package codes.zaak.kotlinmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import codes.zaak.kotlinmvvm.R
import codes.zaak.kotlinmvvm.repository.model.Movie

open class CustomAdapter(ctx: Context?, resource: Int, list: List<Movie>) :
        ArrayAdapter<Movie>(ctx, resource, list) {

    var resource: Int
    var list: List<Movie>
    var layoutInflater: LayoutInflater
    var ctx: Context

    init {
        this.resource = resource
        this.list = list
        this.layoutInflater = ctx?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.ctx = ctx
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        var holder: ViewHolder

        if (convertView == null) {
            view = layoutInflater.inflate(resource, null) //error in this line
            holder = ViewHolder(view)
            view.tag = holder

        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        var movie = list[position]
        holder.title?.text = movie.title
        holder.year?.text = movie.year.toString()
        holder.rating?.text = ctx.getString(R.string.rating, movie.rating)

        return view
    }

    internal class ViewHolder(view: View?) {
        var title: TextView?
        var year: TextView?
        var rating: TextView?

        init {
            this.title = view?.findViewById(R.id.title)
            this.year = view?.findViewById(R.id.year)
            this.rating = view?.findViewById(R.id.rating)
        }
    }

}