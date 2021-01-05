package com;

public class FileNode {
    private FileNode fatherNode;//父节点
    private FileNode leftChileNode;//左孩子节点
    private FileNode prevNode;//前一个兄弟节点
    private FileNode nextNode;//后一个兄弟节点
    private String fileName;//文件名
    private int i_nlink=0;//链接数
    private boolean isDir;//是否是文件夹

    public FileNode getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(FileNode fatherNode) {
        this.fatherNode = fatherNode;
    }

    public FileNode getLeftChileNode() {
        return leftChileNode;
    }

    public void setLeftChileNode(FileNode leftChileNode) {
        this.leftChileNode = leftChileNode;
    }

    public FileNode getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(FileNode prevNode) {
        this.prevNode = prevNode;
    }

    public FileNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(FileNode nextNode) {
        this.nextNode = nextNode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getI_nlink() {
        return i_nlink;
    }

    public void addI_nlink() {
        this.i_nlink ++;
    }
    public void reduceI_nlink() {
        this.i_nlink --;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }
}
