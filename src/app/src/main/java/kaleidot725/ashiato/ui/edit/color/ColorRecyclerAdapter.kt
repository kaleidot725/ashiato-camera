package kaleidot725.ashiato.ui.edit.color

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kaleidot725.ashiato.R
import kaleidot725.ashiato.databinding.ColorRecyclerItemBinding

class ColorRecyclerAdapter(
    private val owner: LifecycleOwner,
    vms: List<ColorRecyclerViewModel>
) :
    RecyclerView.Adapter<ColorRecyclerHolder>() {

    private val vms: MutableList<ColorRecyclerViewModel> = vms.toMutableList()

    fun update(new: List<ColorRecyclerViewModel>) {
        vms.clear()
        vms.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorRecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ColorRecyclerItemBinding>(inflater, R.layout.color_recycler_item, parent, false)
        return ColorRecyclerHolder(owner, binding.root, binding)
    }

    override fun onBindViewHolder(holder: ColorRecyclerHolder, position: Int) {
        holder.bind(vms[position])
    }

    override fun getItemCount(): Int = vms.count()
}