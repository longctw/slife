package com.slife.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.TreeEntity;

@TableName("t_category")
public class Category extends TreeEntity<Category> implements Comparable<Category>{

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public int compareTo(Category o) {
        return this.getSort().compareTo(o.getSort());
    }

    /**
     * varchar(100) 名称
     */
    protected String name;
    @Length(min = 0, max = 100, message = "资源名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
