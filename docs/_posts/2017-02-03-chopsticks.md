---
layout: post

title:  "They're hanging in there with the chopsticks, aren't they?"
subtitle: ""
image: "/img/posts/20/faa7735cfcda4df2978efbf532379e49--eat-sushi-sushi-time.jpg"
show-avatar: true
category: ["Conversational"]
tags: ["Conversational", "Project", "Software", "Build Tools"]
comments: true
social-links: true

---
That classic Seinfeld joke sums up exactly how I felt when I started looking at embedded firmware projects.  It seems like there are only two ways that embedded software developers build things, either in crazy expensive IDE’s or with ‘make’ files.  You know, the files with cryptic file rules that are often created by other scripts and are built by a tool that was created in 1977?

![alt text](/img/posts/20/1989986.jpg " ")

The Java world seems to be leading the pack with build/dependency options, with solid build tools like Maven, Gradle or even Ant+Ivy.  Even the hipsters have Grunt, Gulp or NPM for their NodeJS craziness, Rails has Rake, it seems like everyone is finding ways to make builds easier, developers don’t really want to have to mess with the build tools but we’re in the best position to do it properly.

Of course I can follow and use ‘make’ files if I have to, just like I can use chopsticks if I have to, but when it comes to picking the right tool for the job most of the time I’m not going to choose chopsticks and I’m definitely not going to choose make.

So, why are embedded developers still using ‘make’ files?  I don’t understand it, there’s got to be a better way!

I’m very familiar with Maven, I still think it is the best build and dependency tool available for Java, but it can get a little verbose when you’re trying to do something complicated in your build like cross-compiling and it’s not super slick trying to use external tools. Android has settled on Gradle for their builds and they have native cross compiling, external tools along with Java so it sounded like it might fit the bill.

It took a while, but I finally got a Gradle build working the way I want, able to build and test a firmware project for my Nucleo board, works right out of the box and even allows easy separation of interfaces, platform specific implementations and mocks and other test doubles for tests without having to fiddle around with it just by placing files in the right spot, similar to how Maven is out of the box with Java.

I’ve released the tool as an open source project on GitHub, I hope that someone someday stumbles across it and finds it helpful, but either way I feel a sense of accomplishment that I at least put it out there because I would hate for it to be silly ‘make’ files that would be the thing that deters someone from getting to experience some of the joys of getting into embedded software development.

If you’re interested, you can find the project here: [github.com/ALeggeUp/embedded-builder-gradle-template](https://github.com/ALeggeUp/embedded-builder-gradle-template)
