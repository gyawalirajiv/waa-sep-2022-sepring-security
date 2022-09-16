# Lab 5 - Spring Security

You can continue to your last lab assignment to add security with `JWT` to your project.

### Requirements
--- 

* There are two roles `admin` and `user`.
    * `admin` can access all endpoints.
    * `user` can access only `/products` endpoint.
* Use `UserDetailsService` to load the user from the database.
* Create `UaaController` to implement `signin` and `signup` endpoints.
* Make necessary changes to set `user_id` in `product` with the current request holder's user id when `saveProducts` is
  invoked.
    * You can access the current request holder via `SecurityContextHolder`.
* Generated token should be valid for 15 minutes.
* Use `AOP` to filter out any offensive words.
    * You can use any kind of offensive words dictionary or you can even create one for testing.
    * Assuming `spring` is an offensive word and if the input is : `springing`, it should be filtered out as `******ing`
      .
        * Create `WaaOffensiveWords` aspect.
    * If the same user sends more than 5 different requests that contain offensive words in last 30 minutes, do not
      accept the requests of this user for next 15 minutes and return the error
      message `Max Bad Words Requests Limit has been Reached. You need wait for X minutes.` Change `X` with remaining
      time of the ban.
        * This implementation should be `stateless`. You cannot hold any data in the server's memory. You can design
          table/tables to implement this feature.
        * To practice further (optional), you can use `redis` instead of using `PostgreSQL`.
        * Create `WaaRequestFilter` aspect.
* Continue to your UI project and implement `signin` and `signup` functionality.
* Make necessary changes in your UI project to communicate with backend server.
    * Send the token in each request except for `signin` and `signup` functions.
    * You can store the token in the`localStorage`.

### Technical Details
---

* Use n-tier software architecture model.
* Use DTOs.
* PostgreSQL is recommended as a Relational Database system.
* Populate your database with dummy data using `data.sql`.

## Submission

* Fork this repository and push your changes.
* Once you finished your project, send a Pull Request. (Send only one Pull Request once you finish the assignment.)

### Important Notes
---

* You are not allowed to share codes with your classmates. If detected, you will get NC.
* **For pairs:**
    * Individual's work will be checked from the commits.
    * Share tasks evenly and fairly.
    * To have a clearer understanding of pair programming:
        *  > **Pair programming** is an agile software development technique in which two programmers work together at one workstation. One, the _driver_, writes code while the other, the _observer_ or _navigator_ reviews each line of code as it is typed in. The two programmers switch roles frequently.
        * [Wikipedia](https://en.wikipedia.org/wiki/Pair_programming#:~:text=Pair%20programming%20is%20an%20agile,two%20programmers%20switch%20roles%20frequently.)

- Remember to respect the code honor submission policy. All written code must be original. Presenting something as one’s
  own work when it came from another source is plagiarism and is forbidden.

- Plagiarism is a very serious thing in all American academic institutions and is guarded against vigilantly by every
  professor.

