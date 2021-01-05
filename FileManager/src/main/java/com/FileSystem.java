package com;

import java.io.File;
import java.util.Map;

public class FileSystem {
    private static FileIndex fileIndex=new FileIndex();
    private static FileNode currentNode=null;
    public FileSystem(String rootCatalog){
        System.out.println("根目录为"+rootCatalog);
        //创建为根节点
        FileNode headNode=new FileNode();
        headNode.setFileName(rootCatalog);
        fileIndex.getIndex().put(rootCatalog,headNode);
        cd("c");
    }
    //切换到指定目录下
    public void cd(String pathName){
        currentNode = fileIndex.getIndex().get(pathName);
        if (currentNode==null){
            System.out.println("目录不存在");
        }
    }
    //显示目录下所有目录和文件,如果未指定则显示当前目录下的目录和文件
    public void dir(String pathName){
        if (pathName==null){
            dir();
            return ;
        }
        FileNode fatherNode =fileIndex.getIndex().get(pathName);
        if (fatherNode==null){
            System.out.println("不存在该目录");
            return;
        }
        showChildNode(fatherNode);
    }
    public void dir(){
        showChildNode(currentNode);
    }
    //在当前路径下创建目录
    public void md(String fileName){
        if (fileName.contains("/")){
            mdWithPathName(fileName);
            return;
        }
        if (currentNode==null){
            System.out.println("请选择当前路径");
            return;
        }
        //获取路径名
        String pathName = getPathName(fileName);
        setNewNode(currentNode,pathName,true);
        }
    //在指定路径下创建目录
    private void mdWithPathName(String newPathName){
        //截取父节点主键名
        String fatherPathName = newPathName.substring(0, newPathName.lastIndexOf("/"));
        //判断父节点是否为空
        FileNode fatherNode=(FileNode)fileIndex.getIndex().get(fatherPathName);
        if (fatherNode==null){
            System.out.println("请输入正确的路径");
            return;
        }
        setNewNode(fatherNode,newPathName,true);
    }
    /*
    * 删除目录，先删除子节点，再删去本身
    * */
    public void rd(String pathName){
        FileNode deleteNode;
        deleteNode = fileIndex.getIndex().get(pathName);
        System.out.println("删除目录："+pathName);
        if (deleteNode==null){
            System.out.println("目录不存在");
            return;
        }
        //把该目录从索引表中删除
        fileIndex.getIndex().remove(pathName);
        deleteAllChildNode(deleteNode);
        destroyNode(deleteNode);
    }
    //在当前目录下新建文件
    public void edit(String fileName){
        //判断是在当前节点下创建还是指定路径下
        if (fileName.contains("/")){
            editWithPathName(fileName);
            return;
        }
        //判断文件名是否重复
        boolean repeat = isRepeat(currentNode,fileName);
        if (repeat){
            System.out.println("文件名重复");
            return;
        }
        String pathName = getPathName(fileName);
        //设置新节点，传入文件标志
        setNewNode(currentNode,pathName,false);
    }
    //在指定目录下新建文件
    private void editWithPathName(String pathName){
        //获取父节点
        String fatherPathName = pathName.substring(0, pathName.lastIndexOf("/"));
        FileNode fatherNode = fileIndex.getIndex().get(fatherPathName);
        if (fatherNode==null){
            System.out.println("请输入正确的路径");
            return;
        }
        //获取文件名
        String fileName=pathName.substring(pathName.indexOf("/")+1);
        boolean repeat = isRepeat(fatherNode, fileName);
        if (repeat){
            System.out.println("文件名重复");
            return;
        }
        setNewNode(fatherNode,pathName,false);
    }
    //指定路径删除文件
    public void del(String pathName){
        System.out.println("删除文件："+pathName);
        //根据父节点主键获取父节点
        String fatherPathName = pathName.substring(0,pathName.lastIndexOf("/"));
        FileNode fatherNode = fileIndex.getIndex().get(fatherPathName);
        //获取文件名
        String fileName = pathName.substring(pathName.lastIndexOf("/")+1);
        //找到要删除的节点
        FileNode nextNode=fatherNode.getLeftChileNode();
        boolean flag=false;//判断文件是否存在
        while (nextNode!=null){
            if (nextNode.getFileName().equals(fileName)){
                flag=true;
             destroyNode(nextNode);
             break;
            }
            nextNode=nextNode.getNextNode();
        }
        if (flag==false){
            System.out.println("文件不存在");
            return;
        }
    }
    /*
    *删除节点所有的被引用值，如果是根节点，就删除所有的字节点
    * */
    private void destroyNode(FileNode deleteNode){
        if (deleteNode==null){
            System.out.println("指定目录不存在");
            return;
        }
        //与要删除节点相联系的节点
        FileNode nextNode = deleteNode.getNextNode();
        FileNode prevNode = deleteNode.getPrevNode();
        FileNode fatherNode = deleteNode.getFatherNode();
        FileNode childNode=deleteNode.getLeftChileNode();
        if (deleteNode.isDir()&&childNode!=null){
            System.out.println("目录不为空，是否确认删除");
        }
        if (fatherNode==null){
            if (childNode!=null)
                deleteAllChildNode(deleteNode);
            setNullNode(deleteNode);
            deleteNode=null;
            return;
        }
        //无左兄弟和右兄弟
        if (nextNode==null&&prevNode==null){
            fatherNode.setLeftChileNode(null);
            fatherNode.reduceI_nlink();
            if (childNode!=null)
            childNode.setFatherNode(null);
            setNullNode(deleteNode);
            deleteNode=null;
            return;
        }
        //无右兄弟有左兄弟
        if (nextNode==null&&prevNode!=null){
            prevNode.setNextNode(null);
            prevNode.reduceI_nlink();
            if (childNode!=null)
            childNode.setFatherNode(null);
            setNullNode(deleteNode);
            deleteNode=null;
            return;
        }
        //有右兄弟无左兄弟
        if (nextNode!=null&&prevNode==null){
            //把右兄弟设为父节点新的左孩子
            fatherNode.setLeftChileNode(nextNode);
            nextNode.setPrevNode(null);
            nextNode.reduceI_nlink();
            if (childNode!=null)
            childNode.setFatherNode(null);
            setNullNode(deleteNode);
            deleteNode=null;
            return;
        }
        //有左兄弟和右兄弟
        prevNode.setNextNode(nextNode);
        nextNode.setPrevNode(prevNode);
        if (childNode!=null)
        childNode.setFatherNode(null);
        setNullNode(deleteNode);
        deleteNode=null;
        return;
    }
    //设置新节点
    private void setNewNode(FileNode fatherNode,String pathName,boolean isDir){
        //定义新节点
        FileNode newNode=new FileNode();
        //设置文件夹还是文件
        newNode.setDir(isDir);
        //设置文件名
        String catalogName=pathName.substring(pathName.lastIndexOf("/")+1);
        newNode.setFileName(catalogName);

        if (newNode.isDir()&&fileIndex.getIndex().get(pathName)!=null){
            System.out.println("目录名重复");
            return;
        }
        //设置父节点
        newNode.setFatherNode(fatherNode);
        newNode.addI_nlink();
        //找到父节点的左孩子
        FileNode chileNode = fatherNode.getLeftChileNode();
        //没有左孩子则把新的节点设为左孩子
        if (chileNode==null){
            fatherNode.setLeftChileNode(newNode);
            fatherNode.addI_nlink();
            if (newNode.isDir()) {
                System.out.println("文件夹路径(索引表主键)：" + pathName);
                fileIndex.getIndex().put(pathName, newNode);
            }
            else {
                System.out.println("文件路径：" + pathName);
            }
            return;
        }
        //找到最后一个右兄弟
        FileNode nextNode=chileNode.getNextNode();
        while (nextNode!=null){
            chileNode=nextNode;
            nextNode=nextNode.getNextNode();
        }
        //把最后一个右兄弟设置成新节点的左兄弟
        newNode.setPrevNode(chileNode);
        newNode.addI_nlink();
        //把新的节点设置到最右边
        chileNode.setNextNode(newNode);
        chileNode.addI_nlink();
        //如果是文件夹就加入索引表中
        if (newNode.isDir()){
            //加入索引表中
            fileIndex.getIndex().put(pathName,newNode);
            System.out.println("文件夹路径(索引表主键)："+pathName);
        }else {
            System.out.println("文件路径："+pathName);
        }
    }
    //删除所有子节点
    private void deleteAllChildNode(FileNode fatherNode){
        FileNode leftChileNode = fatherNode.getLeftChileNode();
        //设置一个中间节点
        FileNode tempNode;
        //右兄弟
        FileNode nextNode;
        //如果左孩子为空
        if (leftChileNode==null){
            return;
        }
        //设置父节点的左孩子为空
        fatherNode.setLeftChileNode(null);
        fatherNode.reduceI_nlink();
        //遍历父节点下所有的节点,并删除
        while (leftChileNode!=null) {
            //如果是文件夹，从索引表中删除
          if (leftChileNode.isDir()){
                deleteFromIndex(leftChileNode);
            }
            //找到当前左孩子的右兄弟
            nextNode = leftChileNode.getNextNode();
            //找到下一左孩子
            tempNode=leftChileNode.getLeftChileNode();
            //将该节点置空
            destroyNode(leftChileNode);
            leftChileNode=null;
            leftChileNode=tempNode;
            while (nextNode!= null) {
                //如果是文件夹，从索引表中删除
                if (nextNode.isDir()){
                    deleteFromIndex(nextNode);
                }
                tempNode=nextNode.getNextNode();
                destroyNode(nextNode);
                nextNode =null;
                nextNode = tempNode;
            }
        }
    }
    //判断文件名是否重复
    private boolean isRepeat(FileNode fatherNode,String fileName){
        FileNode leftChileNode = fatherNode.getLeftChileNode();
        if (leftChileNode==null){
            return false;
        }

        FileNode nextNode = leftChileNode;
        while (nextNode!=null){
            if (nextNode.getFileName().equals(fileName)){
                return true;
            }
            nextNode=nextNode.getNextNode();
        }
        return false;
    }
    //在当前路径下，获取路径名
    private static String  getPathName(String fileName){
        //获取路径名
        StringBuilder pathName=new StringBuilder();
        pathName.append(currentNode.getFileName()+"/"+fileName);
        FileNode fatherNode = currentNode.getFatherNode();
        while (fatherNode!=null){
            pathName.insert(0,fatherNode.getFileName()+"/");
            fatherNode=fatherNode.getFatherNode();
        }
        return pathName.toString();
    }
    //根据节点，获取路径
    private void deleteFromIndex(FileNode node){
            Map<String, FileNode> index = fileIndex.getIndex();
            for (Map.Entry<String,FileNode> entry:index.entrySet()){
                if (entry.getValue()==node){
                    index.remove(entry.getKey());
                    break;
                }
            }
    }
    private void setNullNode(FileNode node){
        node.setFatherNode(null);
        node.setPrevNode(null);
        node.setNextNode(null);
        node.setLeftChileNode(null);
        node=null;
    }
    //显示父节点下所有文件
    private void showChildNode(FileNode fatherNode){
        FileNode chileNode = fatherNode.getLeftChileNode();
        if (chileNode==null){
            System.out.println("目录下无任何文件");
            return;
        }
        String fileName=chileNode.isDir()?"文件夹名：":"文件名：";
        System.out.println(fileName+chileNode.getFileName());
        FileNode nextNode=chileNode.getNextNode();
        while (nextNode!=null){
            String name=nextNode.isDir()?"文件夹名：":"文件名：";
            System.out.println(name+nextNode.getFileName());
            nextNode=nextNode.getNextNode();
        }
    }
    public void visitMap(){
        Map<String, FileNode> index = fileIndex.getIndex();
        for (Map.Entry<String,FileNode> entry:index.entrySet()){
            System.out.println("索引表"+entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
