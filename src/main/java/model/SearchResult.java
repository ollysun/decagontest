package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;
/**
 * @author
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SearchResult {

    @JsonProperty("page")
    private String page;

    @JsonProperty("per_page")
    private String per_page;

    @JsonProperty("total")
    private String total;

    @JsonProperty("total_pages")
    private String total_pages;

    @JsonProperty("data")
    private List<AuthorDetails> authorDetails;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public List<AuthorDetails> getAuthorDetails() {
        return authorDetails;
    }

    public void setAuthorDetails(List<AuthorDetails> authorDetails) {
        this.authorDetails = authorDetails;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "page='" + page + '\'' +
                ", per_page='" + per_page + '\'' +
                ", total='" + total + '\'' +
                ", total_pages='" + total_pages + '\'' +
                ", authorDetails=" + authorDetails +
                '}';
    }
}