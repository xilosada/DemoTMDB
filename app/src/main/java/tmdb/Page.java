package tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Page<T> {

    @SerializedName("page")
    @Expose
    public Long page;
    @SerializedName("results")
    @Expose
    public List<T> results = new ArrayList<>();
    @SerializedName("total_results")
    @Expose
    public Long totalResults;
    @SerializedName("total_pages")
    @Expose
    public Long totalPages;

    public Page withPage(Long page) {
        this.page = page;
        return this;
    }

    public Page withResults(List<T> results) {
        this.results = results;
        return this;
    }

    public Page withTotalResults(Long totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Page withTotalPages(Long totalPages) {
        this.totalPages = totalPages;
        return this;
    }

}