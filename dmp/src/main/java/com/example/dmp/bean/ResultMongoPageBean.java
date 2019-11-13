package com.example.dmp.bean;


import org.springframework.data.domain.Page;

/**
 * 接口返回分页结果bean
 *
 * @author houkj
 * 2019年01月13日
 */
@SuppressWarnings("rawtypes")
public class ResultMongoPageBean<T> extends ResultBean {

    /**
     * 数据
     */
    private PageMsg page = new PageMsg();

    @SuppressWarnings("unchecked")
    public ResultMongoPageBean(int status, String msg, Page p) {
        super();
        this.setStatus(status);
        this.setMsg(msg);
        this.setData(p.getContent());
        if (null != p) {
            //页码从1开始 mongo页码从0开始
            this.page.setPageNum(p.getNumber() + 1);
            this.page.setPages(p.getTotalPages());
            this.page.setPageSize(p.getNumberOfElements());
            this.page.setTotal(p.getTotalElements());
        }
    }

    public PageMsg getPage() {
        return page;
    }

    public void setPage(PageMsg page) {
        this.page = page;
    }

}
