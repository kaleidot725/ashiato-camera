package kaleidot725.highestpeaks.ui.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository
import java.lang.Exception

class PageViewModelFactory(val repository : PictureRepository, val position : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PageViewModel::class.java) {
            return PageViewModel(repository, position) as  T
        }

        throw Exception("have created unknown class type")
    }
}