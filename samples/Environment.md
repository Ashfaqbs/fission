When creating an **Environment** in Fission, we configure the runtime for our functions. The environment specifies the runtime image (e.g., Python, Node.js, Java) and settings like resource limits, pool sizes, and timeouts. Below is a detailed explanation of the options we can use when creating an environment.

---

## Environment Creation Options and Explanation

### 1. **Basic Options**

#### a. `--name`
- **Description**: The name of the environment.
- **Example**:
  ```bash
  --name java-jdk21
  ```

#### b. `--image`
- **Description**: The Docker image to use for the environment. This image provides the runtime (e.g., `python:3.9`, `openjdk:21`).
- **Example**:
  ```bash
  --image openjdk:21 - no
  --image fission/jvm-env
  ```

#### c. `--builder`
- **Description**: (Optional) The image to use for building the code (used in case of source packages).
- **Example**:
  ```bash
  --builder fission/java-builder - dont work 
  --builder fission/jvm-builder -- correct
  ```

#### d. `--poolsize`
- **Description**: The number of pods to keep in the pool for this environment. Defaults to 3.
- **Example**:
  ```bash
  --poolsize 5
  ```

---

### 2. **Resource Configuration**

#### a. `--requests`
- **Description**: Set the minimum CPU and memory requests for the environment's pods.
- **Example**:
  ```bash
  --requests "cpu=200m,memory=256Mi"
  ```

#### b. `--limits`
- **Description**: Set the maximum CPU and memory limits for the environment's pods.
- **Example**:
  ```bash
  --limits "cpu=500m,memory=512Mi"
  ```

---

### 3. **Timeouts**

#### a. `--mincpu` and `--maxcpu`
- **Description**: Configure autoscaling behavior based on CPU usage.
- **Example**:
  ```bash
  --mincpu 100m --maxcpu 500m
  ```

#### b. `--timeout`
- **Description**: The maximum duration (in seconds) a function in this environment can run before timing out.
- **Example**:
  ```bash
  --timeout 120
  ```

---

### 4. **Special Options**

#### a. `--keeparchive`
- **Description**: Keeps the package archive for debugging or inspection purposes.
- **Example**:
  ```bash
  --keeparchive
  ```

#### b. `--version`
- **Description**: Specifies the version of the environment. Useful for versioned images.
- **Example**:
  ```bash
  --version 1.0.0
  ```

#### c. `--namespace`
- **Description**: The Kubernetes namespace where the environment is created.
- **Example**:
  ```bash
  --namespace custom-namespace
  ```

---

### Example Commands

#### 1. **Basic Environment**
Create a simple environment for Java functions with JDK 21:
```bash
fission env create \
  --name java-jdk21 \
  --image openjdk:21 \
  --poolsize 3
```

#### 2. **Environment with Resource Limits**
Create a Python environment with custom resource configurations:
```bash
fission env create \
  --name python-3.9 \
  --image fission/python-env:3.9 \
  --requests "cpu=100m,memory=128Mi" \
  --limits "cpu=500m,memory=256Mi" \
  --poolsize 5
```

#### 3. **Environment with Builder**
Create a Java environment with a builder image for source packages:
```bash
fission env create \
  --name java-builder-env \
  --image openjdk:21 \
  --builder fission/java-builder \
  --poolsize 2
```

#### 4. **Environment with Timeout**
Create a Node.js environment with a custom function timeout:
```bash
fission env create \
  --name node-env \
  --image fission/node-env:16 \
  --timeout 180
```

---

### Managing Environments

1. **List All Environments**
   ```bash
   fission env list
   ```

2. **Update an Environment**
   Modify the environment settings (e.g., pool size):
   ```bash
   fission env update \
     --name java-jdk21 \
     --poolsize 10
   ```

3. **Delete an Environment**
   Remove an environment by its name:
   ```bash
   fission env delete --name java-jdk21
   ```

4. **Inspect Environment Pods**
   Check the running pods for a specific environment:
   ```bash
   kubectl get pods -n fission-function -l environmentName=java-jdk21
   ```

---

### Comprehensive Example
Here’s a complete example for creating a Java environment with resource limits, a builder, and a custom pool size:

```bash
fission env create \
  --name java-jdk21 \
  --image openjdk:21 \
  --builder fission/java-builder \
  --poolsize 5 \
  --requests "cpu=200m,memory=256Mi" \
  --limits "cpu=1,memory=1Gi" \
  --timeout 120
```

**Explanation**:
- Uses JDK 21 runtime.
- Supports building Java functions with the builder image.
- Allocates 5 pods in the pool.
- Sets CPU and memory requests/limits.
- Enforces a 120-second timeout for function execution.


Note:
Resource Configuration:
The format shown for resource requests and limits is outdated.
Correct format should be:

```
--resources='{"requests":{"memory":"256Mi","cpu":"100m"},"limits":{"memory":"512Mi","cpu":"200m"}}'
```


- CPU Configuration:
The --mincpu and --maxcpu flags are shown incorrectly. These are not valid flags for environment creation.
The correct way to handle CPU scaling is through the resources configuration mentioned above.

-  Timeout Configuration:
The document suggests using --timeout for environment creation, but this flag is actually used for function creation, not environment creation.


- Version Flag:
The --version flag is shown as an option but is not actually supported in current Fission versions for environment creation.


- Pool Size:
While the --poolsize flag exists, the document doesn't mention that it's only applicable when using the poolmgr executor type.
Correct note should include:

```
# Only applicable with poolmgr executor
--poolsize 3
```

- Pod Inspection Command:
The command shown for inspecting environment pods is incomplete.
Correct command should include all relevant labels:
```
kubectl get pods -n fission-function -l environmentName=java,envName=java
```
