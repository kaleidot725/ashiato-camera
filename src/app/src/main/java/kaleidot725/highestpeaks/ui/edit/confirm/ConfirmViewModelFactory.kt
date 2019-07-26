package kaleidot725.highestpeaks.ui.edit.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.repository.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.highestpeaks.di.repository.LocationRepository
import kaleidot725.highestpeaks.di.service.PictureEditor
import kaleidot725.highestpeaks.ui.edit.EditNavigator
import java.lang.Exception

class ConfirmViewModelFactory(
    val navigator : EditNavigator,
    val locationRepository: LocationRepository,
    val editPicture : Holder<Picture>,
    val bitmapEditor : PictureEditor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == ConfirmViewModel::class.java) {
            return ConfirmViewModel(
                navigator,
                locationRepository,
                editPicture,
                bitmapEditor
            ) as  T
        }

        throw Exception("have created unknown class type")
    }
}