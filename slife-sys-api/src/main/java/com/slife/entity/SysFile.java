package com.slife.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.DataEntity;

import java.io.Serializable;

/**
 * Created by chen on 2017/3/31.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 区域信息表。
 */
@TableName("sys_file")
public class SysFile extends DataEntity<SysFile> {

	private String name;
	
	@TableField(value = "ext_name")
	private String extName;
	
	@TableField(value = "media_type")
	private String mediaType;
	
	private String url;
	
	private String path;
	
	private String spath;
	
	private Long size;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSpath() {
		return spath;
	}

	public void setSpath(String spath) {
		this.spath = spath;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }
}
