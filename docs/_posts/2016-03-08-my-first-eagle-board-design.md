---
layout: post

title:  "My First EAGLE Board Design"
subtitle: ""
image: "/img/posts/09/eagle.jpg"
show-avatar: true
category: ["Conversational"]
tags: ["Conversational", "Project", "EAGLE"]
comments: true
social-links: true

---
Two years ago I started on my irrigation system, it became one of the driving factors to get me back into electronics.

I touched on it a little in a previous post, but as a software developer, I had always wanted to control physical things with software but never was really too interested in toy robots, etc.  I thought that the irrigation system would be a great project because basically it’s just flipping a switch on and off on a timer.

I had a Raspberry Pi, so I did some research on how to use the GPIO’s and started figuring out what I needed to do to turn the sprinkler’s on and off.  It turns out that it is pretty trivial to turn on a GPIO connected to a relay to close the 24VAC circuit turning on the sprinkler.  Too trivial.  It did not satisfy my desire to “learn electronics”, I didn’t feel like I got anything out of the experience that I wouldn’t have gotten for example from trying to install a kernel module to get sound working on the Pi.

I did end up learning a few things with that first iteration though.  I had the original Raspberry Pi which had limited GPIO pins so I had to figure out how to add pins to control more zones, which lead me to the MCP23008 and since they need 5V and the Raspberry Pi runs at 3.3V I had another problem to solve which lead me to darlington arrays and then I was starting to get somewhere.

So, there is no real reason why I couldn’t use the Raspberry Pi to control the sprinklers directly, but where’s the fun in that?  I decided I needed to get “closer to the metal” which means embedded software, which for version one means Arduino.

![alt text](/img/posts/09/screen-shot-2016-03-07-at-10-56-37-pm.png " ")

The above board should work, I think.  It should allow you to put an ATMEGA328P in the socket, programmed already or through the ICSP pins that I cleverly added.  I’m never going to get it manufactured though, turns out that even with the limits on the free version of EAGLE you can create a board that is really expensive to get manufactured (at least in North America).  If I’m going to design a usable board that is worth getting manufactured, I’m going to need to start looking at surface mount components and improve my soldering skills.  I guess there’s a reason why they make these boards double-sided.
