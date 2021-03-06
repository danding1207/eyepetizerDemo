package com.msc.modulenotification.viewmodel

import android.app.Application
import android.arch.core.util.Function
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.text.TextUtils
import com.msc.libcommon.util.Utils
import com.msc.libcoremodel.datamodel.http.entities.AllRecData
import com.msc.libcoremodel.datamodel.http.entities.MessagesData

import com.msc.libcoremodel.datamodel.http.repository.EyepetizerDataRepository
import com.msc.libcoremodel.datamodel.http.utils.NetUtils
import com.msc.modulenotification.BuildConfig
import com.orhanobut.logger.Logger

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class OfficialNotificationViewModel(application: Application) : AndroidViewModel(application) {

    private var mApplication: Application

    /**
     * LiveData支持了lifecycle生命周期检测
     * @return
     */
    var liveObservableData: MutableLiveData<MessagesData>

    //UI使用可观察的数据 ObservableField是一个包装类
    //    public ObservableField<TabsSelectedData> uiObservableData = new ObservableField<>();

    private val mDisposable = CompositeDisposable()

    init {
        ABSENT.value = null
        Logger.d("=======OfficialNotificationViewModel--init=========")
        mApplication = application
        //这里的trigger为网络检测，也可以换成缓存数据是否存在检测
        liveObservableData = Transformations
                .switchMap<Boolean, MessagesData>(NetUtils.netConnected(mApplication),
                        Function<Boolean, LiveData<MessagesData>> { isNetConnected ->
                            Logger.d("=======OfficialNotificationViewModel--apply=========")
                            if (!isNetConnected) {
                                return@Function ABSENT //网络未连接返回空
                            }
                            val applyData = MutableLiveData<MessagesData>()
                            initData(applyData)
                            applyData
                        }) as MutableLiveData<MessagesData>
    }

    /**
     * 刷新数据
     * @param
     */
    fun initData() {
        initData(liveObservableData)
    }

    /**
     * 刷新数据
     * @param
     */
    private fun initData(liveObservableData: MutableLiveData<MessagesData>) {
        Logger.d("=======OfficialNotificationViewModel--initData=========")
        EyepetizerDataRepository.getMessagesDataRepository(
                Utils.getUDID(),
                BuildConfig.VERSION_CODE.toString(),
                BuildConfig.VERSION_NAME,
                Utils.getSystemModel(),
                "eyepetizer_xiaomi_market",
                "eyepetizer_xiaomi_market",
                Utils.getSystemVersion()
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MessagesData> {
                    override fun onSubscribe(d: Disposable) {
                        mDisposable.add(d)
                    }
                    override fun onNext(value: MessagesData) {
                        Logger.d("=======OfficialNotificationViewModel--onNext=========")
                        if(TextUtils.isEmpty(value.nextPageUrl)) {
                            val endmessage = MessagesData.MessageListBean()
                            endmessage.type="end"
                            (value.messageList
                                    as MutableList<MessagesData.MessageListBean>)
                                    .add(endmessage)
                        }
                        liveObservableData.value = value
                    }
                    override fun onError(e: Throwable) {
                        Logger.d("=======OfficialNotificationViewModel--onError=========")

                        liveObservableData.value = ABSENT.value

                        e.printStackTrace()
                    }
                    override fun onComplete() {
                        Logger.d("=======OfficialNotificationViewModel--onComplete=========")
                    }
                })
    }

    /**
     * 刷新数据
     * @param
     */
    fun getMoreData() {
        getMoreData(liveObservableData)
    }

    private fun getMoreData(liveObservableData: MutableLiveData<MessagesData>) {
        Logger.d("=======OfficialNotificationViewModel--initData=========")

        if(liveObservableData.value!=null && liveObservableData.value!!.nextPageUrl !=null) {
            EyepetizerDataRepository.getMoreMessagesDataRepository(
                    liveObservableData.value!!.nextPageUrl!!
            )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<MessagesData> {
                        override fun onSubscribe(d: Disposable) {
                            mDisposable.add(d)
                        }
                        override fun onNext(value: MessagesData) {
                            Logger.d("=======OfficialNotificationViewModel--onNext=========")
                            val oldValue = liveObservableData.value
                            (oldValue!!.messageList as MutableList<MessagesData.MessageListBean>).addAll(value.messageList!!)

                            oldValue.nextPageUrl =value.nextPageUrl

                            if(TextUtils.isEmpty(oldValue.nextPageUrl)) {
                                val endmessage = MessagesData.MessageListBean()
                                endmessage.type="end"
                                (oldValue!!.messageList
                                        as MutableList<MessagesData.MessageListBean>)
                                        .add(endmessage)
                            }

                            liveObservableData.value = oldValue
                        }
                        override fun onError(e: Throwable) {
                            Logger.d("=======OfficialNotificationViewModel--onError=========")
                            e.printStackTrace()
                        }
                        override fun onComplete() {
                            Logger.d("=======OfficialNotificationViewModel--onComplete=========")
                        }
                    })
        } else {

        }

    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
        Logger.d("=======OfficialNotificationViewModel--onCleared=========")
    }

    companion object {
        private val ABSENT = MutableLiveData<MessagesData>()
    }

}
