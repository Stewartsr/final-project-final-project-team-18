#CS 492 Final Project 
Code and demo due by 5:00pm on Friday, 3/22/2018
In this course, a final programming project will take the place of formal exams to test your understanding of the material.  The final project will involve working with a team of 3-4 people to implement a substantial Android app that utilizes the major features we’ve looked at this term.  Specifically, you and your teammates will write an app that satisfies all of these requirements:

It should have multiple activities the user can navigate between.
It should use at least one implicit intent to launch another app.
It should communicate via HTTP(s) with a third-party API to provide data for the app and optionally to send data back to the API.
It must implement activity lifecycle methods to ensure that activity-related data is handled elegantly through lifecycle events.
It should either store user preferences (via SharedPreferences) or store data in device storage (using SQLite). You may do both of these things if you want.
It should have a polished, well-styled user interface.

You’ve written your proposals already, so you should know what app you’re going to work on.  This document contains a few more details about the process for the project.
GitHub repositories
The code for your final project must be in a GitHub repository set up via GitHub Classroom.  You can use this link to form your team and create your final project repository:

https://classroom.github.com/g/-FhUjpYJ

The repository created for your team will be public by default, and I encourage you to keep it public.  These final projects should be nice demonstrations of your Android development abilities and will be a good item to have in your CS portfolio.  It will be great to have the code in a public GitHub repo so you can share it easily when you want to.  However, you will have full administrative control over the repository that’s created for your project, which means you’ll be able to make it private if you wish.

If you’ve already started a GitHub repo for your project, don’t worry.  The repository created via the GitHub classroom link above will be completely empty, so you can simply use git remotes to work with both repositories.  I can help you set that up if needed.
Working with a team on a shared GitHub repo

When working with a team on a shared GitHub repo, it’s a good idea to use a workflow that uses branches and pull requests.  This has a few advantages:

By not working within the same branch, you can better avoid causing conflicts, which can occur when you and another member of your team edit the same parts of the code at the same time.
It helps you to be more familiar with the entire code base, even the parts that other team members are working on, because you’ll see all of the changes to the code as you review pull requests.  This can help you develop more rapidly because you won’t have to spend as much time understanding code that others have written.
It helps to ensure high quality code.  Code in pull requests is not incorporated into the master code branch until the code request is reviewed and approved.  That means everyone has a chance to improve pull request code before it becomes permanent.

One simple but effective branch- and pull-request-based workflow you might consider is the GitHub flow: https://guides.github.com/introduction/flow/.
Grading demonstrations
The grade for your project will include a brief (10-15 minute) demonstration to me of your project’s functionality.  To get a grade for your project, your team must do a demo.  Demonstrations will be scheduled for finals week.  I’ll send more details on scheduling via email.
Code submission
All code for your final project must be pushed to the master branch of the repo created for your team using the GitHub Classroom link above before your grading demo.
Grading criteria
Your team’s grade (out of 150 points) for the final project will be based on successfully implementing a complete Android app that satisfies the criteria listed above.  Remember, if your team does not do a demo for your project, you will receive a zero for it.
Individual grades
Your individual grade for the project will be based on your team’s grade and also on evidence of your meaningful participation in your team’s work on the project, including from these sources:

The commit log of your GitHub repository.
Your presence at and participation in your team’s project demo.
Individual team evaluation.

In particular, if your GitHub commit log shows that you did not make meaningful contributions to your team’s implementation of your app, if you do not participate in your team’s demonstration of your app (without explicit prior approval by me), or if your teammates indicate in their team evaluations that you didn’t meaningfully contribute to the team’s effort, you will receive a lower grade on the project than your teammates.  I may use other sources as evidence of your participation, as well.
