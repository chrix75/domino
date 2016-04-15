# Overview

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

