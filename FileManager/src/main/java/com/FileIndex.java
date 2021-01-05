package com;

import java.util.HashMap;
import java.util.Map;

public class FileIndex {
    private Map<String,FileNode> index=new HashMap();//索引表
    public void setIndex(String primaryKey,FileNode node){
        this.index.put(primaryKey,node);
    }

    public Map<String,FileNode> getIndex() {
        return index;
    }
}
