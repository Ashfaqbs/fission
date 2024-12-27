- Command line interface: 


```

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\python>fission env create --name python --image ghcr.io/fission/python-env
poolsize setting default to 3
environment 'python' created

- We created a simple py code in P0 folder

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\python>cd P0

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\python\P0>fission function create --name hello-py --env python --code hello.py
Package 'hello-py-b14e6c18-88cd-46c5-94f3-b5a3abb46ce2' created
function 'hello-py' created

C:\Users\ashfa\OneDrive\Desktop\My-Learning\fission\fission\samples\python\P0>fission function test --name hello-py
Hello, world!


```