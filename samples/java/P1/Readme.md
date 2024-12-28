# Steps involved:
























### Command line :

```
- we cannot provide the jar to a function we can provide a package to function when using maven
- convert the jar to package.

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission package create --name smp-pkg --env java  --deploy smp.jar
Package 'smp-pkg' created

-- verification of our package

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission package list
NAME                                               BUILD_STATUS ENV    LASTUPDATEDAT       NAMESPACE
smp-pkg                                            succeeded    java   28 Dec 24 12:02 IST default
main-076c390d-2055-43eb-a8f3-14a84029609d          succeeded    py-env 27 Dec 24 18:51 IST default
hello-java-sb-bf79db0c-74aa-4527-aadf-273b8862ef09 succeeded    jvm    27 Dec 24 17:22 IST default
hello-java-da7bb4be-2f9f-444d-b341-4d7eb34fc130    succeeded    java   27 Dec 24 17:03 IST default
hello-java-990d5e82-a6c5-4928-a984-8e68ce93a7ac    succeeded    java   27 Dec 24 16:58 IST default
hello-java-ea1c0b8a-80e0-4c07-abd3-f04c2586dd2c    succeeded    java   27 Dec 24 16:57 IST default
hello-go-369b7a31-33fd-4951-87b4-96ddc4125d8b      succeeded    go     27 Dec 24 12:38 IST default
hello-go-c911ba31-abbd-4cfc-93f1-7f8e0dedf926      succeeded    go     27 Dec 24 12:33 IST default
hello-py-b14e6c18-88cd-46c5-94f3-b5a3abb46ce2      succeeded    python 27 Dec 24 12:29 IST default
hello-js-c105c968-df91-4fcc-8e02-e4e4a466d93b      succeeded    nodejs 27 Dec 24 08:51 IST default

- create the function while passing the package 
C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission function create --name smp  --env java  --pkg smp-pkg --entrypoint com.ashfaq.example.simple_maven_project.HelloFunction
function 'smp' created

- create the trigger for the function

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission httptrigger create --name smp-trigger --url /hello --method GET  --function smp

Options:
  --function=[]            Name(s) of the function for this trigger. (If 2 functions are supplied with
                           this flag, traffic gets routed to them based on weights supplied with --weight
                           flag.)
  --url=''                 URL pattern (See gorilla/mux supported patterns) [DEPRECATED for 'fn create',
                           use 'route create' instead]
  --name=''                HTTP trigger name
  --method=[GET]           HTTP Methods: GET,POST,PUT,DELETE,HEAD. To mention single method: --method
                           GET and for multiple methods --method GET --method POST. [DEPRECATED for 'fn
                           create', use 'route create' instead]
  --createingress=false    Creates ingress with same URL
  --ingressrule=''         Host for Ingress rule: --ingressrule host=path (the format of host/path
                           depends on what ingress controller you used)
  --ingressannotation=[]   Annotation for Ingress: --ingressannotation key=value (the format of
                           annotation depends on what ingress controller you used)
  --ingresstls=''          Name of the Secret contains TLS key and crt for Ingress (the usability of TLS
                           features depends on what ingress controller you used)
  --weight=[]              Weight for each function supplied with --function flag, in the same order.
                           Used for canary deployment
  --spec=false             Save to the spec directory instead of creating on cluster
  --dry=false              View the generated specs
  --prefix=''              Prefix with which functions are exposed. NOTE: Prefix takes precedence over
                           URL/RelativeURL [DEPRECATED for 'fn create', use 'route create' instead]
  --keepprefix=false       Keep the prefix in the URL while forwarding request to the function

Global Options:
  --verbosity=1 (-v)   CLI verbosity (0 is quiet, 1 is the default, 2 is verbose)
  --kube-context=''    Kubernetes context to be used for the execution of Fission commands
  --namespace='' (-n)  If present, the namespace scope for this CLI request

Usage:
  fission httptrigger create [options]

Error: Error while creating HTTP Trigger: HTTPTrigger with same Host, URL & method already exists (a4931be8-aa9a-4c92-a874-197b33359146)

- lets delete the trigger and recreate the HTTP trigger.

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission httptrigger list
NAME                                 METHOD URL    FUNCTION(s)   INGRESS HOST PATH   TLS ANNOTATIONS NAMESPACE
a4931be8-aa9a-4c92-a874-197b33359146 [GET]  /hello hello-java-sb false   *    /hello                 default
main                                 [POST] /main  main          true    *    /main                  default


C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission httptrigger delete --name a4931be8-aa9a-4c92-a874-197b33359146
trigger 'a4931be8-aa9a-4c92-a874-197b33359146' deleted

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission httptrigger create --name smp-trigger --url /hello --method GET  --function smp
trigger 'smp-trigger' created

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission function test --name smp

Options:
  --name=''               Function name
  --method=[GET]          HTTP Methods: GET,POST,PUT,DELETE,HEAD. To mention single method: --method GET
                          and for multiple methods --method GET --method POST. [DEPRECATED for 'fn create',
                          use 'route create' instead]
  --header=[] (-H)        Request headers
  --body='' (-b)          Request body
  --query=[] (-q)         Request query parameters: -q key1=value1 -q key2=value2
  --timeout=1m0s (-t)     Length of time to wait for the response. If set to zero or negative number, no
                          timeout is set
  --dbtype='kubernetes'   Log database type, e.g. influxdb (currently influxdb and kubernetes logs are
                          supported)
  --subpath=''            Sub Path to check if function internally supports routing

Global Options:
  --verbosity=1 (-v)   CLI verbosity (0 is quiet, 1 is the default, 2 is verbose)
  --kube-context=''    Kubernetes context to be used for the execution of Fission commands
  --namespace='' (-n)  If present, the namespace scope for this CLI request

Usage:
  fission function test [options]

Error: error executing HTTP request: Get "http://127.0.0.1:57783/fission-function/smp": function request timeout (60000000000)s exceeded

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission function logs --name smp

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission function logs --name smp


- function not working as expected might be response entity issue or somethingelse . 


- try pointing to docker env and test the function but same issue observed.
C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>minikube docker-env
SET DOCKER_TLS_VERIFY=1
SET DOCKER_HOST=tcp://127.0.0.1:59701
SET DOCKER_CERT_PATH=C:\Users\ashfa\.minikube\certs
SET MINIKUBE_ACTIVE_DOCKERD=minikube
REM To point your shell to minikube's docker-daemon, run:
REM @FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>@FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission function test --name smp

Options:
  --name=''               Function name
  --method=[GET]          HTTP Methods: GET,POST,PUT,DELETE,HEAD. To mention single method: --method GET
                          and for multiple methods --method GET --method POST. [DEPRECATED for 'fn create',
                          use 'route create' instead]
  --header=[] (-H)        Request headers
  --body='' (-b)          Request body
  --query=[] (-q)         Request query parameters: -q key1=value1 -q key2=value2
  --timeout=1m0s (-t)     Length of time to wait for the response. If set to zero or negative number, no
                          timeout is set
  --dbtype='kubernetes'   Log database type, e.g. influxdb (currently influxdb and kubernetes logs are
                          supported)
  --subpath=''            Sub Path to check if function internally supports routing

Global Options:
  --verbosity=1 (-v)   CLI verbosity (0 is quiet, 1 is the default, 2 is verbose)
  --kube-context=''    Kubernetes context to be used for the execution of Fission commands
  --namespace='' (-n)  If present, the namespace scope for this CLI request

Usage:
  fission function test [options]

Error: error executing HTTP request: Get "http://127.0.0.1:51479/fission-function/smp": function request timeout (60000000000)s exceeded

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>fission function logs --name smp

C:\Users\ashfa\OneDrive\Desktop\My-Learning\Java\Code\SB-k8s\simple-maven-project>




```

- Response entity issue :
```
we had used ResponseEntity from spring web but some suggested to use response from Fission package
but obeserved the error import io.fission.Response; as this did not exist in library.

package com.ashfaq.example.simple_maven_project;

import io.fission.Function;
import io.fission.Context;
import io.fission.Response;

public class HelloFunction implements Function {
    @Override
    public Response call(Context context) {
        return Response.builder()
            .body("Hello World!")
            .status(200)
            .header("Content-Type", "text/plain")
            .build();
    }
}

```



- resource clean up
```
cmd to delete the function and package

fission function delete --name smp
fission package delete --name smp-pkg

```