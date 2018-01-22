---
layout: post
title: First Post
subtitle: ...on my fresh baked blog
image: /img/first-post.jpg
show-avatar: true
bigimg:
 - "/img/bigimg/books.jpg" : "Chris Lawton (Unsplash.com)"
category: blog
tags: [General]
css: '/css/extend-home.css'
---
{: .text-muted}
As mentioned earlier, I will post powershell and automation related stuff here. I've already started
thinking about the following posts.

#### Hosting a jekyll blog with incompatible plugins on [Github.com](https://www.github.com)

While I created this blog I noticed that there are several plugins for jekyll which caused build errors on github.
Yes you can build the site manually with `bundle exec jekyll build` but this would be a manual step. So why not
using [AppVeyor](https://www.appveyor.com/) for the build itself and the pushing back the final static site?

#### How to implement Piwik on your jekyll blog

This would be a simple showcase about how to add custom variables and using them in the piwik tracker script.

#### Ponduit

I love working with [Phrabricator](https://www.phacility.com/phabricator/) whenever I can't use github. But If you
need to work with the API called Conduit there are some pitfalls for windows user. If you don't want to install the
full cli called arcanist there weren't any simple alternatives. That's why I wrote a little Powershell based client.
I would like to show you the basic features in this article.

#### PSCoverage

An other project of mine is PSCoverage. It uses `Pester` and generates a coverage report of a given module and sends
it to [Coveralls.io](https://coveralls.io).

#### How to learn Powershell

This post is about how to learn powershell in an effective way. Basically this should help with a collection of good
resources.
