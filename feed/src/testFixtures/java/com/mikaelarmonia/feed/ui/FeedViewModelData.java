package com.mikaelarmonia.feed.ui;

import static com.mikaelarmonia.feed.data.FeedData.articles;

public class FeedViewModelData {
    public static State initViewState = new State.Feed(articles);
}
