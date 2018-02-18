---
layout: post

title:  "AVR Dragon"
subtitle: ""
image: "/img/posts/02/dragon_sign.gif"
show-avatar: true
category: ["Conversational"]
tags: ["Conversational", "Project Log", "Mini Project"]
comments: true
social-links: true

---
My friend Andy is a tech guy as well, he’s not a software engineer but he is “really good with computers” to use an overused phrase.  Not like the teenager down the street that you know, I mean really good.  Andy is a gamer, which is where I think the interest in the hardware came from, he’s always upgrading his video cards, motherboards and memory to get every bit of power possible out of his setup but with good value in mind as well, he is my go-to guy for all things hardware.

Anyway, so that’s a bit of an intro on Andy, I’m sure he’ll come up multiple times, he has similar interests and we cross paths often on things we’re interested in, or just social gatherings.

Andy had some project where he needed an AVR Dragon, so he purchased one, used it once and then put it aside.  Knowing that I was dabbling in this area Andy decided to give it to me to see if I had other uses for it.  Turns out it is a great little tool that I didn’t even know I needed, Thanks Andy!

I have played around with an Arduino Uno (Andy gave the Uno to me as well, actually) quite a bit trying to figure out what I wanted to do with it always using the dev board to upload to the AVR chip, but the AVR Dragon allows you to upload to an AVR chip without needing the Uno dev board plus some other cool things that I won’t get into now, that’s not the reason I wanted to write about it now.

When you purchase an AVR Dragon, it comes like this:

![AVR Dragon](/img/posts/02/m077621p01wl.jpg "AVR Dragon")

You get those few header pins and then a bunch of pins that seem like “future expansion”.  Turns out this is intentional and Atmel has some information on how customize it for what you need to do this [here](http://www.atmel.com/webdoc/avrdragon/avrdragon.prototype_area.html).

So, now finally I get to why I wanted to talk about it, cool little mini project, I added the headers and ZIF socket as they suggest.

![AVR Dragon](/img/posts/02/img_20160205_090506.jpg "AVR Dragon")
![AVR Dragon](/img/posts/02/img_20160205_090452.jpg "AVR Dragon")
![AVR Dragon](/img/posts/02/img_20160205_090350.jpg "AVR Dragon")

I got the ZIF socket on [eBay $2USD from China](http://www.ebay.ca/itm/221917430808?_trksid=p2057872.m2749.l2649&ssPageName=STRK%3AMEBIDX%3AIT), took about two weeks to receive which is quicker than usual, the header pins I got from a local electronics chain ([Sayal](http://www.sayal.com/STORE/index.asp)).  It only took ten minutes or so, the hardest part was checking the solder joints against the dark board, I need to improve the lighting in my lab.

