package me.weyye.todaynews.presenter;

import com.orhanobut.logger.Logger;

import java.util.List;

import me.weyye.todaynews.base.AppClient;
import me.weyye.todaynews.base.BasePresenter;
import me.weyye.todaynews.base.SubscriberCallBack;
import me.weyye.todaynews.model.Music;
import me.weyye.todaynews.model.News;
import me.weyye.todaynews.view.INewsListView;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class NewsListPresenter extends BasePresenter<INewsListView> {
    public NewsListPresenter(INewsListView mvpView) {
        super(mvpView);
    }

    public void getNewsList(String titleCode) {
        addSubscription(AppClient.getApiService().getNews(titleCode), new SubscriberCallBack<List<Music>>() {
            @Override
            protected void onSuccess(List<Music> response) {
                for(Music news : response) {
                    Logger.i("NewsListPresenter", news.getTrack().getTitle());
                }
                mvpView.onGetNewsListSuccess(response);

            }

            @Override
            protected void onError() {
                super.onError();
                mvpView.onError();
            }
        });
    }
}
