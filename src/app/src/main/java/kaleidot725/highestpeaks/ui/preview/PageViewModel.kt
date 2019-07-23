package kaleidot725.highestpeaks.ui.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import kaleidot725.highestpeaks.di.repository.Holder
import kaleidot725.highestpeaks.di.data.Picture
import kaleidot725.michetimer.model.repository.PictureRepository

class PageViewModel(repository: PictureRepository, position : Int) : ViewModel() {

    private val _name : MutableLiveData<String> = MutableLiveData()
    val name : LiveData<String> = _name

    private val _path : MutableLiveData<String> = MutableLiveData()
    val path : LiveData<String> get() = _path

    init {
        val picture = repository.all()[position]
        _path.value = picture.path
        _name.value = picture.name
    }
}
