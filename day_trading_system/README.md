# day-trading-system
This is an implementation of a highly scalable day trading application utilizing tools such as caching and load balancing.

### Technologies 
<p>Utilized Express.js to develop scalable and robust web applications and broke down the application into Microservices, resulting in improved agility and scalability.</p>
<p>Containerized resources across multiple nodes in the distributed system(Docker).</p>
<p>Utilized MongoDB and efficiently store and retrieve data in web applications</p>
<p>Experienced in implementing Redis cache, leveraging its in-memory key-value store
capabilities to enhance application performance, reduce database load, and enable
efficient caching of frequently accessed data. </p>
<p>Skilled in utilizing Nginx load balancer to evenly distribute message processing
workloads across multiple consumer instances.</p>

#### Getting Started
<p>To get started with the application first clone the repository using
<code>git clone</code></p>
<p>
Run <code>docker-compose up --build --scale transact=n</code> where n is the number of transaction servers you want started up which will spin up
all the containers needed for the application</p>

##### Command Line
<p>Running the command line interface to test commands can be done by follwing these steps:</p>
1. switching into the <code>bin</code> folder with <code>cd command-line-app/bin</code>
2. <code>npm install</code>
3. <code>ts-node index.ts</code>

## Architecture
![architecture](https://user-images.githubusercontent.com/54200250/231621622-319bddfc-8787-4c3b-8ca2-14ffebe2d3c9.png)
