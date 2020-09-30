# jlambda
A simple CLI for authoring AWS Lambdas in java


![JLambda](https://github.com/hemantgs/jlambda/workflows/Java%20CI%20with%20Gradle/badge.svg)

### What is jlambda
jlambda is a simple command line tool that lets you generate java project with all AWS lambda 
dependencies built-in. One can simply add business logic to the generated project and use jlambda
once again to build a deployment package(.zip) and publish it to AWS .
For now jlambda makes use of the AWSs' DefaultCredentialResolver to obtain credentials to publish the lambda

### Why should I use jlambda
Java developers shy away from authoring AWS Lambda functions in Java owing to the inherent complexity in setting
up the project and publishing it. Jlambda hopes to alleviate some or most of that pain.
Jlambda is written in java for Java developers.

### How To
```
Usage: jlambda [-h] [-e=<profile>] (-g=<path to generate lambda> | -b=<path to 
               build package to> | -p=<path to packaged zip>)
  -b, --build=<path to build package to>

  -g, --generate=<path to generate lambda>

  -h, --help
  -p, --publish=<path to packaged zip>

```
`./jlambda -g /your/destination/path` will generate a simple java project with a Handler.java class
that will eventually be the handler parameter in AWS Lambda.

`./jlambda -p /your/project/path` will build the deployment package and publish to AWS

### Download
You can download the executable from [Releases](https://github.com/hemantgs/jlambda/releases/download/v0.0.3-alpha/jlambda)

P.S. Right now this is tested only in linux
