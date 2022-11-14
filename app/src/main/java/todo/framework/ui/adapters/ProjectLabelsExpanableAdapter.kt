package todo.framework.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ListAdapter
import android.widget.TextView
import com.example.todo.R

class ProjectLabelsExpanableAdapter(
    private val context: Context,
    private val titles: List<String>,
    private val data: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return titles.size
    }

    override fun getChildrenCount(position: Int): Int {
        return data[titles[position]]?.size ?: 0
    }

    override fun getGroup(position: Int): Any {
        return titles[position]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return data[titles[groupPosition]]!![childPosition]
    }

    override fun getGroupId(titlesPosition: Int): Long {
        return titlesPosition.toLong()
    }

    override fun getChildId(titlePosition: Int, childPosition: Int): Long {
        return data[titles[titlePosition]]!![childPosition].toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        titlePosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater.inflate(R.layout.expandable_title, parent, false)
        }
        val title = view!!.findViewById<TextView>(R.id.expandable_title)
        title.text = getGroup(titlePosition) as String
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null){
           val  layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater.inflate(R.layout.expandable_content,parent,false)
        }
        val labelTitle = view!!.findViewById<TextView>(R.id.tv_tag_title)
        labelTitle.text = getChild(groupPosition,childPosition) as String
        return view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}