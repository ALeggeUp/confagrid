---
layout: page

title: A Journey to the PowerShell Citadel
subtitle: All important places you need to know for learning.
permalink: "powershell-resources"
excerpt: "This page represents my favorite list of PowerShell resources. When you start learning a new language you
often get confused by all the different guides, standards and best practices. So I started collecting all important
sources you need to know."
image: "/img/image/ps-journey.jpg"
show-avatar: true
bigimg:	# /path/to/img - or multiple entries <- "Path": "Description">
  - "/img/bigimg/res-posh.jpg" : "Photo by Eugenio Mazzone on Unsplash"
category: Resources
tags: [Powershell]
googlefonts: ["Share+Tech+Mono"]
comments: true
# custom page vars
edit: "14-12-2017"
---

{% include about.html footer=true readingtime=true %}

# The Journey Begins Now Young Novice

In this section you find the most basic stuff. It's aimed to help beginners who just got in touch with PowerShell.
All external links refer to the latest production ready PowerShell version. This is currently the version `5.1`.

## Docs

* [Official Documentation] - Microsoft recently moved all the PowerShell documentation to [docs.microsoft.com].
* [PowerShell Reference] - If you need details about a specific function or cmdlets you can find it there. The
  Reference is grouped by the built in modules. You can expand each group and select the individual help page of
  each function.
* [About Pages] - The docs does not only contain a reference of all the built in functions. It also contains the so
  called *About* pages. These pages explain specific concepts and language related topics. You can find them in a
  separate *about* section of the **Microsoft.PowerShell.Core** module reference. The titles always starts with
  *about_* and the specific name.
* [The Monad Manifesto] - The Monad Manifesto is the original Jeffrey Snover-authored document that results in the
  Windows PowerShell we know today.

[Official Documentation]: https://docs.microsoft.com/en-us/powershell
[docs.microsoft.com]: https://docs.microsoft.com
[PowerShell Reference]: https://docs.microsoft.com/en-us/powershell/scripting/powershell-scripting?view=powershell-5.1
[About Pages]: https://docs.microsoft.com/de-de/powershell/module/microsoft.powershell.core/about/about_aliases?view=powershell-5.1
[The Monad Manifesto]: https://www.gitbook.com/book/devops-collective-inc/the-monad-manifesto-annotated/details

{: .box-note}
<i class="fa fa-commenting icon-blue" aria-hidden="true"></i> **NOTE:** You can also display the *about_* pages with
a PowerShell function itself. To get a list of all about pages just use `Get-Help about_*`. All you need to to is pick
a topic name and use the function again like this: `Get-Help about_Functions`.

## Start using PowerShell

You can't learn PowerShell like every other language if you don't use it. So try to solve basic tasks with PowerShell.
Google around or ask questions in communities like:

* [StackOverflow] - The bes QA platform out there for finding solutions and help for specific topics or questions.
  Don't post general or vague questions.
* [PowerShell.org] - A very helpful and friendly community.

[StackOverflow]: https://stackoverflow.com/questions/tagged/powershell
[PowerShell.org]: https://powershell.org/forums

# Acolyte

Once you got familiar with the basic concepts start discovering advanced techniques like *Functions*,
*Modules*, *Classes*, *Types*, *Extended Type System*, *PowerShell Remoting*, *Jobs*, *Package Management*.

## It's all about the style

As soon as you managed to spell `PowerShell` please respect the [PowerShell Best Practices and Style Guide](https://github.com/PoshCode/PowerShellPracticeAndStyle).
Unfortunately there is no standard like *PEP8* in Python, but this is the most respected and active one. Trust me -
it's maintained by all the experts out there. Just read it, understand it, adopt it and use whatever you can.

{: .box-warning}
<i class="fa fa-bolt icon-yellow" aria-hidden="true"></i> **IMPORTANT:** There is nothing worse than reading an ugly
piece of code. And yes, ugly code works as well but it's impossible to read, understand, review, maintain and makes
no fun!

## Important Modules

* [PSScriptAnalyzer] - Static code checker for PowerShell modules and scripts.
* [Pester] - Test and mock framework
* [platyPS] - Write PowerShell External Help in Markdown.
* [Plaster] - Plaster is a template-based file and project generator written in PowerShell.
* [psake] - A build automation tool.

[PSScriptAnalyzer]: https://github.com/PowerShell/PSScriptAnalyzer
[Pester]:https://github.com/pester/Pester
[platyPS]: https://github.com/PowerShell/platyPS
[Plaster]: https://github.com/PowerShell/Plaster
[psake]: https://github.com/psake/psake

## Tools

In this phase you should also start working with additional tools like:

* VCS (Version Control System):
  * [Git-SCM](https://git-scm.com/)
* additional Editors, IDEs:
  * [Visual Studio Code](https://code.visualstudio.com/)
* CI/CD Environments:
  * [Github](https://github.com/)
  * [Phabricator](https://www.phacility.com/phabricator/)
  * [Jenkins](https://jenkins.io/)
  * [AppVeyor](https://www.appveyor.com/)
  * [GoCD](https://www.gocd.org/)
* Helper
  * [ILSpy](http://ilspy.net/)
* Coverage Reports
  * [Coveralls.io](https://coveralls.io/)

{: .box-note}
<i class="fa fa-commenting icon-blue" aria-hidden="true"></i> **NOTE:** If you develop open source projects I
recommend using [Github](https://github.com/) in combination with [AppVeyor](https://www.appveyor.com/). You don't
need to maintain any additional infrastructure and they are free for public repositories. Otherwise I like working
with Phabricator as *Git Server* and project coordination. If you take this path you definitely need a build server
like Jenkins.

# Maester

Now you mastered the core concepts, know advanced stuff like *PowerShell Remoting*, *Desired Sate Configuration*
and important additional modules. Now it's important to keep up to date and network. Get input from others and
spread the work of PowerShell while teaching others!

## Grand Maester Blogs

* [dille.name] - An awesome DevOps Engineer and Docker Captain with outstanding
  Microsoft and PowerShell knowledge.
* [DonJones.com] - If you never heard about DonJones I can't help you. He is one of the biggest
  maesters in the PowerShell citadel and a great inspiration.
* [Kevin Marquette on PowerShell Theory] - A great blog about various PowerShell
  topics.
* [keithhill.wordpress.com] - One of the 4 maintainers of the [PowerShell Extension]
  for [VS Code].
* [xainey.github.io] - Michael Willis blog contains great articles about PowerShell classes,
  module creation and building frontend for PowerShell. It's high quality content and easy to follow expert topics.

[dille.name]: http://dille.name
[DonJones.com]: https://donjones.com/
[Kevin Marquette on PowerShell Theory]: https://kevinmarquette.github.io/
[keithhill.wordpress.com]: http://rkeithhill.wordpress.com
[xainey.github.io]: https://xainey.github.io/
[PowerShell Extension]: https://github.com/PowerShell/vscode-powershell
[VS Code]:https://code.visualstudio.com/
## Get in touch with the Archmaesters

Take a look at the [PowerShell Slack Team](http://slack.poshcode.org/) if your searching other active PowerShell experts.

# Additional Resources

* [Writing Modules]- Resources for writing PowerShell Modules. from
  *Writing PowerShell Modules: Tips, Tools, and Best Practices session at the 2017 PowerShell + DevOps Global Summit*
* [Awesome PowerShell] - A curated list of delightful PowerShell packages and resources.

[Writing Modules]: https://github.com/RamblingCookieMonster/WritingModules
[Awesome PowerShell]: https://github.com/janikvonrotz/awesome-powershell
