# jlambda
A simple CLI for authoring AWS Lambdas in java


![JLambda](https://github.com/hemantgs/jlambda/workflows/Java%20CI%20with%20Gradle/badge.svg)
![CodeQL](https://github.com/hemantgs/jlambda/workflows/CodeQL/badge.svg)

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
Usage: jlambda [-h] (-g=<path to generate lambda> | -b=<path to build package 
               to> | [-p=<publish> [-e=<env>]])
  -b, --build=<path to build package to>

  -e, --env=<env>
  -g, --generate=<path to generate lambda>

  -h, --help
  -p, --publish=<publish>

```
Jlambda provides three commands
* `./jlambda -g /your/path` This will generate a simple gradle java project with AWS Lambda dependencies pre-added. All you have to do is import the project .The Main class(AWS Lambda Handler) is by default at `com.lambda.basic.Handler`.It also has a ``default.properties`` file that has all necessary lambda configuration.
    * You can create your own `<env>.properties` to hold account specific configuration
All that is left is to add logic to your function
* `./jlambda -b /your/path` This will build the java project at the specified location into a deployment package(.zip).
* `./jlambda -p /your/path` will build and publish the deployment package to AWS using the config given in `default.properties` . Furthermore , you con use `./jlambda -p /your/path -e <env>` to deploy using environment specific configuration.



### Download
You can download the executable from [Releases](https://github.com/hemantgs/jlambda/releases/download/v0.0.4-alpha/jlambda)

P.S. Right now this is tested only in linux
