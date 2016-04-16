# The domino tutorial
## Purpose

This tutorial offers a way to learn Kotlin language, Neo4J graphDB and many other stuffs by following the development of a real project: **domino**.


## Description of the domino project

The domino project is a processing manager. Its purpose is:

* gives a way to build a processing tree by connecting tasks 
* launches processing in parallel
* manages errors and retries

### Why is project named domino?

![Image of the domino principles](http://www.enpratique.net/wp-content/uploads/2015/03/dominos...jpg)

How do we manage a processing ? We run the first task and when it's done, we run the second one. Each task result leads to another until the last one. Sometimes, the end of a task leads to launch two or more tasks in parallel and other times a task waits for the result of many previous tasks. Take a look [here](https://upload.wikimedia.org/wikipedia/commons/6/6d/Domino_01.jpg).

### Technical

Currently, the domino project is built on:

* Kotlin (a JVM language) http://kotlinlang.org/
* Neo4J graph database http://neo4j.com/
* Linux or OS X OS
* Bash scripts or Python

#### About the choice of the Neo4J database

The Neo4J database has been chosen because of:

* very good integration in JVM environment
* the Cypher query language
* the existence of a community edition of the database
* Isn't the best choice to save a tree representation into a graph DB ?
* Changes in the model are fast and easy.

#### About the choice of the Kotlin language

The main language is Kotlin because:

* It's well thought
* It offers some capabilities from dynamic languages like Python or Groovy while being a static language
* High-order functions
* Its null-safety system
* It's a JVM language (good integration with Java language and Java tools)

### Domain Driven Design

The domino project will be developed following the domain driven design (https://en.wikipedia.org/wiki/Domain-driven_design).
Often, use the domain driven design has an important time cost because changes are not easy with relational database.
But this cost is reduced thanks to usage of Neo4J DB.

## Who is behind the domino project?

I'm a French software architect and developer for more 15 years. I started with C/C++ and now I often develop with JVM
languages as Java8 or Kotlin and I tried others as Groovy, Clojure. I like the functional paradigm (I discovered it
with Clojure and Haskell) and I try to find the best compromise between functional and object programming. For me,
 nothing is bad: we have to use the best tool for a problem.

## So? 

Theses pages below will be update all along the development of this project.

1. What do we want domino to make ?
2. Entities definition
3. First development step: template management

