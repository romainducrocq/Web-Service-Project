# GIT BASICS

### Help
    git help

### Log in
    git config --global user.email "my-email"
    git config --global user.name "my-username"

### Log in with SSH
    https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/connecting-to-github-with-ssh

### Get existing project
    * git clone https://github.com/my/repo.git # HTTPS
    * git clone git@github.com:my/repo.git # SSH

### Infos
    * git status
    * git log -2 number of logs
    * git remote

### Add files to commit
    * git add my-file
    * git add . # add all new or changed files

### Set changes
    * git commit -m "my-message" # added files
    * git commit -a -m "my-message" # all files
    git push origin master # remote branch

### Get changes
    git pull origin master # remote branch

### Create new project
    git init
    > Create a remote on github
    * git remote add origin https://github.com/my/repo.git # HTTPS
    * git remote add origin git@github.com:my/repo.git # SSH

### Create branch
    git branch "my-new-branch"

### Switch branch
    * git checkout "my-branch"
    * git checkout -b "my-new-branch" # Create branch and switch

### Merge branches
    > Switch to branch to merge to
    git merge "my-branch-to-merge-from"

## GITHUB +

### Features
> Fork
Issues
Host a website

### Host a website
> \> Create a gh-pages branch
\> Set as default branch
\> Remove master branch
\> Get url in settings
\> Create index.html and content

    > Create remote my-project-repo on github
    cd my-project-repo
    git init
    git remote add origin https://github.com/username/my-project-repo.git
    git checkout -b gh-pages
    git add .
    git commit -m "Commit my project."
    git push origin gh-pages
    https://username.github.io/my-project-repo/

