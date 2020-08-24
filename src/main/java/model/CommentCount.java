package model;

public class CommentCount {
    private final String name;
    private final Long count;

    public CommentCount(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }



    public Long getCount() {
        return count;
    }


}
