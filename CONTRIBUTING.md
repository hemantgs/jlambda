# Contributing

Thanks for taking a look at jlambda

### Building and running Jlambda locally

To build and run JLambda locally, run the following commands:

```shell
$ ./gradlew clean build
$ cat make.sh build/libs/jlambda.jar > jlambda && chmod +x jlambda
```

This will generate jlambda as a binary in the root of the project.(This will change soon [tracked here](https://github.com/hemantgs/jlambda/issues/18))

Instead of building a binary you can run the `App.java` class via Intellij with commands 

## How to Contribute

### Reporting Bugs and Issues

Report bugs and issues by creating a [new GitHub issue](https://github.com/hemantgs/jlambda/issues).
Ensure that the issue is not already created

### GitHub Workflow

1. Fork the `hemantgs /jlambda` repository into your GitHub account.

2. Clone your fork of the GitHub repository, replacing `<username>` with your GitHub username.

   Use ssh (recommended):

   ```bash
   git clone git@github.com:<username>/jlambda.git
   ```

   Or https:

   ```bash
   git clone https://github.com/<username>/jlambda.git
   ```

3. Add a remote to keep up with upstream changes.

   ```bash
   git remote add upstream https://github.com/hemantgs/jlambda.git
   ```

   If you already have a copy, fetch upstream changes.

   ```bash
   git fetch upstream
   ```

4. Create a feature branch to work in.

   ```bash
   git checkout -b feature-xxx remotes/upstream/master
   ```

5. Work in your feature branch.

   ```bash
   git commit -a
   ```

6. Periodically rebase your changes

   ```bash
   git pull --rebase
   ```

7. When done, combine ("squash") related commits into a single one

   ```bash
   git rebase -i upstream/master
   ```

   This will open your editor and allow you to re-order commits and merge them:
   - Re-order the lines to change commit order (to the extent possible without creating conflicts)
   - Prefix commits using `s` (squash) or `f` (fixup) to merge extraneous commits.

8. Submit a pull-request

   ```bash
   git push origin feature-xxx
   ```

   Go to your fork main page

   ```bash
   https://github.com/<username>/jlambda
   ```

  GitHub will automatically pop up a `Compare & pull request` button for any branches you recently pushed to. If you click that button it will automatically offer you to submit your pull-request.


9. Addressing code review comments

   Repeat steps 5. through 7. to address any code review comments and rebase your changes if necessary.

   Push your updated changes to update the pull request

   ```bash
   git push origin [--force] feature-xxx
   ```

   `--force` may be necessary to overwrite your existing pull request in case your
  commit history was changed when performing the rebase.


