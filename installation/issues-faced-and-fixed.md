- was not able to run the fission next day.

```



C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>minikube status
minikube
type: Control Plane
host: Running
kubelet: Running
apiserver: Running
kubeconfig: Configured
docker-env: in-use


C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>kubectl apply -f https://raw.githubusercontent.com/fission/fission/master/charts/fission-all/templates/crds.yaml
error: unable to read URL "https://raw.githubusercontent.com/fission/fission/master/charts/fission-all/templates/crds.yaml", server reported 404 Not Found, status code=404

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>minikube stop
✋  Stopping node "minikube"  ...
🛑  Powering off "minikube" via SSH ...
🛑  1 node stopped.

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>minikube delete
🔥  Deleting "minikube" in docker ...
🔥  Deleting container "minikube" ...
🔥  Removing C:\Users\ashfa\.minikube\machines\minikube ...
💀  Removed all traces of the "minikube" cluster.

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>minikube start
😄  minikube v1.34.0 on Microsoft Windows 11 Home Single Language 10.0.26100.2605 Build 26100.2605
    ▪ MINIKUBE_ACTIVE_DOCKERD=minikube
✨  Automatically selected the docker driver. Other choices: virtualbox, ssh
📌  Using Docker Desktop driver with root privileges
👍  Starting "minikube" primary control-plane node in "minikube" cluster
🚜  Pulling base image v0.0.45 ...
🔥  Creating docker container (CPUs=2, Memory=4000MB) ...
❗  Failing to connect to https://registry.k8s.io/ from inside the minikube container
💡  To pull new external images, you may need to configure a proxy: https://minikube.sigs.k8s.io/docs/reference/networking/proxy/
🐳  Preparing Kubernetes v1.31.0 on Docker 27.2.0 ...
    ▪ Generating certificates and keys ...
    ▪ Booting up control plane ...
    ▪ Configuring RBAC rules ...
🔗  Configuring bridge CNI (Container Networking Interface) ...
🔎  Verifying Kubernetes components...
    ▪ Using image gcr.io/k8s-minikube/storage-provisioner:v5
🌟  Enabled addons: default-storageclass, storage-provisioner
🏄  Done! kubectl is now configured to use "minikube" cluster and "default" namespace by default

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>kubectl version
Client Version: v1.30.5
Kustomize Version: v5.0.4-0.20230601165947-6ce0bf390ce3
Server Version: v1.31.0

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>kubectl create -k "github.com/fission/fission/crds/v1?ref=v1.20.5"
customresourcedefinition.apiextensions.k8s.io/canaryconfigs.fission.io created
customresourcedefinition.apiextensions.k8s.io/environments.fission.io created
customresourcedefinition.apiextensions.k8s.io/functions.fission.io created
customresourcedefinition.apiextensions.k8s.io/httptriggers.fission.io created
customresourcedefinition.apiextensions.k8s.io/kuberneteswatchtriggers.fission.io created
customresourcedefinition.apiextensions.k8s.io/messagequeuetriggers.fission.io created
customresourcedefinition.apiextensions.k8s.io/packages.fission.io created
customresourcedefinition.apiextensions.k8s.io/timetriggers.fission.io created

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm repo add fission-charts https://fission.github.io/fission-charts/
"fission-charts" already exists with the same configuration, skipping

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm list
NAME    NAMESPACE       REVISION        UPDATED STATUS  CHART   APP VERSION

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm repo remove fission-charts
"fission-charts" has been removed from your repositories

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm repo add fission-charts https://fission.github.io/fission-charts/
"fission-charts" has been added to your repositories

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm repo update
Hang tight while we grab the latest from your chart repositories...
...Successfully got an update from the "fission-charts" chart repository
Update Complete. ⎈Happy Helming!⎈

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm install --version v1.20.5 --namespace fission fission fission-charts/fission-all
Error: INSTALLATION FAILED: create: failed to create: namespaces "fission" not found

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>kubectl create namespace fission
namespace/fission created

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>kubectl create -k "github.com/fission/fission/crds/v1?ref=v1.20.5"
Error from server (AlreadyExists): error when creating "github.com/fission/fission/crds/v1?ref=v1.20.5": customresourcedefinitions.apiextensions.k8s.io "canaryconfigs.fission.io" already exists
Error from server (AlreadyExists): error when creating "github.com/fission/fission/crds/v1?ref=v1.20.5": customresourcedefinitions.apiextensions.k8s.io "environments.fission.io" already exists
Error from server (AlreadyExists): error when creating "github.com/fission/fission/crds/v1?ref=v1.20.5": customresourcedefinitions.apiextensions.k8s.io "functions.fission.io" already exists
Error from server (AlreadyExists): error when creating "github.com/fission/fission/crds/v1?ref=v1.20.5": customresourcedefinitions.apiextensions.k8s.io "httptriggers.fission.io" already exists
Error from server (AlreadyExists): error when creating "github.com/fission/fission/crds/v1?ref=v1.20.5": customresourcedefinitions.apiextensions.k8s.io "kuberneteswatchtriggers.fission.io" already exists
Error from server (AlreadyExists): error when creating "github.com/fission/fission/crds/v1?ref=v1.20.5": customresourcedefinitions.apiextensions.k8s.io "messagequeuetriggers.fission.io" already exists
Error from server (AlreadyExists): error when creating "github.com/fission/fission/crds/v1?ref=v1.20.5": customresourcedefinitions.apiextensions.k8s.io "packages.fission.io" already exists
Error from server (AlreadyExists): error when creating "github.com/fission/fission/crds/v1?ref=v1.20.5": customresourcedefinitions.apiextensions.k8s.io "timetriggers.fission.io" already exists

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm repo add fission-charts https://fission.github.io/fission-charts/
"fission-charts" already exists with the same configuration, skipping

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>kubectl delete -k "github.com/fission/fission/crds/v1?ref=v1.20.5"
customresourcedefinition.apiextensions.k8s.io "canaryconfigs.fission.io" deleted
customresourcedefinition.apiextensions.k8s.io "environments.fission.io" deleted
customresourcedefinition.apiextensions.k8s.io "functions.fission.io" deleted
customresourcedefinition.apiextensions.k8s.io "httptriggers.fission.io" deleted
customresourcedefinition.apiextensions.k8s.io "kuberneteswatchtriggers.fission.io" deleted
customresourcedefinition.apiextensions.k8s.io "messagequeuetriggers.fission.io" deleted
customresourcedefinition.apiextensions.k8s.io "packages.fission.io" deleted
customresourcedefinition.apiextensions.k8s.io "timetriggers.fission.io" deleted

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node> kubectl create namespace fission
Error from server (AlreadyExists): namespaces "fission" already exists

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>kubectl create -k "github.com/fission/fission/crds/v1?ref=v1.20.5"
customresourcedefinition.apiextensions.k8s.io/canaryconfigs.fission.io created
customresourcedefinition.apiextensions.k8s.io/environments.fission.io created
customresourcedefinition.apiextensions.k8s.io/functions.fission.io created
customresourcedefinition.apiextensions.k8s.io/httptriggers.fission.io created
customresourcedefinition.apiextensions.k8s.io/kuberneteswatchtriggers.fission.io created
customresourcedefinition.apiextensions.k8s.io/messagequeuetriggers.fission.io created
customresourcedefinition.apiextensions.k8s.io/packages.fission.io created
customresourcedefinition.apiextensions.k8s.io/timetriggers.fission.io created

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm repo add fission-charts https://fission.github.io/fission-charts/
"fission-charts" already exists with the same configuration, skipping

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm repo remove fission-charts
"fission-charts" has been removed from your repositories

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm repo add fission-charts https://fission.github.io/fission-charts/
"fission-charts" has been added to your repositories

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm repo update
Hang tight while we grab the latest from your chart repositories...
...Successfully got an update from the "fission-charts" chart repository
Update Complete. ⎈Happy Helming!⎈

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>helm install --version v1.20.5 --namespace fission fission fission-charts/fission-all
W1227 08:49:55.077808     508 warnings.go:70] metadata.name: this is used in Pod names and hostnames, which can result in surprising behavior; a DNS label is recommended: [must not contain dots]
NAME: fission
LAST DEPLOYED: Fri Dec 27 08:49:52 2024
NAMESPACE: fission
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
1. Install the client CLI.

Mac:
  $ curl -Lo fission https://github.com/fission/fission/releases/download/v1.20.5/fission-v1.20.5-darwin-amd64 && chmod +x fission && sudo mv fission /usr/local/bin/

Linux:
  $ curl -Lo fission https://github.com/fission/fission/releases/download/v1.20.5/fission-v1.20.5-linux-amd64 && chmod +x fission && sudo mv fission /usr/local/bin/

Windows:
  For Windows, you can use the linux binary on WSL. Or you can download this windows executable: https://github.com/fission/fission/releases/download/v1.20.5/fission-v1.20.5-windows-amd64.exe

2. You're ready to use Fission!
  You can create fission resources in the namespace "default"

  # Create an environment
  $ fission env create --name nodejs --image fission/node-env --namespace default

  # Get a hello world
  $ curl https://raw.githubusercontent.com/fission/examples/master/nodejs/hello.js > hello.js

  # Register this function with Fission
  $ fission function create --name hello --env nodejs --code hello.js --namespace default

  # Run this function
  $ fission function test --name hello --namespace default
  Hello, world!

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>fission env create --name nodejs --image ghcr.io/fission/node-env
poolsize setting default to 3
environment 'nodejs' created

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>curl -LO https://raw.githubusercontent.com/fission/examples/main/nodejs/hello.js
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   119  100   119    0     0    284      0 --:--:-- --:--:-- --:--:--   285

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>fission function create --name hello-js --env nodejs --code hello.js
Package 'hello-js-c105c968-df91-4fcc-8e02-e4e4a466d93b' created
function 'hello-js' created

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>fission function test --name hello-js
hello, world!

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>



C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>fission version
client:
  fission/core:
    BuildDate: "2024-10-04T07:43:36Z"
    GitCommit: 352090d0
    Version: v1.20.5
server:
  fission/core:
    BuildDate: "2024-10-04T07:43:36Z"
    GitCommit: 352090d0
    Version: v1.20.5

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>

```