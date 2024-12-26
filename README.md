# Fission
Open source Kubernetes-native Serverless Framework

## What is Fission?

Fission is a fast, open source serverless framework for Kubernetes with a focus on developer productivity and high performance.

Fission operates on just the code: Docker and Kubernetes are abstracted away under normal operation, though you can use both to extend Fission if you want to.

Fission is extensible to any language; the core is written in Go, and language-specific parts are isolated in something called environments (more below). Fission currently supports NodeJS, Python, Go, Java, Ruby, PHP, Bash, and any Linux executable, with more languages coming soon.


## Performance: 100msec cold start
Fission maintains a pool of “warm” containers that each contain a small dynamic loader. When a function is first called, i.e. “cold-started”, a running container is chosen and the function is loaded. This pool is what makes Fission fast: cold-start latencies are typically about 100msec.

##  Kubernetes is the right place for Serverless
We’re built on Kubernetes because we think any non-trivial app will use a combination of serverless functions and more conventional microservices, and Kubernetes is a great framework to bring these together seamlessly.

Building on Kubernetes also means that anything you do for operations on your Kubernetes cluster — such as monitoring or log aggregation — also helps with ops on your Fission deployment.
