package kaleidot725.highestpeaks.ui.main.history

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection

import kaleidot725.highestpeaks.R
import kaleidot725.highestpeaks.databinding.HistoryFragmentBinding
import kaleidot725.highestpeaks.ui.main.MainNavigator
import kaleidot725.highestpeaks.di.data.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository
import javax.inject.Inject
import javax.inject.Named

enum class HistoryFragmentMode(val value: Int) {
    Display(1),
    Action(2)
}

class HistoryFragment : Fragment(), HistoryFragmentActor, ActionMode.Callback{

    companion object {
        fun newInstance() = HistoryFragment()
    }

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var repository: PictureRepository

    @Inject @field:Named("SelectedPicture")
    lateinit var selected: Holder<Picture>

    private lateinit var viewModel: HistoryViewModel
    private var actionMode : ActionMode? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createView(HistoryFragmentMode.Display)
    }

    override fun onPause() {
        actionMode?.finish()
        actionMode = null
        super.onPause()
    }

    override fun action(): Boolean {
        actionMode = this.activity?.startActionMode(this, ActionMode.TYPE_PRIMARY)
        createView(HistoryFragmentMode.Action)
        return true
    }

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        val inflater = mode.menuInflater
        inflater.inflate(R.menu.history_action_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        actionMode = null
        createView(HistoryFragmentMode.Display)
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.delete -> viewModel.delete()
        }

        actionMode?.finish()
        return true
    }

    private fun createView(mode : HistoryFragmentMode) {
        viewModel = ViewModelProviders.of(this, HistoryViewModelFactory(navigator, this, repository, selected)).get(HistoryViewModel::class.java)
        viewModel.load(mode)

        val binding = DataBindingUtil.bind<HistoryFragmentBinding>(this.view as View)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel

        val recyclerView = this.view?.findViewById<RecyclerView>(R.id.picture_recycler_view)
        recyclerView?.adapter = PictureAdapter(this, viewModel.pictureViewModels.value ?: listOf())
        recyclerView?.layoutManager = GridLayoutManager(context, 2)
        recyclerView?.setHasFixedSize(true)
    }
}
