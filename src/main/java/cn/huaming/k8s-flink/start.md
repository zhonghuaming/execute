#### 配置[kubernetes RBAC权限](https://kubernetes.io/zh/docs/reference/access-authn-authz/rbac/)
##### 方式一
```
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: fabric8-rbac
subjects:
  - kind: ServiceAccount
    # Reference to upper's `metadata.name`
    name: default
    # Reference to upper's `metadata.namespace`
    namespace: default
roleRef:
  kind: ClusterRole
  name: cluster-admin
  apiGroup: rbac.authorization.k8s.io
```
```
kubectl apply -f fabric8-rbac.yaml
```
##### 方式二
```
kubectl create serviceaccount flink -n default

kubectl create clusterrolebinding flink-role-binding-flink -n default  --clusterrole=edit --serviceaccount=default:flink
```


#### 启动任务
```
./bin/flink run-application -p 8 -t kubernetes-application \
-Dkubernetes.cluster-id=my-first-cluster \
-Dtaskmanager.memory.process.size=4096m \
-Dkubernetes.taskmanager.cpu=2 \
-Dtaskmanager.numberOfTaskSlots=4 \
-Dkubernetes.container.image=hb.shencom.cn/scloud/demo-flink-app:zhm-20210831-stream \
-Dkubernetes.namespace=default \
-Dkubernetes.jobmanager.service-account=flink \
-Dkubernetes.rest-service.exposed.type=LoadBalancer \
-Dkubernetes.rest-service.annotations=service.beta.kubernetes.io/aws-load-balancer-type:nlb \
local:///opt/flink/usrlib/WordCount.jar


./bin/flink run-application \
    --target kubernetes-application \
    -Dkubernetes.cluster-id=my-first-application-cluster \
    -Dkubernetes.container.image=hb.shencom.cn/scloud/demo-flink-app:zhm-20210831-stream \
    local:///opt/flink/usrlib/WordCount.jar
```