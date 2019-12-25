package commons;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-17-12-17 16:38
 */
public class PageBean<T> {

    private Integer page;//当前页，
    private Integer limit;//每页记录数
    private Integer count;//总记录数
    private Object data;//数据

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
