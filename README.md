# Introduction 
This is a sample of containerized microservice application.

![Architecture diagram](/media/DOIAB-Sample.png) 

# Getting Started
- Install DOCKER (and DOCKER-COMPOSE)
- Run `docker-compose up`
- Goto `http://localhost/api/prices` in browser

# Build and Test
There are two types of the Dockerfiles in each root folders: `Dockerfile` and `Dockerfile.builder`. First one is traditional docker file to build container from compiled sources. Second one is multi-stage file that compiles source code and builds container. When second option is used no compilers (like Java or Dotnet) are needed.

## Development tools
For *repo* and *collector* microservices **Java 11** and **Maven** (reasonably latest one) are needed. For *web* microservice **Dotnet Core 3.1** is required. Both tools are available for Windows and for Linux/MacOS. Generated files can be produced for both type of OSes.

### Detailed description of microservices
.TBD.