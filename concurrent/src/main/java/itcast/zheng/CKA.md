# CKA

## 第一题 RBAC

**分数：4%**

题目：

​	创建一个名为 `deployment-clusterrole` 的 `clusterrole` ，该 `clusterrole` 只允许创建 `Deployment`、`Daemonset`、`Statefulset`的 `create` 操作。

​	在名字为`app-team1`的`namespace`下创建一个名为`cicd-token`的`serviceAccount`，并且将上一步创建`clusterrole`的权限绑定到该`serviceAccount`

解题：

```bash
kubectl create clusterrole deployment-clusterrole --verb=create --resource=Deployment,Daemonset,Statefulset
kubectl create serviceaccount -n app-team1 cicd-token
kubectl create rolebinding -n app-team1 d01 --serviceaccount=app-team1:cicd-token --clusterrole=deployment-clusterrole
```

## 第二题 节点驱逐

**分数：4%**

题目：

​	将`ek8s-node-1`节点设置为不可用，然后重新调度该节点上的所有Pod

解题：

```bash
kubectl cordon ek8s-node-1
kubectl drain ek8s-node-1 --ignore-daemonsets
```

## 第三题 集群升级

**分数：7%**

题目：

​	现有的 `kubernetes` 集群运行版本 `1.25.3`，仅将主节点上的所有Kuberbetes控制平面和节点组件升级到 `1.25.4`。

​	另外，在主节点上升级 `kubelet` 和 `kubectl`。

​	确保升级之前`drain`主节点，并在升级后`undrain`主节点，请不要升级工作节点、etcd、container管理器、CNI插件、DNS服务或其他插件。

解题：

```shell
# drain 主节点
kubectl drain k8s-master --ignore-daemonsets
# 升级组件 
sudo apt-get update 
sudo apt-get install kubeadm=1.25.4-00
sudo kubeadm upgrade apply 1.25.4 --etcd-upgrade=false
sudo apt-get install kubelet=1.25.4-00 kubectl=1.25.4-00
sudo systemctl restart kubelet
# 检查
kubelet --version
kubectl version
kubeadm version
kubectl get node
# undrain 主节点
kubectl undrain k8s-master
```

## 第四题 ETCD 备份与恢复

**分数：7%**

题目：

​	首先，为运行在 `https://127.0.0.1:2379` 上的现有 `etcd` 实例创建快照并将快照保存到 `/data/backup/etcd-snapshot.db`

​	然后还原位于 `/data/backup/etcd-snapshot-previous.db` 的现有先前快照。

```shell
export ETCDCTL_API=3
# 备份
etcdctl --endpoints=https://127.0.0.1:2379 --cacert=/opt/KUIN00601/ca.crt --
cert=/opt/KUIN00601/etcd-client.crt --key=/opt/KUIN00601/etcd-client.key
snapshot save /data/backup/etcd-snapshot.db
# 恢复 
etcdctl snapshot restore /srv/data/etcd-snapshot-previous.db
```

## 第五题 网络策略

**分数：7%**

文档：https://kubernetes.io/zh-cn/docs/concepts/cluster-administration/logging/

​	**概念 > 服务、负载均衡和联网 > 网络策略**

题目：

​	在现有的 namespace `my-app` 中创建一个名为 `allow-port-from-namespace` 的新的 `NetworkPolicy`。

​	确保新的 `NetworkPolicy` 允许 namespace `my-app` 中的 Pods 来连接到 namespace `big-corp` 中的端口8080。

​	进一步确保新的 `NetworkPolicy`：

​		**不允许**对没有在监听端口 `8080` 的 Pods 的访问。

​		**不允许**不来自 namespace `my-app` 中的 Pods 的访问。

```shell
vi 5.netp.yaml

apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-port-from-namespace
  namespace: my-app
spec:
  podSelector: {}
  policyTypes:
    - Ingress
  ingress:
  - from:
      - namespaceSelector:
          matchLabels:
            name: big-corp
      - ports:
        - protocol: TCP
          port: 8080
          
kubectl apply -f 5.netp.yaml 
```

## 第六题 创建 Service

**分数：7%**

题目：

​	请重新配置现有的部署`front-end`以及添加名为`http`的端口规范来公开现有容器`nginx`的端口`80/tcp`。

​	创建一个名为`front-end-svc`的新服务，以公开容器端口`http`。

​	配置此服务，以通过在排定的节点的`NodePort`来公开各个`Pods`。

```shell
kubectl edit deployment front-end
...
    containers:
    - image: nginx
      imagePullPolicy: Always
      name: nginx
      # 添加一下内容
      ports:
      - name: http 
        containerPort: 80
...
kubectl expose deployment front-end --port=80 --target-port=80 --type=NodePort --name=front-end-svc
```

## 第七题 Ingress

**分数：7%**

文章: https://kubernetes.io/zh-cn/docs/concepts/services-networking/ingress/#the-ingress-resource

​	**概念 > 服务、负载均衡和联网 > Ingress资源**

题目：

​	如下创建一个新的Nginx Ingress资源：

​		名称：`pong`

​		Namespace：`ing-internal`

​		使用服务端口`5678`在路径`/hello`上公开服务`hello`

```shell
vi 7.ing.yaml

apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: pong
  namespace: ing-internal
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  ingressClassName: nginx-example
  rules:
  - http:
      paths:
      - path: /hello
        pathType: Prefix
        backend:
          service:
            name: hellp
            port:
              number: 5678
```

## 第八题 扩容 Deployment

**分数：4%**

题目：

​	将 Deployment 从 `loadbalancer` 扩展至`5`pods

```bash
kubectl scale presentation loadbalancer --replicas=5
```

## 第九题 调度 Pod

**分数：4%**

题目：

​	按照如下要求调度一个`pod`

​		名称：`nginx-kusc00401`

​		image：`nginx`

​		Node selector： `disk=ssd`

```shell
vi 9.pod.yaml
apiVersion: v1
kind: Pod
metadata:
  labels:
    run: nginx-kusc00401
  name: nginx-kusc00401
spec:
  containers:
  - image: nginx
    name: nginx-kusc00401
  nodeSelector:
    dist: ssd
kubectl apply -f 9.pod.yaml
```

## 第十题 统计节点数

**分数：4%**

考题：

​	检查有多少 Worker nodes 已准备就绪（不包括被打上 Taint： `NoSchedule` 的节点），并将数量写入`/opt/KUSC00402/kusc00402.txt`

```shell
kubectl describe node |grep -i taint
echo 2 > /opt/KUSC00402/kusc00402.txt
```

## 第十一题 创建多容器 Pod

**分数：4%**

题目：

​	创建一个名为 `kucc4` 的 pod，在pod里面分别为以下每个 images 单独运行一个 app container （可能会有1~4个images): `nginx + redis + memcached`

```shell
kubectl run kucc4 --image nginx --dry-run=client -oyaml > 11.pod.yaml
vim 11.pod.yaml

apiVersion: v1
kind: Pod
metadata:
  name: kucc4
spec:
  containers:
  - image: nginx
    name: nginx
	- image: redis
		name: redis
	- imags: memcached
		name: memcached
```

## 第十二题 PV

**分数：4%**

文章：https://kubernetes.io/zh-cn/docs/tasks/configure-pod-container/configure-persistent-volume-storage/

​	**任务 > 配置 Pods 和容器 > 配置 Pod 以使用 PersistentVolume 作为存储**

题目：

​	创建名为 `app-data` 的 persistent volume，容量 `2Gi`，访问模式为 `ReadWriteOnce` 。 volume 类型为 `hostPath` ，位于`/srv/app-data`

```shell
vim 12.pv.yaml

apiVersion: v1
kind: PersistentVolume
metadata:
  name: app-data
spec:
  capacity:
    storage: 2Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: "/srv/app-data"
    
kubectl apply -f 12.pv.yaml 
```

## 第十三题 PVC

分数：7%

文章：https://kubernetes.io/zh-cn/docs/tasks/configure-pod-container/configure-persistent-volume-storage/

​	**任务 > 配置 Pods 和容器 > 配置 Pod 以使用 PersistentVolume 作为存储**

题目：

​	创建一个新的 `PersistentVolumeClaim`

​		名称：`pv-volume`

​		class：`csi-hostpath-sc`

​		容量：`10Mi`

​	创建一个新的Pod，此Pod将作为Volume挂载到 `PersistentVolumeClaim`:

​		名称：`web-server`

​		image: `nginx`

​		挂载路径：`		`

​		配置新的Pod，以对Volume具有`ReadWriteOnce`权限。

​	最后，使用 `kubectl edit` 或 `kubectl patch` 将 `PersistentVolumeClaim` 的容量扩展为 `70Mi`，并记录更改。

```shell
vi 13.pvc.yaml

apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: pv-volume
spec:
  storageClassName: csi-hostpath-sc
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 10Mi
---
apiVersion: v1
kind: Pod
metadata:
  name: web-server
spec:
  volumes:
    - name: task-pv-storage
      persistentVolumeClaim:
        claimName: pv-volume
  containers:
    - name: task-pv-container
      image: nginx
      volumeMounts:
        - mountPath: "/usr/share/nginx/html"
          name: task-pv-storage
# 执行生效
kubectl apply -f 13.pvc.yaml
# 修改PVC 70Mi 需要使用 --record 保存更改
kubectl edit pvc pv-volume --record
```

## 第十四题 保存错误日志

**分数：5%**

题目：

​	监控 pod bar 的日志并：

​		提取与错误 `file-not-found` 相对应的日志行

​		将这些日志行写入 `/opt/KUTR00101/bar`

```shell
kubectl logs bar |grep file-not-found > /opt/KUTR00101/bar
```

## 第十五题 sidecar 容器

**分数：7%**

文章：https://kubernetes.io/zh-cn/docs/concepts/cluster-administration/logging

​	概念 > 集群管理 > 日志架构

题目：

​	将一个现有的 Pod 集成到 Kubernetes 的内置日志记录体系结构中（例如 kuberctl logs）。添加 streaming sidecar 容器是实现此要求的一种好方法。

​	使用 `busybox` Image 来将名为 `sidecar` 的 sidecar 容器添加到现有的 Pod `legacy-app` 中。新的 sidecar 容器必须执行以下命令 `/bin/sh -c tail -n+1 -f /var/log/legacy-app.log`

​	使用安装在 /var/log 的 Volume，试日志文件 `legacy-app.log` 可用于 sidecar 容器。

解答：

```shell
kubectl get pod legacy-app -o yaml > 15.pod.yaml 
vim 15.pod.yaml
# 添加边车容器与挂载点

metadata:
  name: leagcy-app
spec:
  # 添加挂载点
  volumes:
  - name: varlog
    emptyDir: {}
    
  containers:
  - name: xx
    image: xx
    # 挂载日志
    volumeMounts:
    - name: varlog
      mountPath: /var/log
  # 添加边车容器
  - name: sidecar
    image: busybox
    args: [/bin/sh, -c, "tail -n+1 -f /var/log/legacy-app.log"]
    volumeMounts:
    - name: varlog 
      mountPath: /var/log
      
# 删除原有 pod
kubectl delete pod leagcy-app
# 使用新 yaml 文件创建
kubectl apply -f 15.pod.yaml
```

## 第十六题 top 命令

**分数：5%**

题目：

​	通过 Pod label `name=cpu-utilizer`，找到找到占用大量 CPU 的 pod，并将占用 CPU 最高的 pod 名称写入文件`/opt/KUTR00401/KUTR00401.txt`（已存在）

解题：

```shell
kubectl top pod -l name=cpu-utilizer --sort-by="cpu" -A 
echo XXXX > /opt/KUTR00401/KUTR00401.txt
```

## 第十七题 集群故障排查

**分数：4%**

题目：

​	名为 `wk8s-node-0` 的 Kubernetes worker node 处于 `NotReady` 状态。调查发生这种情况的原因，并采用相应措施将 node 恢复为 `Ready` 状态，确保所做的任何更改永久有效。

解题：

```shell
systemctl status kubelet
systemctl start kubelet
systemctl enable kubelet
```

## 环境初始化 

### 命令

```shell
# 第1题
kubectl create namespace app-team1
# 第4题
sudo docker cp `sudo docker ps |grep etcd |grep -v "POD"|awk '{print $1}'`:/usr/local/bin/etcdctl /usr/bin/
# 第5题
kubectl create namespace my-app
# 第6题
kubectl create deployment front-end  --image nginx
# 第7题
kubectl create deployment presentation --image nginx --replicas 2
```

