# About

This is repo contains the source files of my personal jekyll based blog.
The theme is based on [beautiful-jekyll](https://github.com/daattali/beautiful-jekyll) by Dean Attali.

❓ **If you like my customization, start with cloning the origin repository by Dean and follow his readme article!**

I contribute to his repository and try to create PRs for all the changes I did. Chances are good that these PRs are already merged...


## ⚠️ Note: github-pages compatibility ⚠️

I recently switched back to the native github pages builds. This means I don't use AppVeyor build engine for the
jekyll builds anymore.

So now it's possible for your to fork this repo and continue with the work without any other external tools or build
steps.

### Page / Post Template
> My personal template for all available *Front Matter* YAML vars

```YAML
---
layout: # page | post (blog) | minimal
title:	# Page or blog post title
subtitle: # Short description of page or blog post that goes under the title
image: # /path/to/img
show-avatar:  #(false) | true
bigimg:	# /path/to/img - or multiple entries <- "Path": "Description">
category: # Powershell
tags: # [tag1, tag2, tag3]

# ---- Jekyll optional vars ---- #
# date: # overrides tha tage from the file name YYYY-MM-DD HH:MM:SS
# permalink: # (default /year/month/day/title.html)
# published: # (true) | false

# ---- Theme based optional vars ---- #
# comments: # (false) | true
# show-avatar: # (true) | false
# social-share: # If you don't want to show buttons to share a blog post on social media, use social-share: false (this feature is turned on by default).
# use-site-title: # (false) | true
---
```

### Snippets

#### Table of Content

```markdown
**Content**

* TOC Placeholder
{:toc}
```

#### Content Summary
```
{% include about.html content="Some awesome content summary goes in here...." %}
```
