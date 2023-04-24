package com.example.fitpetweather.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class BaseViewModel: ViewModel()  {
    companion object{
        private const val TAG = "BaseViewModel"
    }

    val networkError = MutableLiveData<Unit>()



    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    val _onDbError = MutableLiveData<Unit>()
    val onDbError: LiveData<Unit>
        get() = _onDbError


    fun addToDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

}