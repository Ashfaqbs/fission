
- commands:
```
- gets us a temp node running env
C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>fission env create --name nodejs --image ghcr.io/fission/node-env
poolsize setting default to 3
environment 'nodejs' created


- gets us the code which will run on env .
C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>curl -LO https://raw.githubusercontent.com/fission/examples/main/nodejs/hello.js
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   119  100   119    0     0    284      0 --:--:-- --:--:-- --:--:--   285


 - takes the code and runs on top of temp node env.
C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>fission function create --name hello-js --env nodejs --code hello.js
Package 'hello-js-c105c968-df91-4fcc-8e02-e4e4a466d93b' created
function 'hello-js' created

- calling the function.

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\node>fission function test --name hello-js
hello, world!

```

- code which we downloaded :

```

module.exports = async function(context) {
    return {
        status: 200,
        body: "hello, world!\n"
    };
}

```

##  IMP Working explained  :

 Let's break this down in detail, starting with **how normal JavaScript code execution works** and then comparing it with **what Fission does**. and we will see the specific query about Node.js execution and shutdown as well in fission.

---

### **1. How Does Normal JavaScript Code Execution Work?**

When we write JavaScript (JS) code and execute it:

1. **Runtime Setup:**
   - we need **Node.js** installed locally or on a server.
   - Node.js provides the JavaScript runtime and the environment to execute our code. This includes features like asynchronous I/O, an event loop, and access to file systems, etc.

2. **Running the Code:**
   - we execute the JS file using the `node` command:
     ```bash
     node hello.js
     ```
   - The process starts the Node.js runtime, loads your JavaScript code, executes it, and then exits (if there's no long-running task like an HTTP server).

3. **Manual Scaling and Management:**
   - If we want this code to respond to multiple users (e.g., serve HTTP requests), you need to:
     - Write additional logic (like using `http` or `express` modules).
     - Deploy it on a server or container.
     - Manually manage scaling, restarts, updates, etc.

---

### **2. How Does Fission Work?**

With **Fission**, we move from a **code-centric approach** to a **function-centric approach**. Here's how it differs:

#### **Step-by-Step Process in Fission:**

1. **Predefined Runtime (Environment):**
   - Fission provides ready-made runtime environments for languages like Node.js, Python, etc. (e.g., `ghcr.io/fission/node-env`).
   - we don’t need to install or manage Node.js. Fission pulls the containerized environment automatically.

2. **Function Deployment:**
   - Instead of running an entire application, we only deploy small, **single-purpose functions** like `hello.js`.
   - Fission takes care of:
     - Packaging our function.
     - Associating it with the appropriate runtime.
     - Storing it in Kubernetes (ConfigMap, Secrets, or Persistent Volumes).

3. **Lazy-Loading Execution:**
   - When we invoke a function for the first time, Fission:
     - Selects an idle pod from the pool of pre-warmed containers (in this case, Node.js runtime pods).
     - Loads our function (e.g., `hello.js`) into the pod and executes it.
   - Once the function is loaded, subsequent requests are faster (until the pod is scaled down during inactivity).

4. **Scaling and Lifecycle:**
   - **Scaling:** Fission dynamically scales the environment pods up or down based on demand.
   - **Shutdown:** When a pod is idle for too long, Fission automatically scales it down to zero, saving resources.

---

### **Key Differences Between Normal JS Execution and Fission**

| **Aspect**               | **Normal JS Execution**                | **Fission Function Execution**                             |
|--------------------------|---------------------------------------|-----------------------------------------------------------|
| **Runtime Setup**        | You manually install Node.js.         | Runtime is prebuilt as a container (Node.js, Python, etc.).|
| **Code Deployment**      | Deploy entire application code.       | Deploy a single-purpose function.                         |
| **Scaling**              | Manual (e.g., load balancers).        | Automatic scaling based on demand.                        |
| **Startup**              | Starts Node.js for every execution.   | Uses pre-warmed runtime pods for faster execution.         |
| **Shutdown**             | You must explicitly stop the process. | Pods automatically scale down when idle.                  |
| **Management**           | Full responsibility for server setup. | Abstracted; Kubernetes and Fission handle the lifecycle.   |

---

### **3. What Happens When we Run the Fission Commands?**

Let’s address your thought process:

#### **“When I'm running the Node will be running, and post my code will run on top of it...”**

This is correct, but with **Fission**, the details are abstracted. Here's how it works step by step:

1. **Node.js Runtime Pod Initialization:**
   - Fission creates and maintains a pool of **Node.js runtime pods** (environments) in Kubernetes.

2. **Function Code Deployment:**
   - When you create the function (`hello.js`), Fission **does not immediately deploy it in a pod**.
   - Instead, it stores the function code in Kubernetes as a resource (like a ConfigMap).

3. **Function Invocation:**
   - When you call `fission function test`, Fission:
     - Finds an available Node.js runtime pod.
     - Loads our function code (`hello.js`) into the pod dynamically.
     - Executes the function and returns the output.

4. **Post-Execution:**
   - The pod stays running (for subsequent requests) unless it’s idle for a configurable time. If idle, Fission scales it down to zero.

---

### **Why Fission Shuts Down Pods?**

Fission follows a **serverless philosophy**, where:
- Resources are consumed **only when needed**.
- If there’s no demand, pods are stopped to save compute and memory resources in the Kubernetes cluster.

This contrasts with traditional setups where servers (or containers) stay up 24/7, consuming resources regardless of usage.

---

### **4. Advantages of Fission**

- **Cost-Efficient:** Only runs when needed.
- **Simpler Deployment:** No need to manage runtime environments.
- **Focus on Logic:** You only worry about the function, not the server/container.
- **Automatic Scaling:** Handles spikes and reduces to zero during inactivity.

---

- This function can be associated to a trigger making us accessible to call the function anywhere.