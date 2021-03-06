package com.msc.libcoremodel.datamodel.http.service;

import com.msc.libcoremodel.datamodel.http.entities.AllRecData;
import com.msc.libcoremodel.datamodel.http.entities.CommonData;
import com.msc.libcoremodel.datamodel.http.entities.ConfigsData;
import com.msc.libcoremodel.datamodel.http.entities.DiscoveryData;
import com.msc.libcoremodel.datamodel.http.entities.FeedData;
import com.msc.libcoremodel.datamodel.http.entities.FollowData;
import com.msc.libcoremodel.datamodel.http.entities.MessagesData;
import com.msc.libcoremodel.datamodel.http.entities.TabsSelectedData;
import com.msc.libcoremodel.datamodel.http.entities.VideoRelatedData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface EyepetizerDataService {


    //    http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=0&udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&vc=341&vn=3.19&deviceModel=MI%203W&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23
    @GET("v5/index/tab/allRec")
    Observable<CommonData> getAllRecData(@Query("page") String page,
                                         @Query("udid") String udid,
                                         @Query("vc") String vc,
                                         @Query("vn") String vn,
                                         @Query("deviceModel") String deviceModel,
                                         @Query("first_channel") String first_channel,
                                         @Query("last_channel") String last_channel,
                                         @Query("system_version_code") String system_version_code);

    @GET
    Observable<CommonData> getMoreRecData(@Url String url);


//    http://baobab.kaiyanapp.com/api/v5/index/tab/feed?
// udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&
// vc=341&
// vn=3.19&
// deviceModel=MI%203W&
// first_channel=eyepetizer_xiaomi_market&
// last_channel=eyepetizer_xiaomi_market&
// system_version_code=23

    @GET("v5/index/tab/feed")
    Observable<FeedData> getFeedData(@Query("udid") String udid,
                                     @Query("vc") String vc,
                                     @Query("vn") String vn,
                                     @Query("deviceModel") String deviceModel,
                                     @Query("first_channel") String first_channel,
                                     @Query("last_channel") String last_channel,
                                     @Query("system_version_code") String system_version_code);

    @GET
    Observable<FeedData> getMoreFeedData(@Url String url);


    //http://baobab.kaiyanapp.com/api/v5/index/tab/discovery?
    // udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&
    // vc=341&
    // vn=3.19&
    // deviceModel=MI%203W&
    // first_channel=eyepetizer_xiaomi_market&
    // last_channel=eyepetizer_xiaomi_market&
    // system_version_code=23
    @GET("v5/index/tab/discovery")
    Observable<DiscoveryData> getDiscoveryData(@Query("udid") String udid,
                                               @Query("vc") String vc,
                                               @Query("vn") String vn,
                                               @Query("deviceModel") String deviceModel,
                                               @Query("first_channel") String first_channel,
                                               @Query("last_channel") String last_channel,
                                               @Query("system_version_code") String system_version_code);

    @GET
    Observable<DiscoveryData> getMoreDiscoveryData(@Url String url);


    //http://baobab.kaiyanapp.com/api/v3/messages?udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&vc=341&vn=3.19&deviceModel=MI%203W&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23
    @GET("v3/messages")
    Observable<MessagesData> getMessagesData(@Query("udid") String udid,
                                             @Query("vc") String vc,
                                             @Query("vn") String vn,
                                             @Query("deviceModel") String deviceModel,
                                             @Query("first_channel") String first_channel,
                                             @Query("last_channel") String last_channel,
                                             @Query("system_version_code") String system_version_code);

    @GET
    Observable<MessagesData> getMoreMessagesData(@Url String url);

//    http://baobab.kaiyanapp.com/api/v6/community/tab/follow?udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&vc=341&vn=3.19&deviceModel=MI%203W&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23


//    http://baobab.kaiyanapp.com/api/v2/notification/time?udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&vc=341&vn=3.19&deviceModel=MI%203W&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23

    @GET("tabs/follow")
    Observable<FollowData> getFollowData();


//    http://baobab.kaiyanapp.com/api/v2/configs?model=Android&udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&vc=352&vn=4.0&deviceModel=MI%203W&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23

    @GET("v2/configs")
    Observable<ConfigsData> getConfigsData(@Query("model") String model,
                                           @Query("udid") String udid,
                                           @Query("vc") String vc,
                                           @Query("vn") String vn,
                                           @Query("deviceModel") String deviceModel,
                                           @Query("first_channel") String first_channel,
                                           @Query("last_channel") String last_channel,
                                           @Query("system_version_code") String system_version_code);


//    http://baobab.kaiyanapp.com/api/v4/video/related?id=13408&udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&vc=352&vn=4.0&deviceModel=MI%203W&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23
    @GET("v4/video/related")
    Observable<CommonData> getVideoRelatedData(@Query("id") String id,
                                               @Query("udid") String udid,
                                               @Query("vc") String vc,
                                               @Query("vn") String vn,
                                               @Query("deviceModel") String deviceModel,
                                               @Query("first_channel") String first_channel,
                                               @Query("last_channel") String last_channel,
                                               @Query("system_version_code") String system_version_code);


//    http://baobab.kaiyanapp.com/api/v3/search?query=%E8%B0%B7%E9%98%BF%E8%8E%AB&udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&vc=352&vn=4.0&deviceModel=MI%203W&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23

    @GET("v3/search")
    Observable<CommonData> getSearchData(@Query("query") String query,
                                               @Query("udid") String udid,
                                               @Query("vc") String vc,
                                               @Query("vn") String vn,
                                               @Query("deviceModel") String deviceModel,
                                               @Query("first_channel") String first_channel,
                                               @Query("last_channel") String last_channel,
                                               @Query("system_version_code") String system_version_code);
    @GET
    Observable<CommonData> getMoreSearchData(@Url String url);


//    http://baobab.kaiyanapp.com/api/v3/queries/hot?udid=5ab5bd3e87e04215bf7820e58576aa192784ca51&vc=352&vn=4.0&deviceModel=MI%203W&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23

    @GET("v3/queries/hot")
    Observable<List<String>> getSearchHotsData(
                                         @Query("udid") String udid,
                                         @Query("vc") String vc,
                                         @Query("vn") String vn,
                                         @Query("deviceModel") String deviceModel,
                                         @Query("first_channel") String first_channel,
                                         @Query("last_channel") String last_channel,
                                         @Query("system_version_code") String system_version_code);


}
