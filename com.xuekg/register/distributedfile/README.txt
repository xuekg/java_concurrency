如果说要做edits log，场景，你现在执行一个命令，hadoop fs -mkdir /usr/warehosue，创建一个目录，非常简单，
两件：在内存里的文件目录树中加入对应的目录节点；在磁盘里写入一条edits log，记录本次元数据的修改

hdfs client去创建目录的话，会给hdfs NameNode发送一个rpc接口调用的请求，调用人家的mkdir()接口，在那个接口里就会完成上述的两件事情

接下来咱们其实主要是做两件事情，第一件是在内存文件目录树中，加入进去对应的一个目录节点，第二件事情是在edits log写入磁盘文件

FSNamesystem，其实是作为NameNode里元数据操作的核心入口，负责管理所有的元数据的操作，但是在里面的话呢，他可能会调用其他的组件完成相关的事情

FSDirectory，专门负责管理内存中的文件目录树

FSEditLog，专门负责管理写入edits log到磁盘文件里去
