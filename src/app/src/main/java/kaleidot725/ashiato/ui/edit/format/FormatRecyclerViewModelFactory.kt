package kaleidot725.ashiato.ui.edit.format

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.ashiato.di.data.Format
import kaleidot725.ashiato.di.service.FormatEditor
import kaleidot725.ashiato.di.service.PictureEditor

class FormatRecyclerViewModelFactory(
    private val pictureEditor: PictureEditor,
    private val formatEditor: FormatEditor,
    private val format : Format
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == FormatRecyclerViewModel::class.java) {
            return FormatRecyclerViewModel(pictureEditor, formatEditor, format) as T
        }

        throw IllegalArgumentException("undefined class")
    }
}