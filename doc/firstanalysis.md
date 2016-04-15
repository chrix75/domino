# First analysis

Let's imagine: we want a system in which we define a processing by settings tasks todo. Some tasks may depend on previous tasks or not.

## A non-technical case: A simple recipe

It's the evening and we want to eat chicken à la sauce Normande with pasta. So, how do we do that?

First, we can sum-up tasks to do like:

![Simple recipe](https://raw.githubusercontent.com/chrix75/domino/master/doc/images/recipe_001.png)

This recipe has a starting and an ending point (Recipe and Eat) and 2 tasks (prepare the pasta and cook the chicken). In our case, we consider tasks are done in parallel.

It's a general view but it's possible to have a finer view:

![Simple recipe with steps](https://raw.githubusercontent.com/chrix75/domino/master/doc/images/recipe_002.png)

In this view, we see a task is divided into steps. Some of them are done in parallel and others are done sequentially.

Taking a look at this recipe... Something is missing: quantities. We can consider those as task parameters that are shared with steps. Like that:

![Simple recipe with parameters](https://raw.githubusercontent.com/chrix75/domino/master/doc/images/recipe_003.png)

We notice we could replace pasta by vegetables while keeping the chicken cooking.


## First definitions

With our first case, we can define these points:

1. A processing is a group of tasks.
2. A task is seen as group of steps.
3. A task may have many parameters.
3. Tasks and steps may be run in parallel.
4. Some tasks or steps are launched only when previous ones are over.
5. Some steps use parameters of their parent task. We can say step has parameters too.


## Generalization

Below, our first model for domino:

![1st model](https://raw.githubusercontent.com/chrix75/domino/1eb082b20d19e283fa5100076db674cfe333850f/doc/images/processing_classes.png)

This model isn't about execution, it describes only a static point-of-view. Some decisions are been taken:

* A task must have at least one step
* All task parameters must initialize at least one step parameter


## A more technical case: Find duplicates between 2 files

We want to define a task that searches records with the same keys from 2 files. We obtain that diagram:

![Duplicates processing](https://raw.githubusercontent.com/chrix75/domino/master/doc/images/duplicates.png)

In the diagram above, we see the task has 2 steps to do:

1. Sort 2 files (those sorting are done in parallel)
2. Search the duplicates between the 2 sorted files. That means this step starts only when the 2 sorting are made.

We get more information about the task. First, there are two sorts of parameters:

* the task's parameters that are **input** parameters
* the steps' parameters that are *internal* parameters

The input parameters are set by the user of the task while internal parameters are set by the system itself while the running.
In the diagram, we notice some step parameters are initialized directly by the input parameters when others are set from other step parameters.

For example: 

* the input file of the first sort step is `inputFileOne` parameter of the task
* the `secondFile` of the search duplicates step is initialized with the value of the internal parameter `sortedFile` of the first sort step



## Changes in the model

We simplify model by removing the TaskParameter entity.

![Model v2](https://raw.githubusercontent.com/chrix75/domino/master/doc/images/processing_classes_v2.png)



