package register.distributedfile;

import concurrency_6.SpinLockDemo;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xuekg
 * @description 负责管理内存中的文件目录树的核心组件
 * @date 2021/7/31 16:22
 **/
public class FSDirectory {

    /**
     * 内存中的文件目录树
     */
    private INodeDirectory dirTree;

    public FSDirectory() {
        this.dirTree = new INodeDirectory("/");
    }

    /**
     * 创建目录
     *
     * @param path 目录路径
     */
    public void mkdir(String path) {
        // path = /usr/warehouse/hive
        // 你应该先判断一下，“/”根目录下有没有一个“usr”目录的存在
        // 如果说有的话，那么再判断一下，“/usr”目录下，有没有一个“/warehouse”目录的存在
        // 如果说没有，那么就得先创建一个“/warehosue”对应的目录，挂在“/usr”目录下
        // 接着再对“/hive”这个目录创建一个节点挂载上去

        //纯内存操作
        synchronized (dirTree) {
            String[] pathes = path.split("/");
            INodeDirectory parent = null;

            for (String splitPath : pathes) {
                if (splitPath.trim().equals("")) {
                    continue;
                }

                INodeDirectory dir = this.findDirectory(dirTree, splitPath);
                if (dir != null) {
                    parent = dir;
                    continue;
                }

                INodeDirectory child = new INodeDirectory(splitPath);
                parent.addChild(child);
            }
        }
    }

    /**
     * 对文件目录树递归查找目录
     *
     * @param dirTree
     * @param splitPath
     * @return
     */
    private INodeDirectory findDirectory(INodeDirectory dirTree, String splitPath) {
        if (dirTree.getChildren().size() == 0) {
            return null;
        }

        INodeDirectory result = null;

        for (INode child : dirTree.getChildren()) {
            if (child instanceof INodeDirectory) {
                INodeDirectory childDir = (INodeDirectory) child;
                if (childDir.getPath().equals(splitPath)) {
                    return childDir;
                }

                result = findDirectory(childDir, splitPath);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }


    /**
     * 代表的是文件目录树中的一个节点
     *
     * @author zhonghuashishan
     */
    private interface INode {

    }

    /**
     * 代表文件目录树中的一个目录
     *
     * @author zhonghuashishan
     */
    public static class INodeDirectory implements INode {

        private String path;
        private List<INode> children;

        public INodeDirectory(String path) {
            this.path = path;
            this.children = new LinkedList<INode>();
        }

        public void addChild(INode inode) {
            this.children.add(inode);
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<INode> getChildren() {
            return children;
        }

        public void setChildren(List<INode> children) {
            this.children = children;
        }

    }

    /**
     * 代表文件目录树中的一个文件
     *
     * @author zhonghuashishan
     */
    public static class INodeFile implements INode {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
