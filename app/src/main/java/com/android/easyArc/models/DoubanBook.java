package com.android.easyArc.models;

import com.android.mvp.model.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alex on 17/3/6.
 */

public class DoubanBook extends BaseResponse {
    private int count;
    private int start;
    private int total;
    private List<Book> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public class Book implements Serializable {
        private String title;
        private String image ;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
