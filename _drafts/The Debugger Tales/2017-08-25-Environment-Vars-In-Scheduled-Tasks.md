---
layout: post
title: "The Debugger Tales #1"
subtitle: Environment Variables In Scheduled Tasks
excerpt: "This post covers a short story about why you shouldn't use profile related environment vars in
scripts. Especially if you need to run them as scheduled tasks."
image: /img/image/tdt1.png
show-avatar: true
bigimg: /img/bigimg/tdt_back.png
category: "The Debugger Tales"
tags: [The Debugger Tales, Scheduled Tasks, Environment Vars]

# ---- Theme based optional vars ---- #
comments: true

# use-site-title: # (false) | true
---
{% include about.html %}
## The Scenario
I recently worked on a controller script to synchronize new active directory users with a two-factor authentication
solution. The controller script itself calls different functions from preexisting modules. It is triggered by the
windows task scheduler on its dedicated job server.

The described scenario worked for about a year but suddenly the responsible sysop reported a problem:

> **SysOp:** "Hi Marco, We've got a problem with your 2FA Sync tool. I didn't change anything but it stopped working
this morning. Could you take a look at it?"
>
> **Me:** "Sure. But what do you mean exactly with **it stopped working**? Did you get an error message in the
powershell console or an corresponding eventlog entry?"
>
> **SysOp:** "Well, I didn't get the daily reports and the needed files were not created."
>
> **Me:** "Ok.Just give me a couple of minutes. It shouldn't be a big problem!"

{: .box-note}
*... And that was truly my assumption. It turned out I would search for a solution the whole day.*

## Analysis

### Manual Execution

So first things first. I checked if someone broke the dependencies with the modules and tried to run the script
itself.

```console
~> cd C:\Scripts\2FA
~> .\Invoke-2FAUserSync.ps1 -MailDest 'marco.blessing@gmail.com' -WhatIf -Debug -Verbose
```
![Console Run Result](/img/posts/2FAConsoleRun.jpg)

Ok it works. There are some user related warnings, but it doesn't matter right now.

### Execute it like a scheduled tasks

Next step is to check if the the scheduled task itself is able to call it. Therefore I take the command itself form
the task and try to run it. Either form the *Start->Run* window or the *command shell*

```console
C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe -NoProfile -NonInteractive -ExecutionPolicy Bypass -Command "&{C:\Scripts\2FA\Invoke-2FAUserSync.ps1 -MailDest 'marco.blessing@gmail.com' -WhatIf}"
```

This returned the same results like in first run in the powershell console. Thats great. Up to now it means, there
is no major problem with the script itself and the execution command string with its arguments is correct.

### Run as scheduled task

So lets finally run the task itself -> That's strange - It works as well. I expected it to fail like the Sysop
colleague said.

### Run the scheduled task triggered by time event

Let's schedule the task to run it once in 5 minutes up to now. -> Well, it worked again, and again, and again.
So I called my colleague and told him I can't reproduce the error and it seems to work again. I scheduled the
task as usual to run every morning 5 am and logged off.

The next morning came - The same problem occurred. So I repeated every test again and tried it at least 10 times.

## The Problem

Later this day someone forgot accidentally to log off with the service account and I could reproduce it:
If the task gets triggered while the service account was logged in or locked, it runs without any problems. But if
someone logs off the service account, like you normally would after you finished your work remotely, it fails.

So I continued inspecting the task and double checked the task details. But it didn't work either.

I stumbled upon a post from the [Rambling Cookie Monster](http://ramblingcookiemonster.github.io/Task-Scheduler/).
While reading the article started wondering if I used any types or assemblies which doesn't work in the non
interactive mode. This would explain why i ran in a user context while the service account is logged in. So I
checked the scripts again and even the other modules which I used.

However, there are two things I'm wondering about:

- How could it be, that this script runs for about a year now an nobody noticed the problem earlier?
  This means basically no one logged of the service account for about a year.
- And second: No one tested it correctly before it is used in a production environment!


## The Cause
What if the environment variables I'm using is causing this? - So I decided to create an other scheduled task which
writes all the variable values into a temp file. Afterwards it should be easy to compare the values.

The scheduled tasks looks like this:

```console
# Run
C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe

# Args
-NoLogo -NonInteractive -NoProfile -Command "&{
    Get-Date | Out-File 'C:\vars.txt' -Encoding utf8 -Force -Append;
    Get-ChildItem Env: | Out-File 'C:\vars.txt' -Encoding utf8 -Force -Append
}"
```

And now we can compare the results. I ran task while the service account was locked and logged off


**With service account locked**
![With locked user](/img/posts/stask1.png)

**With service account logged off**
![With locked user](/img/posts/stask2.png)

Do you notice the highlighted variable called `APPDATA`? - It's missing while the service account is logged off!
And that's the problem. The controller script uses a credential manager which tries to read a credential store in
the user profile.

## Solution

The solution here is to change the credential store location to `ProgramData` which exists in both scenarios.

## Final Conclusion

If there is one thing I've learned form this: Choose environment variables wisely. You never know how your function
will be used in the future. Especially when you decided using profile related environment variables :D

Did you experience a similar situation? - Let me know...
