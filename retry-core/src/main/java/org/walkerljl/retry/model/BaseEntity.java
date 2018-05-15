package org.walkerljl.retry.model;

import java.io.Serializable;

import org.walkerljl.retry.impl.util.JSONUtil;

/**
 * Base entity
 *
 * @author xingxun
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8613057061704029872L;

    @Override
    public String toString() {
        return JSONUtil.toJSONString(this);
    }
}