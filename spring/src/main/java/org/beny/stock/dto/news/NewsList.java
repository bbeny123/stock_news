package org.beny.stock.dto.news;

import org.beny.stock.model.News;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class NewsList {

    private List<NewsListItem> items;
    private long totalItems;
    private long totalPages;

    public List<NewsListItem> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<NewsListItem> items) {
        this.items = items;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public static NewsList of(Page<News> news) {
        NewsList list = new NewsList();
        list.items = news.map(NewsListItem::of).getContent();
        list.totalItems = news.getTotalElements();
        list.totalPages = news.getTotalPages();
        return list;
    }

}
