# CalculatorSimple

How to commit change in Adnroid Studio

 Open the project you want to push in Android Studio.

Click VCS -> Enable version Control Integration -> Git

There doesn't seem to be a way to add a remote through the GUI. So open Git Bash in the root of the project and do git remote add <remote_name> <remote_url>

Now when you do VCS -> Commit changes -> Commit & Push you should see your remote and everything should work through the GUI.

If you are getting the error: fatal: remote <remote_name> already exists that means you already added it. To see your remotes do git remote -v and git remote rm <remote_name> to remove.
